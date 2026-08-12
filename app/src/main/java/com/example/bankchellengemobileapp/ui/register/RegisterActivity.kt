package com.example.bankchellengemobileapp.ui.register

import android.content.Intent
import com.example.bankchellengemobileapp.BuildConfig
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.bankchellengemobileapp.R
import com.example.bankchellengemobileapp.data.client.dto.ClientRequestDTO
import com.example.bankchellengemobileapp.network.OnboardingManager
import com.example.bankchellengemobileapp.network.RetrofitClient
import com.example.bankchellengemobileapp.ui.login.LoginActivity
import com.example.bankchellengemobileapp.ui.main.MainActivity
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val name: EditText = findViewById(R.id.editNome)
        val cpf: EditText = findViewById(R.id.editCpf)
        val dateOfBirth: EditText = findViewById(R.id.editDataNascimento)
        val email: EditText = findViewById(R.id.editEmailRegistro)
        val password: EditText = findViewById(R.id.editSenhaRegistro)
        val buttonRegister: Button = findViewById(R.id.btnCadastrar)

        applyMask(dateOfBirth, "##/##/####")
        applyMask(cpf, "###.###.###-##")

        buttonRegister.setOnClickListener {

            if (BuildConfig.SKIP_VALIDATION) {
                startActivity(Intent(this, AccountSetupActivity::class.java))
                finish()
                return@setOnClickListener
            }
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
                    cpf = cpf.text.toString().filter { it.isDigit() },
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

    private fun applyMask(editText: EditText, mask: String) {
        editText.addTextChangedListener(object : TextWatcher {
            private var isUpdating = false

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (isUpdating) return

                val digits = s.toString().filter { it.isDigit() }
                val formatted = buildMasked(digits, mask)

                isUpdating = true
                editText.setText(formatted)
                editText.setSelection(formatted.length)
                isUpdating = false
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun buildMasked(digits: String, mask: String): String {
        val result = StringBuilder()
        var digitIndex = 0

        for (maskChar in mask) {
            if (digitIndex >= digits.length) break

            if (maskChar == '#') {
                result.append(digits[digitIndex])
                digitIndex++
            } else {
                result.append(maskChar)
            }
        }

        return result.toString()
    }
}