package com.blackhand.chatgpt.presentation.splash

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.blackhand.chatgpt.core.response.NetworkResult
import com.blackhand.chatgpt.domin.model.UserInfoRemoteModel
import com.blackhand.chatgpt.presentation.chat.MainActivity
import com.blackhand.chatgpt.presentation.register.features.ui.activity.LogActivity
import com.blackhand.chatgpt.presentation.splash.databinding.ActivitySplashScreenBinding
import com.intuit.sdp.BuildConfig
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {
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

    private fun initObserver() {
        lifecycleScope.launch {
            splashViewModel.getUserInfo.observe(this@SplashScreenActivity, Observer { response ->
                when (response) {
                    is NetworkResult.Success -> {
                        navigateToMainActivity()
                    }

                    is NetworkResult.Loading -> {
                        binding.pbLoading.show()
                    }

                    is NetworkResult.Error -> {
                        navigateToLogActivity()
                    }

                    else -> {}
                }
            })
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
        val background = ContextCompat.getDrawable(
            this,
            com.blackhand.chatgpt.common.sharedui.R.drawable.gradient_theme
        )
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