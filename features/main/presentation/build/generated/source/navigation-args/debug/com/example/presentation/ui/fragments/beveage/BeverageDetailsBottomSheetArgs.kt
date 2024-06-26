package com.example.presentation.ui.fragments.beveage

import android.os.Bundle
import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import com.example.domain.models.Beverage
import java.io.Serializable
import java.lang.IllegalArgumentException
import java.lang.UnsupportedOperationException
import kotlin.Suppress
import kotlin.jvm.JvmStatic

public data class BeverageDetailsBottomSheetArgs(
  public val beverage: Beverage,
) : NavArgs {
  @Suppress("CAST_NEVER_SUCCEEDS")
  public fun toBundle(): Bundle {
    val result = Bundle()
    if (Parcelable::class.java.isAssignableFrom(Beverage::class.java)) {
      result.putParcelable("beverage", this.beverage as Parcelable)
    } else if (Serializable::class.java.isAssignableFrom(Beverage::class.java)) {
      result.putSerializable("beverage", this.beverage as Serializable)
    } else {
      throw UnsupportedOperationException(Beverage::class.java.name +
          " must implement Parcelable or Serializable or must be an Enum.")
    }
    return result
  }

  @Suppress("CAST_NEVER_SUCCEEDS")
  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    if (Parcelable::class.java.isAssignableFrom(Beverage::class.java)) {
      result.set("beverage", this.beverage as Parcelable)
    } else if (Serializable::class.java.isAssignableFrom(Beverage::class.java)) {
      result.set("beverage", this.beverage as Serializable)
    } else {
      throw UnsupportedOperationException(Beverage::class.java.name +
          " must implement Parcelable or Serializable or must be an Enum.")
    }
    return result
  }

  public companion object {
    @JvmStatic
    @Suppress("DEPRECATION")
    public fun fromBundle(bundle: Bundle): BeverageDetailsBottomSheetArgs {
      bundle.setClassLoader(BeverageDetailsBottomSheetArgs::class.java.classLoader)
      val __beverage : Beverage?
      if (bundle.containsKey("beverage")) {
        if (Parcelable::class.java.isAssignableFrom(Beverage::class.java) ||
            Serializable::class.java.isAssignableFrom(Beverage::class.java)) {
          __beverage = bundle.get("beverage") as Beverage?
        } else {
          throw UnsupportedOperationException(Beverage::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
        if (__beverage == null) {
          throw IllegalArgumentException("Argument \"beverage\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"beverage\" is missing and does not have an android:defaultValue")
      }
      return BeverageDetailsBottomSheetArgs(__beverage)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle):
        BeverageDetailsBottomSheetArgs {
      val __beverage : Beverage?
      if (savedStateHandle.contains("beverage")) {
        if (Parcelable::class.java.isAssignableFrom(Beverage::class.java) ||
            Serializable::class.java.isAssignableFrom(Beverage::class.java)) {
          __beverage = savedStateHandle.get<Beverage?>("beverage")
        } else {
          throw UnsupportedOperationException(Beverage::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
        if (__beverage == null) {
          throw IllegalArgumentException("Argument \"beverage\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"beverage\" is missing and does not have an android:defaultValue")
      }
      return BeverageDetailsBottomSheetArgs(__beverage)
    }
  }
}
