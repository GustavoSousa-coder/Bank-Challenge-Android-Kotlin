package com.example.bankchellengemobileapp.ui.register

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.bankchellengemobileapp.R
import com.example.bankchellengemobileapp.data.client.dto.ClientRequestDTO
import com.example.bankchellengemobileapp.network.OnboardingManager
import com.example.bankchellengemobileapp.network.RetrofitClient
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import kotlin.jvm.java
import android.text.Editable
import android.text.TextWatcher
import com.example.bankchellengemobileapp.ui.login.LoginActivity

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val name: EditText = findViewById(R.id.editNome)
        val cpf: EditText = findViewById(R.id.editCpf)
        val dateOfBirth: EditText = findViewById(R.id.editDataNascimento)

        dateOfBirth.addTextChangedListener(object : TextWatcher {
            private var isUpdating = false
            private var oldText = ""

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val str = s.toString().replace("[^\\d]".toRegex(), "")
                var formatted = ""

                if (isUpdating) {
                    oldText = str
                    isUpdating = false
                    return
                }

                isUpdating = true

                if (str.length >= 2) {
                    formatted += str.substring(0, 2) + "/"
                    if (str.length >= 4) {
                        formatted += str.substring(2, 4) + "/"
                        formatted += if (str.length > 8) str.substring(4, 8) else str.substring(4)
                    } else {
                        formatted += str.substring(2)
                    }
                } else {
                    formatted = str
                }

                dateOfBirth.setText(formatted)
                dateOfBirth.setSelection(formatted.length)
            }

            override fun afterTextChanged(s: Editable?) {}
        })
        val email: EditText = findViewById(R.id.editEmailRegistro)
        val password: EditText = findViewById(R.id.editSenhaRegistro)
        val buttonRegister: Button = findViewById(R.id.btnCadastrar)

        buttonRegister.setOnClickListener {

            val dataEntered = listOf(name, cpf, dateOfBirth, email, password).all { it.text.toString().isNotBlank() }

            if (dataEntered) {
                val dateConverted = try {
                    LocalDate.parse(
                        dateOfBirth.text.toString(),
                        DateTimeFormatter.ofPattern("dd/MM/yyyy")
                    )
                } catch (e: DateTimeParseException) {
                    Toast.makeText(this, "Data inválida, use o formato dd/MM/yyyy", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val request = ClientRequestDTO(
                    name = name.text.toString(),
                    cpf = cpf.text.toString(),
                    dateOfBirth = dateConverted,
                    email = email.text.toString(),
                    password = password.text.toString()
                )

                val clientApi = RetrofitClient.getClientApi(this)

                lifecycleScope.launch {
                    try {
                        val response = clientApi.save(request)

                        if (response.isSuccessful) {
                            val clientCreated = response.body()
                            if (clientCreated != null) {
                                OnboardingManager.savePendingClientUuid(this@RegisterActivity, clientCreated.uuid)
                                startActivity(
                                    Intent(
                                        this@RegisterActivity,
                                        LoginActivity::class.java
                                    )
                                )
                                finish()
                            }
                        } else {
                            Toast.makeText(this@RegisterActivity, "Erro ao cadastrar", Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                        Toast.makeText(this@RegisterActivity, "Erro de conexão: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }

            } else {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }


        }
    }
}