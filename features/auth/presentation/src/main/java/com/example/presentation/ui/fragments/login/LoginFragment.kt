package com.example.presentation.ui.fragments.login

import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.core.Constants
import com.example.core_ui.base.BaseFragment
import com.example.core_ui.extensions.showShortToast
import com.example.presentation.R
import com.example.presentation.databinding.FragmentLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {
    override fun getViewBinding() = FragmentLoginBinding.inflate(layoutInflater)
    override val viewModel by viewModel<LoginViewModel>()

    override fun setupListeners() {
        binding.btnLogin.setOnClickListener {
            viewModel.userLogin(
                binding.etUserEmail.text.toString(),
                binding.etUserPassword.text.toString()
            )
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
            loading = {
                showDialog()
            },
            success = {
                hideDialog()
                navigateToMain()
            },
            error = {
                hideDialog()
                showShortToast(it)
            }
        )
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