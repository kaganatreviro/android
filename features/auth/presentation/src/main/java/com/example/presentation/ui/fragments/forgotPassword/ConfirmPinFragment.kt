package com.example.presentation.ui.fragments.forgotPassword

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isEmpty
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core_ui.base.BaseFragment
import com.example.domain.models.ResetPasswordRequest
import com.example.presentation.R
import com.example.presentation.databinding.FragmentConfirmPinBinding

class ConfirmPinFragment :
    BaseFragment<FragmentConfirmPinBinding, ForgotPasswordViewModel>(R.layout.fragment_confirm_pin) {

    override val binding by viewBinding(FragmentConfirmPinBinding::bind)
    override val viewModel by viewModels<ForgotPasswordViewModel>()
    private val args: ConfirmationPinFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showKeyBoard()
        setupTimer()
        initListeners()
    }

    private fun initListeners() {
        binding.btnResentCode.setOnClickListener {

        }
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnDone.setOnClickListener {

        }
    }

    @SuppressLint("SetTextI18n")
    private fun setupTimer() {
        val timer = object : CountDownTimer(59000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.tvResentCode.text =
                    resources.getString(com.example.core_ui.R.string.resent_code_timer) + " " + millisUntilFinished.toString()
            }

            override fun onFinish() {
                binding.tvResentCode.isVisible = false
                binding.btnResentCode.isVisible = true
            }
        }
        timer.start()
    }

    override fun onBackPressed() {
        findNavController().popBackStack()
    }

    private fun configCode(){
        val params = ResetPasswordRequest("", binding.pinview.value)
        viewModel.userResetPassword(params)
    }

    private fun isEmptyPinView(): Boolean{
        return binding.pinview.isEmpty()
    }

    private fun showKeyBoard() {
        binding.pinview.requestFocus()
        val mm =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        mm.showSoftInput(binding.pinview, 0)
    }
}