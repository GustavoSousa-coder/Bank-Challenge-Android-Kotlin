package com.example.bankchellengemobileapp.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.bankchellengemobileapp.BuildConfig
import com.example.bankchellengemobileapp.R
import com.example.bankchellengemobileapp.data.client.dto.AuthenticationRequestDTO
import com.example.bankchellengemobileapp.data.enums.AccountStatus
import com.example.bankchellengemobileapp.network.OnboardingManager
import com.example.bankchellengemobileapp.network.RetrofitClient
import com.example.bankchellengemobileapp.network.TokenManager
import com.example.bankchellengemobileapp.ui.main.MainActivity
import com.example.bankchellengemobileapp.ui.register.AccountSetupActivity
import com.example.bankchellengemobileapp.ui.register.RegisterActivity
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException
import java.util.UUID

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val login: EditText = findViewById(R.id.editEmail)
        val password: EditText = findViewById(R.id.editSenha)
        val buttonEnter: Button = findViewById(R.id.btnEntrar)
        val txtCadastro: TextView = findViewById(R.id.txtCadastro)


        buttonEnter.setOnClickListener {

            if (BuildConfig.SKIP_VALIDATION) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
                return@setOnClickListener
            }

            val dataEntered = listOf(login, password).all { it.text.toString().isNotBlank() }

            if (!dataEntered) {
                Toast.makeText(this, "Preencha login e senha", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val authRequest = AuthenticationRequestDTO(
                login = login.text.toString(),
                password = password.text.toString()
            )

            val clientLoginApi = RetrofitClient.getClientLoginApi(this)

            lifecycleScope.launch {
                try {
                    val response = clientLoginApi.login(authRequest)

                    if (response.isSuccessful) {
                        val authResponse = response.body()
                        if (authResponse != null) {
                            TokenManager.saveSession(this@LoginActivity, authResponse.token, authResponse.uuid)
                            val destination = destinationAfterLogin(authResponse.uuid)
                            if (destination == null) {
                                TokenManager.clearToken(this@LoginActivity)
                                Toast.makeText(this@LoginActivity, "Não foi possível carregar sua conta. Tente novamente.", Toast.LENGTH_SHORT).show()
                                return@launch
                            }

                            startActivity(Intent(this@LoginActivity, destination))
                            finish()
                        }
                    } else {
                        Toast.makeText(this@LoginActivity, "Login ou senha inválidos", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: CancellationException) {
                    throw e
                } catch (e: Exception) {
                    TokenManager.clearToken(this@LoginActivity)
                    Toast.makeText(this@LoginActivity, "Erro de conexão: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }



        }

        txtCadastro.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

    }

    /**
     * Determines the next Activity the user should be directed to after a successful login based on their account status.
     *
     * This function fetches the accounts associated with the provided client UUID. If an active account
     * is found, it saves the account ID and directs the user to the [MainActivity]. If no active account
     * exists, it directs the user to the [AccountSetupActivity] to complete their registration.
     *
     * @param clientUuid The unique identifier of the authenticated client.
     * @return The class of the Activity to navigate to, or `null` if the account information could not be retrieved.
     */
    private suspend fun destinationAfterLogin(clientUuid: UUID): Class<*>? {
        val response = RetrofitClient.getAccountApi(this).getAllByClientId(clientUuid)
        if (!response.isSuccessful) return null

        val account = response.body()?.firstOrNull { it.status == AccountStatus.ACTIVE }
            ?: return AccountSetupActivity::class.java

        TokenManager.saveAccountId(this, account.uuid)
        OnboardingManager.clearPendingClientUuid(this)
        return MainActivity::class.java
    }


}