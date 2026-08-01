package com.example.bankchellengemobileapp.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.bankchellengemobileapp.R
import com.example.bankchellengemobileapp.data.client.dto.AuthenticationRequestDTO
import com.example.bankchellengemobileapp.network.OnboardingManager
import com.example.bankchellengemobileapp.network.RetrofitClient
import com.example.bankchellengemobileapp.network.TokenManager
import com.example.bankchellengemobileapp.ui.home.HomeActivity
import com.example.bankchellengemobileapp.ui.register.AccountSetupActivity
import com.example.bankchellengemobileapp.ui.register.RegisterActivity
import kotlinx.coroutines.launch
import kotlin.jvm.java

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val login: EditText = findViewById(R.id.editEmail)
        val password: EditText = findViewById(R.id.editSenha)
        val buttonEnter: Button = findViewById(R.id.btnEntrar)
        val txtCadastro: TextView = findViewById(R.id.txtCadastro)


        buttonEnter.setOnClickListener {

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
                            val destination = if (OnboardingManager.getPendingClientUuid(this@LoginActivity) != null) {
                                AccountSetupActivity::class.java
                            } else {
                                        HomeActivity::class.java
                            }

                            startActivity(Intent(this@LoginActivity, destination))
                            finish()
                        }
                    } else {
                        Toast.makeText(this@LoginActivity, "Login ou senha inválidos", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(this@LoginActivity, "Erro de conexão: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        txtCadastro.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

    }

}