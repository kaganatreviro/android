package com.example.presentation.ui.fragments.search

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavDirections
import com.example.domain.models.Beverage
import com.example.domain.models.EstablishmentDetailsArg
import com.example.presentation.R
import java.io.Serializable
import java.lang.UnsupportedOperationException
import kotlin.Int
import kotlin.Suppress

public class SearchFragmentDirections private constructor() {
  private data class ActionSearchFragmentToBeverageDetailsFragment(
    public val beverage: Beverage,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_searchFragment_to_beverageDetailsFragment

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

  private data class ActionSearchFragmentToEstablishmentBottomSheet(
    public val establishmentDetailsArg: EstablishmentDetailsArg,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_searchFragment_to_establishmentBottomSheet

    public override val arguments: Bundle
      @Suppress("CAST_NEVER_SUCCEEDS")
      get() {
        val result = Bundle()
        if (Parcelable::class.java.isAssignableFrom(EstablishmentDetailsArg::class.java)) {
          result.putParcelable("establishmentDetailsArg", this.establishmentDetailsArg as
              Parcelable)
        } else if (Serializable::class.java.isAssignableFrom(EstablishmentDetailsArg::class.java)) {
          result.putSerializable("establishmentDetailsArg", this.establishmentDetailsArg as
              Serializable)
        } else {
          throw UnsupportedOperationException(EstablishmentDetailsArg::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
        return result
      }
  }

  public companion object {
    public fun actionSearchFragmentToBeverageDetailsFragment(beverage: Beverage): NavDirections =
        ActionSearchFragmentToBeverageDetailsFragment(beverage)

    public
        fun actionSearchFragmentToEstablishmentBottomSheet(establishmentDetailsArg: EstablishmentDetailsArg):
        NavDirections = ActionSearchFragmentToEstablishmentBottomSheet(establishmentDetailsArg)
  }
}
