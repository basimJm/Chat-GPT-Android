package com.blackhand.chatgpt.presentation.chat

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.blackhand.chatgpt.common.sharedui.viewmodel.UserSharedViewModel
import com.blackhand.chatgpt.core.response.NetworkResult
import com.blackhand.chatgpt.domin.model.Session
import com.blackhand.chatgpt.domin.model.sessions.SessionsHistory
import com.blackhand.chatgpt.presentation.chat.databinding.FragmentChatBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChatFragment : Fragment() {
    private lateinit var binding: FragmentChatBinding
    private val userSHaredViewModel: UserSharedViewModel by viewModels()
    private val chatViewModel: ChatViewModel by viewModels()
    private lateinit var chatAdapter: ChatAdapter
    var sessionID = ""
    var userID = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = arguments?.getSerializable("session") as? Session
        sessionID = data?.id.toString()
        lifecycleScope.launch {
            userSHaredViewModel.getUserInfo.collectLatest {
                Log.d("USERTOKENVALID", it?.data?.userData?.id.toString())

                chatViewModel.fetchChatHistory(it?.data?.userData?.id.toString(), sessionID)
            }
        }

        initObserver()
        initListener()
    }

    private fun initListener() {
        binding.btnSend.setOnClickListener {
            lifecycleScope.launch {
                userSHaredViewModel.fetchLoginUserInfo()
                chatViewModel.fetchChatHistory(userID, sessionID)
            }
        }
    }

    private fun initObserver() {
        lifecycleScope.launch {
            chatViewModel.chatHistory.collectLatest { response ->
                handleResponse(response)
            }
        }
    }

    private fun handleResponse(response: NetworkResult<SessionsHistory?>?) {
        when (response) {
            is NetworkResult.Success -> {
                chatAdapter = ChatAdapter()
                chatAdapter.chatHistories = response.data?.sessions?.chatHistory
                binding.rvChatList.adapter = chatAdapter
                binding.pbChatLoading.isVisible = false
            }

            is NetworkResult.Loading -> {
                binding.pbChatLoading.isVisible = true
            }

            is NetworkResult.Error -> {

            }

            else -> {}
        }
    }
}