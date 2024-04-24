package com.example.presentation.ui.fragments.login

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core_ui.base.BaseFragment
import com.example.presentation.R
import com.example.presentation.databinding.FragmentLoginBinding

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(R.layout.fragment_login) {

    override val binding by viewBinding(FragmentLoginBinding::bind)
    override val viewModel by viewModels<LoginViewModel>()


}