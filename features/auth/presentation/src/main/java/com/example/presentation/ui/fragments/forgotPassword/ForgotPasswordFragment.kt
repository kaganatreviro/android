package com.example.presentation.ui.fragments.forgotPassword

import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core_ui.base.BaseFragment
import com.example.core_ui.extensions.gone
import com.example.core_ui.extensions.isNotEmpty
import com.example.core_ui.extensions.showShortToast
import com.example.core_ui.extensions.visible
import com.example.domain.models.ForgotPasswordRequest
import com.example.presentation.R
import com.example.presentation.databinding.FragmentForgotPasswordBinding

class ForgotPasswordFragment :
    BaseFragment<FragmentForgotPasswordBinding, ForgotPasswordViewModel>(R.layout.fragment_forgot_password) {
    override val binding by viewBinding(FragmentForgotPasswordBinding::bind)
    override val viewModel by viewModels<ForgotPasswordViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        initListeners()
    }

    private fun setupView() {
        binding.etInputEmail.addTextChangedListener(textWatcher)
    }

    private fun initListeners() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnSend.setOnClickListener {
            sendEmail()
        }
    }

    private fun isValidEmail(email: CharSequence?): Boolean {
        return !email.isNullOrBlank() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun sendEmail() {
        val param = binding.etInputEmail.text.toString()
        if (isValidEmail(param))
            viewModel.userForgotPassword(ForgotPasswordRequest(param))
        else
            binding.etInputEmail.error = "Wrong email"
    }

    override fun launchObservers() {
        viewModel.forgotPasswordState.spectateUiState(
            loading = {
                binding.progressBar.visible()
            },
            success = {
                binding.progressBar.gone()
                showShortToast("Success")
                findNavController().navigate(ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToConfirmPinFragment(it.email.toString()))
            },
            error = {
                binding.progressBar.gone()
                showShortToast(it)
            }
        )
    }

    override fun updateButtonState() {
        binding.btnSend.isEnabled = binding.etInputEmail.isNotEmpty()
    }

    override fun onBackPressed() {
        findNavController().popBackStack()
    }
}