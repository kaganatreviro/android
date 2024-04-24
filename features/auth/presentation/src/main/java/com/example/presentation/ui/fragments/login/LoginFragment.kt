package com.example.presentation.ui.fragments.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core_ui.base.BaseFragment
import com.example.presentation.R
import com.example.presentation.databinding.FragmentLoginBinding
import com.example.presentation.ui.fragments.regist.SingUpFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(R.layout.fragment_login) {

    override val binding by viewBinding(FragmentLoginBinding::bind)
    override val viewModel by viewModels<LoginViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() = with(binding){
        btnEnter.setOnClickListener {
            if (checkEmptyLines()){
                showSimpleDialog()
            }else{
                //Todo navigate
            }
        }
    }

    private fun showSimpleDialog(){
        MaterialAlertDialogBuilder(requireContext(),
            androidx.appcompat.R.style.AlertDialog_AppCompat)
            .setMessage("Проверьте и заполните все поля")
            .setTitle("Поле не должно быть пустым!")
            .setPositiveButton("OK") { dialog, which ->
                dialog.dismiss()
            }
            .show()
    }

    private fun checkEmptyLines(): Boolean{
        return binding.etInputLogin.text.isNullOrEmpty()||
                binding.etPass.text.isNullOrEmpty()

    }
}