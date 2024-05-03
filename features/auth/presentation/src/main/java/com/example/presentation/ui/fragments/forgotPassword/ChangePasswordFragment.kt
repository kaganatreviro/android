package com.example.presentation.ui.fragments.forgotPassword

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core_ui.base.BaseFragment
import com.example.presentation.R
import com.example.presentation.databinding.FragmentChangePasswordBinding

class ChangePasswordFragment : BaseFragment<FragmentChangePasswordBinding, ForgotPasswordViewModel>(R.layout.fragment_change_password) {
    override val binding by viewBinding(FragmentChangePasswordBinding::bind)
    override val viewModel by viewModels<ForgotPasswordViewModel>()

}