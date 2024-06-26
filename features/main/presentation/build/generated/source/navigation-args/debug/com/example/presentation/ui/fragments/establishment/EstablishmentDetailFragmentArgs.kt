package com.example.presentation.ui.fragments.establishment

import android.os.Bundle
import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import com.example.domain.models.EstablishmentDetailsArg
import java.io.Serializable
import java.lang.IllegalArgumentException
import java.lang.UnsupportedOperationException
import kotlin.Suppress
import kotlin.jvm.JvmStatic

public data class EstablishmentDetailFragmentArgs(
  public val establishmentDetailsArg: EstablishmentDetailsArg,
) : NavArgs {
  @Suppress("CAST_NEVER_SUCCEEDS")
  public fun toBundle(): Bundle {
    val result = Bundle()
    if (Parcelable::class.java.isAssignableFrom(EstablishmentDetailsArg::class.java)) {
      result.putParcelable("establishmentDetailsArg", this.establishmentDetailsArg as Parcelable)
    } else if (Serializable::class.java.isAssignableFrom(EstablishmentDetailsArg::class.java)) {
      result.putSerializable("establishmentDetailsArg", this.establishmentDetailsArg as
          Serializable)
    } else {
      throw UnsupportedOperationException(EstablishmentDetailsArg::class.java.name +
          " must implement Parcelable or Serializable or must be an Enum.")
    }
    return result
  }

  @Suppress("CAST_NEVER_SUCCEEDS")
  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    if (Parcelable::class.java.isAssignableFrom(EstablishmentDetailsArg::class.java)) {
      result.set("establishmentDetailsArg", this.establishmentDetailsArg as Parcelable)
    } else if (Serializable::class.java.isAssignableFrom(EstablishmentDetailsArg::class.java)) {
      result.set("establishmentDetailsArg", this.establishmentDetailsArg as Serializable)
    } else {
      throw UnsupportedOperationException(EstablishmentDetailsArg::class.java.name +
          " must implement Parcelable or Serializable or must be an Enum.")
    }
    return result
  }

  public companion object {
    @JvmStatic
    @Suppress("DEPRECATION")
    public fun fromBundle(bundle: Bundle): EstablishmentDetailFragmentArgs {
      bundle.setClassLoader(EstablishmentDetailFragmentArgs::class.java.classLoader)
      val __establishmentDetailsArg : EstablishmentDetailsArg?
      if (bundle.containsKey("establishmentDetailsArg")) {
        if (Parcelable::class.java.isAssignableFrom(EstablishmentDetailsArg::class.java) ||
            Serializable::class.java.isAssignableFrom(EstablishmentDetailsArg::class.java)) {
          __establishmentDetailsArg =
              bundle.get("establishmentDetailsArg") as EstablishmentDetailsArg?
        } else {
          throw UnsupportedOperationException(EstablishmentDetailsArg::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
        if (__establishmentDetailsArg == null) {
          throw IllegalArgumentException("Argument \"establishmentDetailsArg\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"establishmentDetailsArg\" is missing and does not have an android:defaultValue")
      }
      return EstablishmentDetailFragmentArgs(__establishmentDetailsArg)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle):
        EstablishmentDetailFragmentArgs {
      val __establishmentDetailsArg : EstablishmentDetailsArg?
      if (savedStateHandle.contains("establishmentDetailsArg")) {
        if (Parcelable::class.java.isAssignableFrom(EstablishmentDetailsArg::class.java) ||
            Serializable::class.java.isAssignableFrom(EstablishmentDetailsArg::class.java)) {
          __establishmentDetailsArg =
              savedStateHandle.get<EstablishmentDetailsArg?>("establishmentDetailsArg")
        } else {
          throw UnsupportedOperationException(EstablishmentDetailsArg::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
        if (__establishmentDetailsArg == null) {
          throw IllegalArgumentException("Argument \"establishmentDetailsArg\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"establishmentDetailsArg\" is missing and does not have an android:defaultValue")
      }
      return EstablishmentDetailFragmentArgs(__establishmentDetailsArg)
    }
  }
}
