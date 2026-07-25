package com.example.bankchellengemobileapp.ui.splash

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.OvershootInterpolator
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

        val bars = listOf<View>(
            findViewById(R.id.bar1), findViewById(R.id.bar2), findViewById(R.id.bar3),
            findViewById(R.id.bar4), findViewById(R.id.bar5), findViewById(R.id.bar6)
        )
        val barsContainer: View = findViewById(R.id.barsContainer)
        val txtAppName: TextView = findViewById(R.id.txtAppName)
        val shimmerView: View = findViewById(R.id.shimmerView)

        barsContainer.post {
            riseBarsDiagonally(bars) {
                collapseToCorner(barsContainer) {
                    revealLogo(txtAppName, shimmerView) {
                        navigateNext()
                    }
                }
            }
        }
    }

    private fun riseBarsDiagonally(bars: List<View>, onFinished: () -> Unit) {
        val screenHeight = resources.displayMetrics.heightPixels.toFloat()
        val diagonalOffsetX = 60f // deslocamento horizontal, cria a diagonal
        val staggerDelay = 100L
        val barDuration = 550L

        val animators = bars.mapIndexed { index, view ->
            view.translationY = screenHeight
            view.translationX = -diagonalOffsetX

            val moveY = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, screenHeight, 0f)
            val moveX = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, -diagonalOffsetX, 0f)

            AnimatorSet().apply {
                playTogether(moveY, moveX)
                duration = barDuration
                startDelay = index * staggerDelay
                interpolator = OvershootInterpolator(1.1f)
            }
        }

        AnimatorSet().apply {
            playTogether(animators)
            addListener(simpleEndListener { onFinished() })
            start()
        }
    }

    private fun collapseToCorner(barsContainer: View, onFinished: () -> Unit) {
        barsContainer.pivotX = barsContainer.width.toFloat()
        barsContainer.pivotY = 0f

        val scaleX = ObjectAnimator.ofFloat(barsContainer, View.SCALE_X, 1f, 0.12f)
        val scaleY = ObjectAnimator.ofFloat(barsContainer, View.SCALE_Y, 1f, 0.12f)
        val rotate = ObjectAnimator.ofFloat(barsContainer, View.ROTATION, 0f, 35f)

        AnimatorSet().apply {
            playTogether(scaleX, scaleY, rotate)
            duration = 500
            startDelay = 200
            interpolator = AccelerateInterpolator(1.4f)
            addListener(simpleEndListener { onFinished() })
            start()
        }
    }

    private fun revealLogo(textView: TextView, shimmerView: View, onFinished: () -> Unit) {
        textView.scaleX = 0.9f
        textView.scaleY = 0.9f

        val fadeIn = ObjectAnimator.ofFloat(textView, View.ALPHA, 0f, 1f)
        val scaleX = ObjectAnimator.ofFloat(textView, View.SCALE_X, 0.9f, 1f)
        val scaleY = ObjectAnimator.ofFloat(textView, View.SCALE_Y, 0.9f, 1f)

        AnimatorSet().apply {
            playTogether(fadeIn, scaleX, scaleY)
            duration = 400
            interpolator = DecelerateInterpolator()
            addListener(simpleEndListener { runShimmer(shimmerView, onFinished) })
            start()
        }
    }

    private fun runShimmer(shimmerView: View, onFinished: () -> Unit) {
        val screenWidth = resources.displayMetrics.widthPixels.toFloat()
        shimmerView.translationX = -screenWidth / 2
        shimmerView.alpha = 1f
        shimmerView.rotation = 20f

        ObjectAnimator.ofFloat(shimmerView, View.TRANSLATION_X, -screenWidth / 2, screenWidth / 2).apply {
            duration = 500
            interpolator = DecelerateInterpolator()
            addListener(simpleEndListener {
                shimmerView.postDelayed({ onFinished() }, 500)
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