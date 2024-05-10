package com.example.presentation.ui.fragments.forgotPassword

import android.annotation.SuppressLint
import android.content.Context
import android.os.CountDownTimer
import android.util.Patterns
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core_ui.base.BaseFragment
import com.example.core_ui.extensions.gone
import com.example.core_ui.extensions.isNotEmpty
import com.example.core_ui.extensions.showShortToast
import com.example.core_ui.extensions.showSimpleDialog
import com.example.core_ui.extensions.visible
import com.example.domain.models.ForgotPasswordRequest
import com.example.domain.models.ResetPasswordRequest
import com.example.presentation.R
import com.example.presentation.databinding.FragmentForgotPasswordBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Locale

class ForgotPasswordFragment :
    BaseFragment<FragmentForgotPasswordBinding, ForgotPasswordViewModel>(R.layout.fragment_forgot_password) {
    override val binding by viewBinding(FragmentForgotPasswordBinding::bind)
    override val viewModel by viewModel<ForgotPasswordViewModel>()
    private lateinit var emailArgs: String

    override fun setupListeners() {
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.btnSend.setOnClickListener {
            sendEmail()
            showShortToast("its worked")
        }

        binding.btnResentCode.setOnClickListener {
            setupConfirmPinView()
            sendEmail()
        }

        binding.etEnterPin.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                hideKeyBoard()
                confirmPin()
                return@setOnEditorActionListener true
            }
            false
        }
    }

    private fun isValidEmail(email: String?): Boolean {
        return !email.isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun sendEmail() {
        showShortToast("its peace")
        val param = binding.etInputEmail.text.toString().lowercase(Locale.getDefault())
        if (isValidEmail(param)) {
            showShortToast("its request")
            emailArgs = param
            viewModel.userForgotPassword(ForgotPasswordRequest(param))
        } else
            showSimpleDialog("Incorrect email format", "Please check your email and try again")
    }

    override fun launchObservers() {
        viewModel.forgotPasswordState.spectateUiState(
            loading = {
                binding.btnSend.text = ""
                binding.btnSend.isEnabled = false
                binding.progressBar.visible()
            },
            success = {
                binding.progressBar.gone()
                binding.btnSend.text = getString(com.example.core_ui.R.string.send)
                binding.btnSend.isEnabled = true
                binding.flResetPassword.isVisible = false
                binding.flPinConfirm.isVisible = true
                setupConfirmPinView()
            },
            error = {
                binding.progressBar.gone()
                binding.btnSend.text = getString(com.example.core_ui.R.string.send)
                binding.btnSend.isEnabled = true
                showSimpleDialog(it, "")
            }
        )

        viewModel.resetPasswordState.spectateUiState(
            loading = {
                binding.flProgressBar.visible()
            },
            success = {
                binding.flProgressBar.gone()
                findNavController().navigate(
                    ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToChangePasswordFragment(
                        emailArgs
                    )
                )
            },
            error = {
                binding.flProgressBar.gone()
                showSimpleDialog(it, "")
            }
        )
    }

    @SuppressLint("SetTextI18n")
    private fun setupTimer() {
        val timer = object : CountDownTimer(300000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minutesLeft = (millisUntilFinished / 1000) / 60 // Оставшиеся минуты
                val secondsLeft = (millisUntilFinished / 1000) % 60 // Оставшиеся секунды
                binding.tvResentCode.text =
                    resources.getString(com.example.core_ui.R.string.resent_code_timer) + " " + String.format(
                        "%02d:%02d",
                        minutesLeft,
                        secondsLeft
                    )
            }

            override fun onFinish() {
                binding.tvResentCode.isVisible = false
                binding.btnResentCode.isVisible = true
            }
        }
        timer.start()
    }

    @SuppressLint("SetTextI18n")
    private fun setupConfirmPinView() {
        setupTimer()
        binding.tvTitlePin.text =
            getString(com.example.core_ui.R.string.enter_pin_title) + " " + emailArgs
        showKeyBoard()
    }

    private fun confirmPin() {
        if (!isPinViewIsEmpty()) {
            showSimpleDialog("Empty TextField", "Please, check your email and enter pinCode")
        } else {
            val params = ResetPasswordRequest(emailArgs, binding.etEnterPin.text.toString())
            viewModel.userResetPassword(params)
        }
    }

    private fun isPinViewIsEmpty(): Boolean {
        return binding.etEnterPin.isNotEmpty()
    }

    private fun showKeyBoard() {
        binding.etEnterPin.requestFocus()
        val mm =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        mm.showSoftInput(binding.etEnterPin, 0)
    }

    private fun hideKeyBoard() {
        val mm =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        mm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    override fun onBackPressed() {
        findNavController().navigateUp()
    }
}