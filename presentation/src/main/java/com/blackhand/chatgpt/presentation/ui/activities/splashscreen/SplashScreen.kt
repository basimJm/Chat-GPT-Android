package com.blackhand.chatgpt.presentation.ui.activities.splashscreen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.blackhand.chatgpt.domin.model.UserInfoRemoteModel
import com.blackhand.chatgpt.presentation.R
import com.blackhand.chatgpt.presentation.databinding.ActivitySplashScreenBinding
import com.blackhand.chatgpt.presentation.ui.activities.LogActivity
import com.blackhand.chatgpt.presentation.ui.activities.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Suppress("DEPRECATION")
@AndroidEntryPoint
class SplashScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    private var data: UserInfoRemoteModel? = null
    private val splashViewModel: SplashViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setStatusBarGradiant()
        splashAnimation()
        initObserver()
    }

    @SuppressLint("CommitPrefEdits")
    private fun initObserver() {
        lifecycleScope.launch {
            splashViewModel.getUserInfo.collectLatest { response ->
                if (response?.isSuccessful == true) {
                    delay(6000)
                    navigateToMainActivity()
                } else {
                    delay(6000)
                    navigateToLogActivity()
                }
            }
        }
    }


    private fun navigateToLogActivity() {
        startActivity(Intent(this, LogActivity::class.java))

    }

    private fun navigateToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun splashAnimation() {
        val logoAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_splash)
        val loadingAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_loading)
        binding.ivLogo.startAnimation(logoAnimation)
        binding.pbLoading.startAnimation(loadingAnimation)
    }

    private fun setStatusBarGradiant() {
        val window: Window = this.window
        val background = ContextCompat.getDrawable(this, R.drawable.gradient_theme)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)
        window.navigationBarColor = ContextCompat.getColor(this, android.R.color.transparent)
        window.setBackgroundDrawable(background)
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}