package com.blackhand.chatgpt.presentation.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blackhand.chatgpt.core.constants.Constant.Companion.ERROR_OBJECT
import com.blackhand.chatgpt.core.constants.Constant.Companion.SOME_THING_WENT_WRONG
import com.blackhand.chatgpt.core.constants.Constant.Companion.STATUS_CODE
import com.blackhand.chatgpt.core.enums.StatusCode
import com.blackhand.chatgpt.core.response.NetworkResult
import com.blackhand.chatgpt.domin.model.UserInfoRemoteModel
import com.blackhand.chatgpt.domin.repo.UserInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val userInfoRepository: UserInfoRepository) :
    ViewModel() {
    private val _getUserInfo = MediatorLiveData<NetworkResult<UserInfoRemoteModel?>?>()
    val getUserInfo: LiveData<NetworkResult<UserInfoRemoteModel?>?> = _getUserInfo


    init {
        fetchLoginUserInfo()
    }

    private fun fetchLoginUserInfo() {
        viewModelScope.launch {
            _getUserInfo.postValue(NetworkResult.Loading())
            val response = userInfoRepository.getUserInfo()
            val errorObj =
                JSONObject(response.errorBody()!!.charStream().readText()).getJSONObject(
                    ERROR_OBJECT
                )
                    .optInt(STATUS_CODE)

            handleResponse(response, errorObj)
        }
    }

    private fun handleResponse(
        response: Response<UserInfoRemoteModel?>,
        errorObj: Int
    ) {
        if (response.isSuccessful && response.body() != null) {
            _getUserInfo.postValue(NetworkResult.Success(response.body()))
        } else if (errorObj == StatusCode.INVALID_TOKEN.value) {
            _getUserInfo.postValue(NetworkResult.Error(SOME_THING_WENT_WRONG))
        }
    }
}