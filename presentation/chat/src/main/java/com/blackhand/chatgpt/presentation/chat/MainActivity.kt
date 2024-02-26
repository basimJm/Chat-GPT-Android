package com.blackhand.chatgpt.presentation.chat

import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.blackhand.chatgpt.presentation.chat.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var isFirstClick = true

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setStatusBarGradiant()
        onBackPressedCallBack()
    }

    /**
     * this function give main activity background so also change status and navigation bar
     */
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

    /**
     * this function handle back press
     */
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun onBackPressedCallBack() {
        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (isFirstClick) {
                        Toast.makeText(
                            this@MainActivity,
                            getString(com.blackhand.chatgpt.common.sharedui.R.string.press_again_to_close_app),
                            Toast.LENGTH_SHORT
                        ).show()
                        isFirstClick = false
                    } else {
                        finish()
                    }
                }
            })
    }
}