package com.example.presentation.ui.fragments.regist

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core_ui.base.BaseFragment
import com.example.presentation.R
import com.example.presentation.databinding.FragmentLoginBinding
import com.example.presentation.databinding.FragmentSignUpBinding
import com.example.presentation.ui.fragments.login.LoginViewModel
import java.util.regex.Pattern

class SignUpFragment :
    BaseFragment<FragmentSignUpBinding, SignUpViewModel>(R.layout.fragment_sign_up) {
    override val binding by viewBinding(FragmentSignUpBinding::bind)
    override val viewModel by viewModels<SignUpViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    override fun setupListeners() {
        super.setupListeners()

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.etPass.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val password = binding.etPass.text.toString()
                validatePass(password)
            }

            override fun afterTextChanged(s: Editable?) {}
        })

    }

    fun validatePass(password: String) {
        // if password length is less than 8
        if (password.length in 15 downTo 8) {
            binding.tvPassValidLength.setTextColor(resources.getColor(com.example.core_ui.R.color.correct_true_text))
        } else {
            binding.tvPassValidLength.setTextColor(resources.getColor(com.example.core_ui.R.color.correct_false_text))
        }
    }

    override fun onBackPressed() {
        findNavController().popBackStack()
    }
}