package com.example.presentation.ui.fragments.confirmPin

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
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core_ui.base.BaseFragment
import com.example.core_ui.extensions.gone
import com.example.core_ui.extensions.showShortToast
import com.example.core_ui.extensions.visible
import com.example.domain.models.ResetPasswordRequest
import com.example.presentation.R
import com.example.presentation.databinding.FragmentConfirmPinBinding

class ConfirmPinFragment :
    BaseFragment<FragmentConfirmPinBinding, ConfirmPinViewModel>(R.layout.fragment_confirm_pin) {

    override val binding by viewBinding(FragmentConfirmPinBinding::bind)
    override val viewModel by viewModels<ConfirmPinViewModel>()
    private val args by navArgs<ConfirmPinFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupView()
        initListeners()
    }

    @SuppressLint("SetTextI18n")
    private fun setupView(){
        showKeyBoard()
        setupTimer()

        binding.tvTitle.text = resources.getString(com.example.core_ui.R.string.enter_pin_title) + " ramazanmarov@gmail.com"
        binding.btnDone.isEnabled = isEmptyPinView()
    }

    private fun initListeners() {
        binding.btnResentCode.setOnClickListener {

        }
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnDone.setOnClickListener {
            configCode()
        }
    }

    override fun launchObservers() {
        viewModel.resetPasswordState.spectateUiState(
            loading = {
                binding.progressBar.visible()
            },
            success = {
                binding.progressBar.gone()
                showShortToast("Success")
                findNavController().navigate(ConfirmPinFragmentDirections.actionConfirmPinFragmentToChangePasswordFragment(args.email))
            },
            error = {
                binding.progressBar.gone()
                showShortToast(it)
            }
        )
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
        val params = ResetPasswordRequest(args.email, binding.pinview.value)
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