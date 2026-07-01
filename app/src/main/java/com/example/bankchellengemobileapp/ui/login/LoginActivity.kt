package com.example.bankchellengemobileapp.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.bankchellengemobileapp.R
import com.example.bankchellengemobileapp.ui.home.HomeActivity
import com.example.bankchellengemobileapp.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnEntrar: Button = findViewById(R.id.btnEntrar)
        val txtCadastro: TextView = findViewById(R.id.txtCadastro)


        btnEntrar.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        txtCadastro.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}