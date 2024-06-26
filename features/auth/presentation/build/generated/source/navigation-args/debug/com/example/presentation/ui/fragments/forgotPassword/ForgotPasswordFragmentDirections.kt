package com.example.presentation.ui.fragments.forgotPassword

import android.os.Bundle
import androidx.navigation.NavDirections
import com.example.presentation.R
import kotlin.Int
import kotlin.String

public class ForgotPasswordFragmentDirections private constructor() {
  private data class ActionForgotPasswordFragmentToChangePasswordFragment(
    public val email: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_forgotPasswordFragment_to_changePasswordFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("email", this.email)
        return result
      }
  }

  public companion object {
    public fun actionForgotPasswordFragmentToChangePasswordFragment(email: String): NavDirections =
        ActionForgotPasswordFragmentToChangePasswordFragment(email)
  }
}
