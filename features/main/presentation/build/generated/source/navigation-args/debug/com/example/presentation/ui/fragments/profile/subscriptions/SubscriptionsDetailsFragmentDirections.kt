package com.example.presentation.ui.fragments.profile.subscriptions

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavDirections
import com.example.domain.models.BuySubscriptionResponse
import com.example.presentation.R
import java.io.Serializable
import java.lang.UnsupportedOperationException
import kotlin.Int
import kotlin.Suppress

public class SubscriptionsDetailsFragmentDirections private constructor() {
  private data class ActionSubscriptionsDetailsFragmentToWebViewFragment(
    public val Url: BuySubscriptionResponse,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_subscriptionsDetailsFragment_to_webViewFragment

    public override val arguments: Bundle
      @Suppress("CAST_NEVER_SUCCEEDS")
      get() {
        val result = Bundle()
        if (Parcelable::class.java.isAssignableFrom(BuySubscriptionResponse::class.java)) {
          result.putParcelable("Url", this.Url as Parcelable)
        } else if (Serializable::class.java.isAssignableFrom(BuySubscriptionResponse::class.java)) {
          result.putSerializable("Url", this.Url as Serializable)
        } else {
          throw UnsupportedOperationException(BuySubscriptionResponse::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
        return result
      }
  }

  public companion object {
    public fun actionSubscriptionsDetailsFragmentToWebViewFragment(Url: BuySubscriptionResponse):
        NavDirections = ActionSubscriptionsDetailsFragmentToWebViewFragment(Url)
  }
}
