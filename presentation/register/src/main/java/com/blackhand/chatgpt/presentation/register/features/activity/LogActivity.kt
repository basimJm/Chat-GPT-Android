package com.blackhand.chatgpt.presentation.register.features.activity

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.blackhand.chatgpt.presentation.register.databinding.ActivityLogBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setStatusBarGradiant()
    }

    private fun setStatusBarGradiant() {
        val window: Window = this.window
        val background = ContextCompat.getDrawable(
            this,
            com.blackhand.chatgpt.common.sharedui.R.drawable.gradient_theme
        )
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)
        window.navigationBarColor =
            ContextCompat.getColor(this, com.blackhand.chatgpt.common.sharedui.R.color.white)
        window.setBackgroundDrawable(background)
    }
}