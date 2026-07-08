package com.example.bankchellengemobileapp.ui.register

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.bankchellengemobileapp.R
import com.example.bankchellengemobileapp.data.account.dto.AccountRequestDTO
import com.example.bankchellengemobileapp.data.enums.AccountType
import com.example.bankchellengemobileapp.network.OnboardingManager
import com.example.bankchellengemobileapp.network.RetrofitClient
import com.example.bankchellengemobileapp.ui.login.LoginActivity
import kotlinx.coroutines.launch

class AccountSetupActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_setup)

        val radioGroup: RadioGroup = findViewById(R.id.radioGroupTipoConta)
        val btnConfirmar: Button = findViewById(R.id.btnConfirmarConta)

        val clientUuid = OnboardingManager.getPendingClientUuid(this)

        if (clientUuid == null) {
            Toast.makeText(this, "Nenhum cadastro pendente encontrado", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        btnConfirmar.setOnClickListener {

            val type = when (radioGroup.checkedRadioButtonId) {
                R.id.radioCorrente -> AccountType.CORRENTE
                R.id.radioPoupanca -> AccountType.POUPANCA
                else -> null
            }

            if (type == null) {
                Toast.makeText(this, "Selecione um tipo de conta", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val accountApi = RetrofitClient.getAccountApi(this)
            val request = AccountRequestDTO(type = type)

            lifecycleScope.launch {
                try {
                    val response = accountApi.save(clientUuid, request)

                    if (response.isSuccessful) {
                        OnboardingManager.clearPendingClientUuid(this@AccountSetupActivity)
                        Toast.makeText(this@AccountSetupActivity, "Conta criada com sucesso!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@AccountSetupActivity, LoginActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this@AccountSetupActivity, "Erro ao criar conta", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(this@AccountSetupActivity, "Erro de conexão: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}