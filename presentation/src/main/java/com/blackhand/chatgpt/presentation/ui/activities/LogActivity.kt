package com.blackhand.chatgpt.presentation.ui.activities

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.blackhand.chatgpt.presentation.R
import com.blackhand.chatgpt.presentation.databinding.ActivityLogBinding

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
        val background = ContextCompat.getDrawable(this, R.drawable.gradient_theme)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.white)
        window.setBackgroundDrawable(background)
    }
}