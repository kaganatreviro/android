package com.example.presentation.ui.fragments.establishment.feedback

import android.os.Bundle
import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import com.example.domain.models.Feedback
import java.io.Serializable
import java.lang.IllegalArgumentException
import java.lang.UnsupportedOperationException
import kotlin.Suppress
import kotlin.jvm.JvmStatic

public data class FeedbackDetailsFragmentArgs(
  public val feedback: Feedback,
) : NavArgs {
  @Suppress("CAST_NEVER_SUCCEEDS")
  public fun toBundle(): Bundle {
    val result = Bundle()
    if (Parcelable::class.java.isAssignableFrom(Feedback::class.java)) {
      result.putParcelable("feedback", this.feedback as Parcelable)
    } else if (Serializable::class.java.isAssignableFrom(Feedback::class.java)) {
      result.putSerializable("feedback", this.feedback as Serializable)
    } else {
      throw UnsupportedOperationException(Feedback::class.java.name +
          " must implement Parcelable or Serializable or must be an Enum.")
    }
    return result
  }

  @Suppress("CAST_NEVER_SUCCEEDS")
  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    if (Parcelable::class.java.isAssignableFrom(Feedback::class.java)) {
      result.set("feedback", this.feedback as Parcelable)
    } else if (Serializable::class.java.isAssignableFrom(Feedback::class.java)) {
      result.set("feedback", this.feedback as Serializable)
    } else {
      throw UnsupportedOperationException(Feedback::class.java.name +
          " must implement Parcelable or Serializable or must be an Enum.")
    }
    return result
  }

  public companion object {
    @JvmStatic
    @Suppress("DEPRECATION")
    public fun fromBundle(bundle: Bundle): FeedbackDetailsFragmentArgs {
      bundle.setClassLoader(FeedbackDetailsFragmentArgs::class.java.classLoader)
      val __feedback : Feedback?
      if (bundle.containsKey("feedback")) {
        if (Parcelable::class.java.isAssignableFrom(Feedback::class.java) ||
            Serializable::class.java.isAssignableFrom(Feedback::class.java)) {
          __feedback = bundle.get("feedback") as Feedback?
        } else {
          throw UnsupportedOperationException(Feedback::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
        if (__feedback == null) {
          throw IllegalArgumentException("Argument \"feedback\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"feedback\" is missing and does not have an android:defaultValue")
      }
      return FeedbackDetailsFragmentArgs(__feedback)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle):
        FeedbackDetailsFragmentArgs {
      val __feedback : Feedback?
      if (savedStateHandle.contains("feedback")) {
        if (Parcelable::class.java.isAssignableFrom(Feedback::class.java) ||
            Serializable::class.java.isAssignableFrom(Feedback::class.java)) {
          __feedback = savedStateHandle.get<Feedback?>("feedback")
        } else {
          throw UnsupportedOperationException(Feedback::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
        if (__feedback == null) {
          throw IllegalArgumentException("Argument \"feedback\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"feedback\" is missing and does not have an android:defaultValue")
      }
      return FeedbackDetailsFragmentArgs(__feedback)
    }
  }
}
