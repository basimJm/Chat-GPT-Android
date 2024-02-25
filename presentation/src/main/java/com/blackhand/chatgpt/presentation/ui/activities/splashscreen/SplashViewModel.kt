package com.blackhand.chatgpt.presentation.ui.activities.splashscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blackhand.chatgpt.domin.model.UserInfoRemoteModel
import com.blackhand.chatgpt.domin.repo.UserInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val userInfoRepository: UserInfoRepository) :
    ViewModel() {
    private val _getUserInfo = MutableStateFlow<Response<UserInfoRemoteModel?>?>(null)
    val getUserInfo: StateFlow<Response<UserInfoRemoteModel?>?> = _getUserInfo.asStateFlow()
    private val _loading = MutableStateFlow<Boolean>(false)
    val loading = _loading.asStateFlow()
    private val _error = MutableStateFlow<Exception?>(null)
    val error = _error.asStateFlow()

    init {
        fetchLoginUserInfo()
    }

    private fun fetchLoginUserInfo() {
        viewModelScope.launch {
            _loading.emit(true)
            try {

                val data = userInfoRepository.getUserInfo()
                Log.d("USERTOKENVALID", "message is  : ${data.body()}")
                if (data.isSuccessful) {
                    _getUserInfo.emit(data)
                    Log.d("USERTOKENVALID", "view model response success")
                } else {
                    Log.d("USERTOKENVALID", "view model response failed")
                }

            } catch (e: Exception) {
                _error.emit(e)
            }
            _loading.emit(false)
        }
    }
}