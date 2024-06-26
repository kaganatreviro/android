package com.example.presentation.ui.fragments.profile

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.presentation.R

public class ProfileFragmentDirections private constructor() {
  public companion object {
    public fun actionProfileFragmentToEditProfileFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_profileFragment_to_editProfileFragment)

    public fun actionProfileFragmentToSubscriptionsDetailsFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_profileFragment_to_subscriptionsDetailsFragment)
  }
}
