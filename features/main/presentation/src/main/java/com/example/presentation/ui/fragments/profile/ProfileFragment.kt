package com.example.presentation.ui.fragments.profile

import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core.Constants.DEEPLINK_NAV_TO_AUTH_MODULE
import com.example.core_ui.base.BaseFragment
import com.example.core_ui.extensions.gone
import com.example.core_ui.extensions.loadImageWithGlide
import com.example.core_ui.extensions.showShortToast
import com.example.core_ui.extensions.visible
import com.example.domain.models.User
import com.example.presentation.R
import com.example.presentation.databinding.FragmentHomeBinding
import com.example.presentation.databinding.FragmentProfileBinding
import com.example.presentation.ui.fragments.MainViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment :
    BaseFragment<FragmentProfileBinding, ProfileViewModel>() {
    override fun getViewBinding() = FragmentProfileBinding.inflate(layoutInflater)
    override val viewModel by viewModel<ProfileViewModel>()
    private val mainViewModel by activityViewModel<MainViewModel>()

    override fun setupListeners() {
        binding.btnLogout.setOnClickListener {
            mainViewModel.logout()
        }
    }

    override fun launchObservers() {
        viewModel.userState.spectateUiState(
            loading = {
                binding.progressBar.visible()
            },
            success = { user ->
                binding.progressBar.gone()
                setUserData(user)
            },
            error = {
                binding.progressBar.gone()
                showShortToast(it)
            }
        )
    }

    private fun setUserData(user: User): Unit = with(binding) {
        user.avatar?.let { ivUserAvatar.loadImageWithGlide(it) }
        tvUserName.text = user.name
        tvUserEmail.text = user.email
    }
}