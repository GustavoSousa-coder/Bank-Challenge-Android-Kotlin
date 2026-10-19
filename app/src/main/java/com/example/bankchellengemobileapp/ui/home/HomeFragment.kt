package com.example.bankchellengemobileapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.bankchellengemobileapp.R
import com.example.bankchellengemobileapp.network.TokenManager
import com.example.bankchellengemobileapp.ui.login.LoginActivity
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Calendar
import java.util.Locale

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textGreeting: TextView = view.findViewById(R.id.text_greeting)
        val textName: TextView = view.findViewById(R.id.text_user_name)
        val textBalance: TextView = view.findViewById(R.id.balance_value)
        val textAccount: TextView = view.findViewById(R.id.text_account_value)

        textGreeting.text = getString(R.string.home_greeting_format, getString(currentPeriod()))

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    if (state.sessionExpired) {
                        goToLogin()
                        return@collect
                    }

                    state.client?.let { textName.text = it.firstName }

                    state.account?.let { account ->
                        val money = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR"))
                        textBalance.text = money.format(account.balance)

                        val hasAccountData = account.agency.isNotBlank() && account.accountNumber.isNotBlank()
                        (textAccount.parent as View).visibility = if (hasAccountData) View.VISIBLE else View.GONE
                        if (hasAccountData) {
                            textAccount.text = getString(
                                R.string.home_account_format,
                                account.agency,
                                account.accountNumber
                            )
                        }
                    }

                    state.errorMessage?.let { message ->
                        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                        viewModel.errorShown()
                    }
                }
            }
        }
    }

    private fun currentPeriod(): Int {
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        return when {
            hour < 12 -> R.string.period_morning
            hour < 18 -> R.string.period_afternoon
            else -> R.string.period_night
        }
    }

    private fun goToLogin() {
        TokenManager.clearToken(requireContext())
        startActivity(
            Intent(requireContext(), LoginActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        )
        requireActivity().finish()
    }
}