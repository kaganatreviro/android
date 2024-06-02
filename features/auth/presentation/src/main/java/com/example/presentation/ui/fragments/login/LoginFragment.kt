package com.example.presentation.ui.fragments.login

import android.util.Patterns
import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.core.Constants
import com.example.core_ui.base.BaseFragment
import com.example.core_ui.extensions.showShortToast
import com.example.core_ui.extensions.showSimpleDialog
import com.example.presentation.R
import com.example.presentation.databinding.FragmentLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {
    override fun getViewBinding() = FragmentLoginBinding.inflate(layoutInflater)
    override val viewModel by viewModel<LoginViewModel>()

    override fun setupListeners() {
        binding.btnLogin.setOnClickListener {
            makeLogin()
        }
        binding.tvNoneAccount.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToSignUpFragment())
        }
        binding.tvResetPass.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment())
        }
    }

    override fun launchObservers() {
        viewModel.loginState.spectateUiState(
            success = {
                navigateToMain()
            },
            error = {
                showShortToast(it)
            }
        )
    }

    private fun isValidEmail(email: String?): Boolean {
        return !email.isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun makeLogin() {
        if (isValidEmail(binding.etUserEmail.text.toString())) {
            viewModel.userLogin(
                binding.etUserEmail.text.toString().lowercase(),
                binding.etUserPassword.text.toString()
            )
        } else showSimpleDialog("Error", "Incorrect email")
    }

    private fun navigateToMain() {
        val request = NavDeepLinkRequest.Builder
            .fromUri(Constants.Deeplink.DEEPLINK_NAV_TO_MAIN_MODULE.toUri())
            .build()
        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.nav_graph_auth, false)
            .build()
        findNavController().navigate(request, navOptions)
    }
}