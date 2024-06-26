package com.example.presentation.ui.dialogs

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavDirections
import com.example.domain.models.Beverage
import com.example.domain.models.Feedback
import com.example.presentation.R
import java.io.Serializable
import java.lang.UnsupportedOperationException
import kotlin.Int
import kotlin.Suppress

public class EstablishmentBottomSheetDirections private constructor() {
  private data class ActionEstablishmentDetailFragmentToBeverageDetailsFragment(
    public val beverage: Beverage,
  ) : NavDirections {
    public override val actionId: Int =
        R.id.action_establishmentDetailFragment_to_beverageDetailsFragment

    public override val arguments: Bundle
      @Suppress("CAST_NEVER_SUCCEEDS")
      get() {
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
  }

  private data class ActionEstablishmentDetailFragmentToFeedbackDetailsFragment(
    public val feedback: Feedback,
  ) : NavDirections {
    public override val actionId: Int =
        R.id.action_establishmentDetailFragment_to_feedbackDetailsFragment

    public override val arguments: Bundle
      @Suppress("CAST_NEVER_SUCCEEDS")
      get() {
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
  }

  public companion object {
    public fun actionEstablishmentDetailFragmentToBeverageDetailsFragment(beverage: Beverage):
        NavDirections = ActionEstablishmentDetailFragmentToBeverageDetailsFragment(beverage)

    public fun actionEstablishmentDetailFragmentToFeedbackDetailsFragment(feedback: Feedback):
        NavDirections = ActionEstablishmentDetailFragmentToFeedbackDetailsFragment(feedback)
  }
}
