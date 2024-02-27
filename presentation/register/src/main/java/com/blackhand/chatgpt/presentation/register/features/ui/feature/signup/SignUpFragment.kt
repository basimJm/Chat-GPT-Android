package com.blackhand.chatgpt.presentation.register.features.ui.feature.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.blackhand.chatgpt.common.sharedui.dialog.ErrorDialog
import com.blackhand.chatgpt.core.constants.Constant
import com.blackhand.chatgpt.core.response.NetworkResult
import com.blackhand.chatgpt.core.utils.MostUsedUtils
import com.blackhand.chatgpt.core.utils.SharedPref
import com.blackhand.chatgpt.core.utils.SharedPref.init
import com.blackhand.chatgpt.domin.model.UserInfoRemoteModel
import com.blackhand.chatgpt.domin.model.request.signup.CreateUserRequestModel
import com.blackhand.chatgpt.presentation.register.databinding.FragmentSignUpBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private val signUpViewModel: SignUpViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
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
            signUpViewModel.signUpUser.observe(viewLifecycleOwner) { response ->
                handleResponse(response)
            }
        }
    }

    private fun handleResponse(response: NetworkResult<UserInfoRemoteModel?>?) {
        when (response) {
            is NetworkResult.Success -> {
                Toast.makeText(requireContext(), response.data?.token, Toast.LENGTH_SHORT).show()
                saveToken(response.data?.token.toString())
            }

            is NetworkResult.Error -> {
                Toast.makeText(requireContext(), response.message.toString(), Toast.LENGTH_SHORT)
                    .show()
                errorDialog(response.message.toString())
            }


            else -> {}
        }
    }

    private fun initListener() {
        onLoginClicked()
    }

    private fun onLoginClicked() {
        binding.btnSignUp.setOnClickListener {
            val isValid = MostUsedUtils.editTextValidation(editTextFieldsList())
            if (isValid) {
                MostUsedUtils.hideKeyBoard(activity, requireView())
                lifecycleScope.launch {

                    signUpViewModel.createUser(fillSignUpBody())
                }
            }
        }
    }

    private fun saveToken(token: String) {
        SharedPref.putString(Constant.USER_TOKEN, token)
    }

    private fun fillSignUpBody(): CreateUserRequestModel {
        val name = binding.etEmail.text.toString()
        val email = binding.etEmail.text.toString()
        val phone = binding.etPhoneNumber.text.toString()
        val password = binding.etPassword.text.toString()
        val confirmPassword = binding.etConfirmPassword.text.toString()

        return CreateUserRequestModel(name, email, phone, password, confirmPassword)
    }

    private fun editTextFieldsList(): List<AppCompatEditText> {
        return listOf(
            binding.etName,
            binding.etEmail,
            binding.etPhoneNumber,
            binding.etPassword,
            binding.etConfirmPassword
        )
    }

    private fun errorDialog(message: String) {
        ErrorDialog.showErrorDialog(
            requireContext(), getString(com.blackhand.chatgpt.common.sharedui.R.string.error),
            message,
            getString(com.blackhand.chatgpt.common.sharedui.R.string.try_again)
        )
    }
}