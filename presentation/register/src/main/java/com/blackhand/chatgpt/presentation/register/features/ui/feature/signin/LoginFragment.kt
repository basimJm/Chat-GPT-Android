package com.blackhand.chatgpt.presentation.register.features.ui.feature.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.blackhand.chatgpt.common.sharedui.dialog.ErrorDialog
import com.blackhand.chatgpt.core.constants.Constant.Companion.USER_TOKEN
import com.blackhand.chatgpt.core.response.NetworkResult
import com.blackhand.chatgpt.core.utils.MostUsedUtils.editTextValidation
import com.blackhand.chatgpt.core.utils.MostUsedUtils.hideKeyBoard
import com.blackhand.chatgpt.core.utils.SharedPref.init
import com.blackhand.chatgpt.core.utils.SharedPref.putString
import com.blackhand.chatgpt.domin.model.UserInfoRemoteModel
import com.blackhand.chatgpt.domin.model.request.signin.LoginRequestModel
import com.blackhand.chatgpt.presentation.register.R
import com.blackhand.chatgpt.presentation.register.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val loginViewModel: LoginInViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPreference()
        initListener()
        initObserver()
    }

    private fun initPreference() {
        init(requireContext())
    }

    private fun initObserver() {
        lifecycleScope.launch {
            loginViewModel.userLogin.observe(viewLifecycleOwner) { response ->
                handleResponseState(response)
            }
        }
    }

    private fun handleResponseState(response: NetworkResult<UserInfoRemoteModel?>?) {
        when (response) {
            is NetworkResult.Loading -> {
                binding.lpiLoading.isVisible = true
            }

            is NetworkResult.Success -> {
                binding.lpiLoading.isVisible = false
                saveToken(response.data?.token.toString())
            }

            is NetworkResult.Error -> {
                errorDialog(response.message.toString())
                binding.lpiLoading.isVisible = false
            }

            is NetworkResult.Idle -> {
                binding.lpiLoading.isVisible = false
            }

            else -> {}
        }
    }

    private fun initListener() {
        onLoginClicked()
        onSignUpClicked()
    }

    private fun onSignUpClicked() {
        binding.btnSignUp.setOnClickListener {
            findNavController().navigate(R.id.signUpFragment)
        }
    }

    private fun onLoginClicked() {
        binding.btnLogin.setOnClickListener {
            val isValid = editTextValidation(editTextFieldsList())
            if (isValid) {
                hideKeyBoard(activity, requireView())
                lifecycleScope.launch {

                    loginViewModel.fetchUserInfo(fillLoginBody())
                }
            }
        }
    }

    private fun errorDialog(message: String) {
        ErrorDialog.showErrorDialog(
            requireContext(), getString(com.blackhand.chatgpt.common.sharedui.R.string.error),
            message,
            getString(com.blackhand.chatgpt.common.sharedui.R.string.try_again)
        )
    }

    private fun saveToken(token: String) {
        putString(USER_TOKEN, token)
    }

    private fun fillLoginBody(): LoginRequestModel {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        return LoginRequestModel(email, password)
    }

    private fun editTextFieldsList(): List<AppCompatEditText> {
        return listOf(binding.etEmail, binding.etPassword)
    }

    override fun onPause() {
        super.onPause()
        loginViewModel.resetResponseState()
    }
}