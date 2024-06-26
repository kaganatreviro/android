package com.example.presentation.ui.fragments.profile.subscriptions

import android.os.Bundle
import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import com.example.domain.models.BuySubscriptionResponse
import java.io.Serializable
import java.lang.IllegalArgumentException
import java.lang.UnsupportedOperationException
import kotlin.Suppress
import kotlin.jvm.JvmStatic

public data class WebViewFragmentArgs(
  public val Url: BuySubscriptionResponse,
) : NavArgs {
  @Suppress("CAST_NEVER_SUCCEEDS")
  public fun toBundle(): Bundle {
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

  @Suppress("CAST_NEVER_SUCCEEDS")
  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    if (Parcelable::class.java.isAssignableFrom(BuySubscriptionResponse::class.java)) {
      result.set("Url", this.Url as Parcelable)
    } else if (Serializable::class.java.isAssignableFrom(BuySubscriptionResponse::class.java)) {
      result.set("Url", this.Url as Serializable)
    } else {
      throw UnsupportedOperationException(BuySubscriptionResponse::class.java.name +
          " must implement Parcelable or Serializable or must be an Enum.")
    }
    return result
  }

  public companion object {
    @JvmStatic
    @Suppress("DEPRECATION")
    public fun fromBundle(bundle: Bundle): WebViewFragmentArgs {
      bundle.setClassLoader(WebViewFragmentArgs::class.java.classLoader)
      val __Url : BuySubscriptionResponse?
      if (bundle.containsKey("Url")) {
        if (Parcelable::class.java.isAssignableFrom(BuySubscriptionResponse::class.java) ||
            Serializable::class.java.isAssignableFrom(BuySubscriptionResponse::class.java)) {
          __Url = bundle.get("Url") as BuySubscriptionResponse?
        } else {
          throw UnsupportedOperationException(BuySubscriptionResponse::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
        if (__Url == null) {
          throw IllegalArgumentException("Argument \"Url\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"Url\" is missing and does not have an android:defaultValue")
      }
      return WebViewFragmentArgs(__Url)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): WebViewFragmentArgs {
      val __Url : BuySubscriptionResponse?
      if (savedStateHandle.contains("Url")) {
        if (Parcelable::class.java.isAssignableFrom(BuySubscriptionResponse::class.java) ||
            Serializable::class.java.isAssignableFrom(BuySubscriptionResponse::class.java)) {
          __Url = savedStateHandle.get<BuySubscriptionResponse?>("Url")
        } else {
          throw UnsupportedOperationException(BuySubscriptionResponse::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
        if (__Url == null) {
          throw IllegalArgumentException("Argument \"Url\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"Url\" is missing and does not have an android:defaultValue")
      }
      return WebViewFragmentArgs(__Url)
    }
  }
}
