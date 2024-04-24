package com.example.presentation.ui.fragments.regist


import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core_ui.base.BaseFragment
import com.example.presentation.R
import com.example.presentation.databinding.FragmentSingUpBinding

class SingUpFragment : BaseFragment<FragmentSingUpBinding, SingUpViewModel>(R.layout.fragment_sing_up) {

    override val binding by viewBinding(FragmentSingUpBinding::bind)
    override val viewModel by viewModels<SingUpViewModel>()
}