package com.blackhand.chatgpt.presentation.register.features.ui.feature.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blackhand.chatgpt.core.constants.Constant.Companion.CREATE_USER_ERROR
import com.blackhand.chatgpt.core.constants.Constant.Companion.DEFAULT_VALUE
import com.blackhand.chatgpt.core.constants.Constant.Companion.ERROR_OBJECT
import com.blackhand.chatgpt.core.constants.Constant.Companion.NESTED_JSON_ARRAY_ERROR
import com.blackhand.chatgpt.core.constants.Constant.Companion.PHONE_NUMBER_ERROR
import com.blackhand.chatgpt.core.response.NetworkResult
import com.blackhand.chatgpt.domin.model.UserInfoRemoteModel
import com.blackhand.chatgpt.domin.model.request.signup.CreateUserRequestModel
import com.blackhand.chatgpt.domin.repo.UserInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val userInfoRepository: UserInfoRepository) :
    ViewModel() {
    private val _sighUpUser = MutableLiveData<NetworkResult<UserInfoRemoteModel?>>()
    val signUpUser: LiveData<NetworkResult<UserInfoRemoteModel?>> = _sighUpUser

    fun createUser(createUserRequest: CreateUserRequestModel) {
        viewModelScope.launch {
            _sighUpUser.postValue(NetworkResult.Loading())
            val response = userInfoRepository.sighUpUser(createUserRequest)
            handleResponse(response)
        }
    }

    private fun handleResponse(response: Response<UserInfoRemoteModel?>) {
        if (response.isSuccessful && response.body() != null) {
            _sighUpUser.postValue(NetworkResult.Success(response.body()))
        } else if (response.errorBody() != null) {
            _sighUpUser.postValue(NetworkResult.Error(getErrorJsonObject(response)))
        }
    }

    private fun getErrorJsonObject(response: Response<UserInfoRemoteModel?>): String {
        val body = response.errorBody()?.string()
        val errorObj = JSONObject(body.toString())
        return if (errorObj.has(NESTED_JSON_ARRAY_ERROR)) {
            errorObj.getJSONObject(NESTED_JSON_ARRAY_ERROR).getJSONArray(NESTED_JSON_ARRAY_ERROR)
                .getJSONObject(0)
                .getString(CREATE_USER_ERROR)
        } else if (errorObj.has(ERROR_OBJECT)) {
            PHONE_NUMBER_ERROR
        } else {
            DEFAULT_VALUE
        }
    }
}