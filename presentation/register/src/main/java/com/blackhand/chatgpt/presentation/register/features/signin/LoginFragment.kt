package com.blackhand.chatgpt.presentation.register.features.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.blackhand.chatgpt.core.response.NetworkResult
import com.blackhand.chatgpt.domin.model.request.signin.LoginRequest
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
        initObserver()
        initListener()
    }

    private fun initObserver() {
        lifecycleScope.launch {
            loginViewModel.userLogin.observe(viewLifecycleOwner) { response ->
                when (response) {
                    is NetworkResult.Loading -> {
                        binding.lpiLoading.isVisible = true
                    }

                    is NetworkResult.Success -> {
                        Toast.makeText(requireContext(), "success login ", Toast.LENGTH_LONG)
                            .show()
                        binding.lpiLoading.isVisible = false
                    }

                    is NetworkResult.Error -> {
                        Toast.makeText(
                            requireContext(),
                            response.message.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    else -> {
                        binding.lpiLoading.isVisible = true
                    }
                }
            }
        }
    }

    private fun initListener() {
        onLoginClicked()
        binding.btnSignUp.setOnClickListener {
            findNavController().navigate(R.id.signUpFragment)
        }
    }

    private fun onLoginClicked() {
        binding.btnLogin.setOnClickListener {
            lifecycleScope.launch {
                val body: LoginRequest = LoginRequest("testUser@gma8il.com", "9981057402")
                loginViewModel.fetchUserInfo(body)
            }
        }
    }
}