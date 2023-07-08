package com.example.task1.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.task1.R
import com.example.task1.databinding.FragmentLoginBinding
import com.example.task1.ui.base.BaseFragment
import com.example.task1.ui.helper.NavigationCommand
import com.example.task1.ui.viewmodels.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment() {
    private lateinit var binding: FragmentLoginBinding

    override val _viewModel by activityViewModels<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_login, container, false
            )
        initActions()
        return binding.root
    }

    private fun initActions() {
        with(binding) {
            btLogin.setOnClickListener {
                when {
                    binding.etEmail.text.isNullOrEmpty() -> binding.inputEmailLayout.error =
                        getString(R.string.error_empty_email_message)

                    binding.etPassword.text.isNullOrEmpty() -> binding.inputPasswordLayout.error =
                        getString(R.string.error_empty_password_message)

                    else -> {
                        loginPrefs.save(true)
                        _viewModel.navigationCommand.postValue(
                            NavigationCommand.Back
                        )
                    }
                }
            }
            binding.etEmail.doOnTextChanged { _, _, _, _ ->
                binding.inputEmailLayout.error = null
            }

            binding.etPassword.doOnTextChanged { _, _, _, _ ->
                binding.inputPasswordLayout.error = null
            }
        }
    }
}