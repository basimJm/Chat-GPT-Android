package com.blackhand.chatgpt.presentation.chat

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blackhand.chatgpt.core.constants.Constant.Companion.DEFAULT_VALUE
import com.blackhand.chatgpt.core.response.NetworkResult
import com.blackhand.chatgpt.domin.model.sessions.SessionsHistory
import com.blackhand.chatgpt.domin.repo.UserInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val userInfoRepository: UserInfoRepository,
    private val saveStateHandle: SavedStateHandle
) :
    ViewModel() {
    private val _chatHistory = MutableStateFlow<NetworkResult<SessionsHistory?>?>(null)
    val chatHistory: StateFlow<NetworkResult<SessionsHistory?>?> = _chatHistory.asStateFlow()

    fun fetchChatHistory(userID:String,sessionID: String) {
        viewModelScope.launch {
//            val userID = saveStateHandle["userID"] ?: "DEFAULT_VALUE"
//            Log.d("USERTOKENVALID", userID.toString())
            _chatHistory.emit(NetworkResult.Loading())
            val chatHistory = userInfoRepository.getUserChatHistory(userID, sessionID)
            if (chatHistory.isSuccessful && chatHistory.body() != null) {
                _chatHistory.emit(NetworkResult.Success(chatHistory.body()))
            } else if (chatHistory.errorBody() != null) {
                _chatHistory.emit(NetworkResult.Error("session deleted"))
            }
        }
    }
}