package com.example.presentation.ui.fragments.profile

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.core.Constants
import com.example.core.either.NetworkError
import com.example.core_ui.base.BaseFragment
import com.example.core_ui.base.BaseFragment.SubscriptionData.subscriptionEndDate
import com.example.core_ui.base.BaseFragment.SubscriptionData.subscriptionStatus
import com.example.core_ui.base.BaseFragment.SubscriptionData.subscriptionsPlanId
import com.example.core_ui.base.BaseFragment.SubscriptionData.subscriptionsPlanName
import com.example.core_ui.extensions.loadImageWithGlide
import com.example.core_ui.extensions.showShortToast
import com.example.domain.models.User
import com.example.presentation.R
import com.example.presentation.databinding.FragmentProfileBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

class ProfileFragment :
    BaseFragment<FragmentProfileBinding, ProfileViewModel>() {
    override fun getViewBinding() = FragmentProfileBinding.inflate(layoutInflater)
    override val viewModel by activityViewModel<ProfileViewModel>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun initialize() {
        if (subscriptionStatus) {
            binding.containerSubscription.isVisible = true
            binding.containerSubscriptionEmpty.isVisible = false
            setSubscriptionData()
        } else {
            binding.containerSubscription.isVisible = false
            binding.containerSubscriptionEmpty.isVisible = true
            checkSubscriptionStatus()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    private fun setSubscriptionData() = with(binding) {
        tvSubsName.text = subscriptionsPlanName
        tvSubsDuration.text = "1 membership"
        val dateTime = OffsetDateTime.parse(subscriptionEndDate)
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val subscriptionEndDateTime = dateTime.format(formatter)
        tvSubsDeadline.text = "Valid through $subscriptionEndDateTime"
    }

    private fun checkSubscriptionStatus(){
        viewModel.checkSubscriptionStatus()
    }

    override fun setupListeners() {
        binding.btnEditProfile.setOnClickListener {
            navigateToEditProfile()
        }

        binding.containerSubscription.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToSubscriptionsDetailsFragment())
        }

        binding.containerSubscriptionEmpty.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToSubscriptionsDetailsFragment())
        }

        binding.btnLogout.setOnClickListener {
            showLogoutDialog()
        }
    }

    private fun showLogoutDialog() {
        MaterialAlertDialogBuilder(
            requireContext(),
            androidx.appcompat.R.style.AlertDialog_AppCompat
        )
            .setMessage("Are you sure you want to Log Out?")
            .setTitle("Exit")
            .setPositiveButton("Yes") { dialog, which ->
                dialog.dismiss()
                viewModel.logout()
            }
            .setNegativeButton("Cancel") { dialog, which ->
                dialog.dismiss()
            }
            .show()
    }

    override fun launchObservers() {
        viewModel.userState.spectateNewUiState (
            success = { user ->
                setUserData(user)
            },
            error = {
                if (it is NetworkError.Api)
                    showShortToast(it.error)
            }
        )

        viewModel.userLogoutState.spectateUiState(
            success = {
                logout()
                viewModel.resetUserLogoutState()
            },
            error = {
                showShortToast(it)
            }
        )

        viewModel.checkSubscriptionStatusState.spectateUiState(
            showLoader = false,
            success = {
                subscriptionStatus = it.isActive
                subscriptionsPlanName = it.plan.name
                subscriptionEndDate = it.endDate
                subscriptionsPlanId = it.plan.id
            },
            error = {
                subscriptionStatus = false
                subscriptionsPlanName = ""
                subscriptionEndDate = ""
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

    private fun logout() {
        val request = NavDeepLinkRequest.Builder
            .fromUri(Constants.Deeplink.DEEPLINK_NAV_TO_AUTH_MODULE.toUri())
            .build()
        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.nav_graph_main, false)
            .build()
        findNavController().navigate(request, navOptions)
    }
}