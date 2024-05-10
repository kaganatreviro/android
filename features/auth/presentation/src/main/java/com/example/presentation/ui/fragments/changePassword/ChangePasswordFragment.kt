package com.example.presentation.ui.fragments.changePassword

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core_ui.base.BaseFragment
import com.example.core_ui.extensions.gone
import com.example.core_ui.extensions.showShortToast
import com.example.core_ui.extensions.showSimpleDialog
import com.example.core_ui.extensions.visible
import com.example.domain.models.ChangePasswordRequest
import com.example.presentation.R
import com.example.presentation.databinding.FragmentChangePasswordBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChangePasswordFragment :
    BaseFragment<FragmentChangePasswordBinding, ChangePasswordViewModel>(R.layout.fragment_change_password) {
    override val binding by viewBinding(FragmentChangePasswordBinding::bind)
    override val viewModel by viewModel<ChangePasswordViewModel>()
    private val args: ChangePasswordFragmentArgs by navArgs()

    override fun setupListeners() {

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.etUserPassword.addTextChangedListener {
            validatePass(it.toString())
        }

        binding.btnDone.setOnClickListener {
            changePassword()
        }
    }

    private fun changePassword() {
        if (isTextFieldsIsEmpty()) {
            showSimpleDialog("Empty textField!", "Please, fill all textField")
        } else {
            val params = ChangePasswordRequest(
                args.toString(),
                binding.etUserPassword.text.toString(),
                binding.etRePass.text.toString()
            )
            viewModel.userChangePassword(params)
        }
    }

    @SuppressLint("ResourceAsColor")
    fun validatePass(password: String) {
        if (password.isEmpty()) {
            binding.tvPassValidLength.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    com.example.core_ui.R.color.correct_false_text
                )
            )
        } else {
            binding.tvPassValidLength.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    com.example.core_ui.R.color.correct_true_text
                )
            )
        }
        if (password.length < 8) {
            binding.tvPassValidUpLowCase.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    com.example.core_ui.R.color.correct_false_text
                )
            )
        } else {
            binding.tvPassValidUpLowCase.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    com.example.core_ui.R.color.correct_true_text
                )
            )
        }
    }

    private fun isTextFieldsIsEmpty(): Boolean {
        return binding.etUserPassword.text.isNullOrEmpty() && binding.etRePass.text.isNullOrEmpty()
    }

    override fun launchObservers() {
        viewModel.changePasswordState.spectateUiState(
            loading = {
                binding.btnDone.text = ""
                binding.btnDone.isEnabled = false
                binding.progressBar.visible()
            },
            success = {
                binding.progressBar.gone()
                findNavController().navigate(ChangePasswordFragmentDirections.actionChangePasswordFragmentToLoginFragment())
            },
            error = {
                binding.progressBar.gone()
                binding.btnDone.isEnabled = true
                binding.btnDone.text = getString(com.example.core_ui.R.string.done)
                showShortToast(it)
            }
        )
    }

    override fun onBackPressed() {
        findNavController().navigateUp()
    }
}