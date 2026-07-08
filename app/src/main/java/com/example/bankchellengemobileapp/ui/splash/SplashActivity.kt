package com.example.bankchellengemobileapp.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.bankchellengemobileapp.R
import com.example.bankchellengemobileapp.network.OnboardingManager
import com.example.bankchellengemobileapp.network.TokenManager
import com.example.bankchellengemobileapp.ui.login.LoginActivity
import com.example.bankchellengemobileapp.ui.register.AccountSetupActivity
import kotlin.jvm.java

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({

            val destination = when {
                TokenManager.getToken(this) != null && OnboardingManager.getPendingClientUuid(this) != null ->
                    AccountSetupActivity::class.java
                else ->
                    LoginActivity::class.java
            }

            startActivity(Intent(this, destination))
            finish()

        }, 2500)


    }
}