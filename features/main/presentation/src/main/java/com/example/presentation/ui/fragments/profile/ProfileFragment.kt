package com.example.presentation.ui.fragments.profile

import androidx.navigation.fragment.findNavController
import com.example.core_ui.base.BaseFragment
import com.example.core_ui.extensions.loadImageWithGlide
import com.example.core_ui.extensions.showShortToast
import com.example.domain.models.User
import com.example.presentation.databinding.FragmentProfileBinding
import com.example.presentation.ui.fragments.MainViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class ProfileFragment :
    BaseFragment<FragmentProfileBinding, ProfileViewModel>() {
    override fun getViewBinding() = FragmentProfileBinding.inflate(layoutInflater)
    override val viewModel by activityViewModel<ProfileViewModel>()
    private val mainViewModel by activityViewModel<MainViewModel>()

    override fun setupListeners() {
        binding.btnLogout.setOnClickListener {
            mainViewModel.logout()
        }
        binding.containerProfile.setOnClickListener {
            navigateToEditProfile()
        }
    }

    override fun launchObservers() {
        viewModel.userState.spectateUiState(
            success = { user ->
                setUserData(user)
            },
            error = {
                showShortToast(it)
            }
        )
    }

    private fun setUserData(user: User): Unit = with(binding) {
        user.avatar?.let { ivUserAvatar.loadImageWithGlide(it) }
        tvUserName.text = user.name
        tvUserEmail.text = user.email
    }

    private fun navigateToEditProfile() {
        findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToEditProfileFragment())
    }
}