package com.blackhand.chatgpt.presentation.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.blackhand.chatgpt.presentation.R
import com.blackhand.chatgpt.presentation.databinding.ActivitySplashScreenBinding

@Suppress("DEPRECATION")
class SplashScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setStatusBarGradiant()
        navigateToMainActivity()
    }

    private fun navigateToMainActivity() {
        splashAnimation()
        Handler().postDelayed({
            startActivity(Intent(this, LogActivity::class.java))
        }, 3000)
    }

    private fun splashAnimation() {
        val sideAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_splash)
        binding.ivLogo.startAnimation(sideAnimation)
    }

    private fun setStatusBarGradiant() {
        val window: Window = this.window
        val background = ContextCompat.getDrawable(this, R.drawable.gradient_theme)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)
        window.navigationBarColor = ContextCompat.getColor(this,android.R.color.transparent)
        window.setBackgroundDrawable(background)
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}