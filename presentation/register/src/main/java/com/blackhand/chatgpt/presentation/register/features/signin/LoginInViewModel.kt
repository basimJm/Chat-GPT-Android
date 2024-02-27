package com.blackhand.chatgpt.presentation.register.features.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blackhand.chatgpt.core.constants.Constant
import com.blackhand.chatgpt.core.constants.Constant.Companion.ERROR_OBJECT
import com.blackhand.chatgpt.core.constants.Constant.Companion.STATUS_CODE
import com.blackhand.chatgpt.core.enums.StatusCode
import com.blackhand.chatgpt.core.response.NetworkResult
import com.blackhand.chatgpt.domin.model.UserInfoRemoteModel
import com.blackhand.chatgpt.domin.model.request.signin.LoginRequest
import com.blackhand.chatgpt.domin.repo.UserInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginInViewModel @Inject constructor(private val userInfoRepository: UserInfoRepository) :
    ViewModel() {
    private val _userLogin = MutableLiveData<NetworkResult<UserInfoRemoteModel?>?>()
    val userLogin: LiveData<NetworkResult<UserInfoRemoteModel?>?> = _userLogin


    fun fetchUserInfo(loginRequest: LoginRequest) {
        viewModelScope.launch {
            _userLogin.postValue(NetworkResult.Loading())
            val response = userInfoRepository.loginUser(loginRequest)
            val errorMessage = parseJsonObject(response)
            handleResponse(response, errorMessage)
        }
    }

    private fun parseJsonObject(response: Response<UserInfoRemoteModel?>): Int {

        val errorBodyString = response.errorBody()?.string() ?: "{}"
        val errorObj = JSONObject(errorBodyString)
        if (errorObj.has(ERROR_OBJECT)) {
            return errorObj.getJSONObject(ERROR_OBJECT).optInt(STATUS_CODE)
        }
        return 0
    }

    private fun handleResponse(
        response: Response<UserInfoRemoteModel?>,
        errorObj: Int
    ) {
        if (response.isSuccessful && response.body() != null) {
            _userLogin.postValue(NetworkResult.Success(response.body()))
        } else if (errorObj == StatusCode.UNAUTHORIZED.value) {
            _userLogin.postValue(NetworkResult.Error(Constant.INVALID_EMAIL_OR_PASSWORD))
        }
    }

    fun resetResponseState() {
        _userLogin.postValue(NetworkResult.Idle())
    }
}