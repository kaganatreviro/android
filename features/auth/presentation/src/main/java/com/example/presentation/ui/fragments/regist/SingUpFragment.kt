package com.example.presentation.ui.fragments.regist


import androidx.fragment.app.viewModels
import com.example.core_ui.base.BaseFragment
import com.example.presentation.databinding.FragmentSignUpBinding

class SingUpFragment : BaseFragment<FragmentSignUpBinding, SingUpViewModel>() {

    override val viewModel by viewModels<SingUpViewModel>()
    override fun getViewBinding() = FragmentSignUpBinding.inflate(layoutInflater)
}