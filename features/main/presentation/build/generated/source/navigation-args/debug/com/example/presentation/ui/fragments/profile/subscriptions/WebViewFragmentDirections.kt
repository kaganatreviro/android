package com.example.presentation.ui.fragments.profile.subscriptions

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.presentation.R

public class WebViewFragmentDirections private constructor() {
  public companion object {
    public fun actionWebViewFragmentToProfileFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_webViewFragment_to_profileFragment)
  }
}
