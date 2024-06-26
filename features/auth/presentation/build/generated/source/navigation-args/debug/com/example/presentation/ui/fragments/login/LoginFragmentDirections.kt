package com.example.presentation.ui.fragments.login

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.presentation.R

public class LoginFragmentDirections private constructor() {
  public companion object {
    public fun actionLoginFragmentToSignUpFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_loginFragment_to_signUpFragment)

    public fun actionLoginFragmentToForgotPasswordFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_loginFragment_to_forgotPasswordFragment)

    public fun actionLoginFragmentToMainGraph(): NavDirections =
        ActionOnlyNavDirections(R.id.action_loginFragment_to_main_graph)
  }
}
