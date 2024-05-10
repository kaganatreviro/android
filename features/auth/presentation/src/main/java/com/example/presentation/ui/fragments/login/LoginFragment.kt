package com.example.presentation.ui.fragments.login

import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core.Constants.DEEPLINK_NAV_TO_MAIN_MODULE
import com.example.core_ui.base.BaseFragment
import com.example.core_ui.extensions.gone
import com.example.core_ui.extensions.showShortToast
import com.example.core_ui.extensions.visible
import com.example.presentation.R
import com.example.presentation.databinding.FragmentLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(R.layout.fragment_login) {

    override val binding by viewBinding(FragmentLoginBinding::bind)
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
                binding.progressBar.visible()
                binding.btnLogin.isEnabled = false
                binding.btnLogin.text = ""
            },
            success = {
                binding.progressBar.gone()
                binding.btnLogin.isEnabled = true
                binding.btnLogin.text = getString(com.example.core_ui.R.string.sign_in)
                navigateToMain()
            },
            error = {
                binding.progressBar.gone()
                binding.btnLogin.isEnabled = true
                binding.btnLogin.text = getString(com.example.core_ui.R.string.sign_in)
                showShortToast(it)
            }
        )
    }

    private fun navigateToMain() {
        val request = NavDeepLinkRequest.Builder
            .fromUri(DEEPLINK_NAV_TO_MAIN_MODULE.toUri())
            .build()
        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.nav_graph_auth, false)
            .build()
        findNavController().navigate(request, navOptions)
    }
}