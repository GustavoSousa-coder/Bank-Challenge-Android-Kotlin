package com.example.bankchellengemobileapp.ui.splash

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.bankchellengemobileapp.R
import com.example.bankchellengemobileapp.network.OnboardingManager
import com.example.bankchellengemobileapp.network.TokenManager
import com.example.bankchellengemobileapp.ui.login.LoginActivity
import com.example.bankchellengemobileapp.ui.register.AccountSetupActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val destination = when {
            TokenManager.getToken(this) != null && OnboardingManager.getPendingClientUuid(this) != null ->
                AccountSetupActivity::class.java

            else ->
                LoginActivity::class.java
        }

        startActivity(Intent(this, destination))
        finish()

    }

}