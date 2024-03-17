package com.blackhand.chatgpt.presentation.chat

import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.blackhand.chatgpt.common.sharedui.viewmodel.UserSharedViewModel
import com.blackhand.chatgpt.core.response.NetworkResult
import com.blackhand.chatgpt.domin.model.Session
import com.blackhand.chatgpt.domin.model.UserInfoRemoteModel
import com.blackhand.chatgpt.presentation.chat.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ActionCallBack {

    private lateinit var binding: ActivityMainBinding
    private val userSHaredViewModel: UserSharedViewModel by viewModels()
    private lateinit var sessionAdapter: SessionsAdapter
    var isFirstClick = true

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initObserver()
        setStatusBarGradiant()
        onBackPressedCallBack()
    }

    private fun initObserver() {
        lifecycleScope.launch {
            userSHaredViewModel.getUserInfo.collectLatest { response ->
                userSHaredViewModel.getUserId(response?.data?.userData?.id.toString())
                handleResponse(response)
            }
        }
    }

    private fun handleResponse(response: NetworkResult<UserInfoRemoteModel?>?) {
        when (response) {
            is NetworkResult.Success -> {
                sessionAdapter = SessionsAdapter(response.data?.userData?.sessions, this)
                binding.rvSessionsList.adapter = sessionAdapter
            }

            is NetworkResult.Loading -> {

            }

            is NetworkResult.Error -> {

            }

            else -> {}
        }
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

    override fun onSessionClicked(session: Session?) {
        val bundle = Bundle().apply {
            putSerializable("session", session)
        }
        binding.FCVChat.findNavController().navigate(R.id.chatFragment, bundle)
        binding.dlMain.closeDrawers()
    }

}