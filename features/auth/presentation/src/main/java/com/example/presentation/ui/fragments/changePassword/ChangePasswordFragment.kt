package com.example.presentation.ui.fragments.changePassword

import android.annotation.SuppressLint
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.core_ui.base.BaseFragment
import com.example.core_ui.extensions.showShortToast
import com.example.core_ui.extensions.showSimpleDialog
import com.example.domain.models.ChangePasswordRequest
import com.example.presentation.databinding.FragmentChangePasswordBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChangePasswordFragment :
    BaseFragment<FragmentChangePasswordBinding, ChangePasswordViewModel>() {
    override fun getViewBinding() = FragmentChangePasswordBinding.inflate(layoutInflater)
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
            success = {
                findNavController().navigate(ChangePasswordFragmentDirections.actionChangePasswordFragmentToLoginFragment())
            },
            error = {
                showShortToast(it)
            }
        )
    }

    override fun onBackPressed() {
        findNavController().navigateUp()
    }
}