package com.example.presentation.ui.fragments.changePassword

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.presentation.R

public class ChangePasswordFragmentDirections private constructor() {
  public companion object {
    public fun actionChangePasswordFragmentToLoginFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_changePasswordFragment_to_loginFragment)
  }
}
