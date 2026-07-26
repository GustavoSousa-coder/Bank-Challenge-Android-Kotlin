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

        val logoMark: View = findViewById(R.id.logoMark)
        val txtAppName: TextView = findViewById(R.id.txtAppName)
        val shimmerView: View = findViewById(R.id.shimmerView)
        val progressTrack: View = findViewById(R.id.progressTrack)
        val progressFill: View = findViewById(R.id.progressFill)

        logoMark.post {
            revealLogo(logoMark, txtAppName) {
                runShimmer(shimmerView, logoMark) {
                    runProgress(progressTrack, progressFill) {
                        navigateNext()
                    }
                }
            }
        }
    }

    private fun revealLogo(logoMark: View, txtAppName: TextView, onFinished: () -> Unit) {
        logoMark.scaleX = 0.85f
        logoMark.scaleY = 0.85f

        val logoFade = ObjectAnimator.ofFloat(logoMark, View.ALPHA, 0f, 1f)
        val logoScaleX = ObjectAnimator.ofFloat(logoMark, View.SCALE_X, 0.85f, 1f)
        val logoScaleY = ObjectAnimator.ofFloat(logoMark, View.SCALE_Y, 0.85f, 1f)

        val textFade = ObjectAnimator.ofFloat(txtAppName, View.ALPHA, 0f, 1f).apply {
            startDelay = 150
        }

        AnimatorSet().apply {
            playTogether(logoFade, logoScaleX, logoScaleY, textFade)
            duration = 500
            interpolator = DecelerateInterpolator()
            addListener(simpleEndListener { onFinished() })
            start()
        }
    }

    private fun runShimmer(shimmerView: View, logoMark: View, onFinished: () -> Unit) {
        val travel = logoMark.width.toFloat().takeIf { it > 0 } ?: 200f
        shimmerView.translationX = -travel
        shimmerView.alpha = 0.9f

        ObjectAnimator.ofFloat(shimmerView, View.TRANSLATION_X, -travel, travel).apply {
            duration = 550
            interpolator = DecelerateInterpolator()
            addListener(simpleEndListener {
                shimmerView.animate().alpha(0f).setDuration(150).withEndAction { onFinished() }.start()
            })
            start()
        }
    }

    private fun runProgress(progressTrack: View, progressFill: View, onFinished: () -> Unit) {
        progressFill.scaleX = 0f
        progressFill.pivotX = 0f

        val trackFadeIn = ObjectAnimator.ofFloat(progressTrack, View.ALPHA, 0f, 1f).apply {
            duration = 200
        }

        val fillGrow = ObjectAnimator.ofFloat(progressFill, View.SCALE_X, 0f, 1f).apply {
            duration = 650
            interpolator = DecelerateInterpolator()
        }

        AnimatorSet().apply {
            play(fillGrow).after(trackFadeIn)
            addListener(simpleEndListener {
                progressTrack.postDelayed({ onFinished() }, 250)
            })
            start()
        }
    }

    private fun navigateNext() {
        val destination = when {
            TokenManager.getToken(this) != null && OnboardingManager.getPendingClientUuid(this) != null ->
                AccountSetupActivity::class.java
            else ->
                LoginActivity::class.java
        }

        startActivity(Intent(this, destination))
        finish()
    }

    private fun simpleEndListener(onEnd: () -> Unit) = object : Animator.AnimatorListener {
        override fun onAnimationEnd(animation: Animator) = onEnd()
        override fun onAnimationStart(animation: Animator) {}
        override fun onAnimationCancel(animation: Animator) {}
        override fun onAnimationRepeat(animation: Animator) {}
    }

}