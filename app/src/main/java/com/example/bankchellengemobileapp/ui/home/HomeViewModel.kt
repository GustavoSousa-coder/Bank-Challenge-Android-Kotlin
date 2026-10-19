package com.example.bankchellengemobileapp.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankchellengemobileapp.BuildConfig
import com.example.bankchellengemobileapp.data.account.model.AccountModel
import com.example.bankchellengemobileapp.data.client.model.ClientModel
import com.example.bankchellengemobileapp.network.RetrofitClient
import com.example.bankchellengemobileapp.network.TokenManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

data class HomeUiState(
    val isLoading: Boolean = false,
    val client: ClientModel? = null,
    val account: AccountModel? = null,
    val errorMessage: String? = null,
    val sessionExpired: Boolean = false
)

class HomeViewModel(app: Application) : AndroidViewModel(app) {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        load()
    }

    fun load() {
        if (BuildConfig.SKIP_VALIDATION) {
            _uiState.update {
                it.copy(
                    client = ClientModel(id = "demo", fullName = "Cliente Demo", firstName = "Cliente"),
                    account = AccountModel(agency = "0001", accountNumber = "00000-0", balance = 1234.56)
                )
            }
            return
        }

        val context = getApplication<Application>()
        val clientUuid = TokenManager.getClientUuid(context)
        val accountId = TokenManager.getAccountId(context)

        if (clientUuid == null || accountId == null) {
            _uiState.update { it.copy(sessionExpired = true) }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            try {
                val clientResponse = RetrofitClient.getClientApi(context).getById(clientUuid)
                val accountResponse = RetrofitClient.getAccountApi(context).getById(accountId)

                val clientDto = clientResponse.body()
                val accountDto = accountResponse.body()

                if (clientResponse.isSuccessful && accountResponse.isSuccessful &&
                    clientDto != null && accountDto != null
                ) {
                    val client = ClientModel(
                        id = clientDto.uuid.toString(),
                        fullName = clientDto.name,
                        firstName = clientDto.name.trim().substringBefore(" ")
                    )
                    val account = AccountModel(
                        agency = "",
                        accountNumber = "",
                        balance = accountDto.balance.toDouble()
                    )
                    _uiState.update { it.copy(isLoading = false, client = client, account = account) }
                } else {
                    _uiState.update {
                        it.copy(isLoading = false, errorMessage = "Não foi possível carregar os dados")
                    }
                }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, errorMessage = "Erro de conexão") }
            }
        }
    }

    fun errorShown() {
        _uiState.update { it.copy(errorMessage = null) }
    }
}