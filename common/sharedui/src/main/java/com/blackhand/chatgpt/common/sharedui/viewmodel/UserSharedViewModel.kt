package com.blackhand.chatgpt.common.sharedui.viewmodel

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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class UserSharedViewModel @Inject constructor(private val userInfoRepository: UserInfoRepository) :
    ViewModel() {
    private val _getUserInfo = MutableStateFlow<NetworkResult<UserInfoRemoteModel?>?>(null)
    val getUserInfo: StateFlow<NetworkResult<UserInfoRemoteModel?>?> = _getUserInfo.asStateFlow()
    private val _userID = MutableStateFlow<String?>(null)
    val userID: StateFlow<String?> = _userID.asStateFlow()

    init {
        fetchLoginUserInfo()
    }

    fun fetchLoginUserInfo() {
        viewModelScope.launch {
            _getUserInfo.emit(NetworkResult.Loading())
            val response = userInfoRepository.getUserInfo()
            val errorObj = getErrorObject(response)

            handleResponse(response, errorObj)
        }
    }

    private fun getErrorObject(response: Response<UserInfoRemoteModel?>): Int {
        var errorObj = JSONObject()
        return if (errorObj.has(ERROR_OBJECT)) {
            errorObj = JSONObject(response.errorBody()?.string().toString())
            errorObj.getJSONObject(ERROR_OBJECT).optInt(STATUS_CODE)
        } else {
            0
        }
    }

    private suspend fun handleResponse(
        response: Response<UserInfoRemoteModel?>,
        errorObj: Int
    ) {
        if (response.isSuccessful && response.body() != null) {
            _getUserInfo.emit(NetworkResult.Success(response.body()))
        } else if (response.code() == StatusCode.INVALID_TOKEN.value || errorObj == StatusCode.INVALID_TOKEN.value) {
            _getUserInfo.emit(NetworkResult.Error(SOME_THING_WENT_WRONG))
        }
    }

    fun getUserId(id: String?) {
        viewModelScope.launch { _userID.emit(id) }
    }
}