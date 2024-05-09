package com.example.presentation.ui.fragments.profile

import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core_ui.base.BaseFragment
import com.example.core_ui.extensions.gone
import com.example.core_ui.extensions.loadImageWithGlide
import com.example.core_ui.extensions.showShortToast
import com.example.core_ui.extensions.visible
import com.example.domain.models.User
import com.example.presentation.R
import com.example.presentation.databinding.FragmentProfileBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment :
    BaseFragment<FragmentProfileBinding, ProfileViewModel>(R.layout.fragment_profile) {

    override val binding by viewBinding(FragmentProfileBinding::bind)
    override val viewModel by viewModel<ProfileViewModel>()


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