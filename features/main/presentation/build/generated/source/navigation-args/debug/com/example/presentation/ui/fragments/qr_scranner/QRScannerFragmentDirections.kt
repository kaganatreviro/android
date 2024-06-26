package com.example.presentation.ui.fragments.qr_scranner

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.domain.models.EstablishmentDetailsArg
import com.example.presentation.R
import java.io.Serializable
import java.lang.UnsupportedOperationException
import kotlin.Int
import kotlin.Suppress

public class QRScannerFragmentDirections private constructor() {
  private data class ActionQRScannerFragmentToEstablishmentDetailFragment(
    public val establishmentDetailsArg: EstablishmentDetailsArg,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_QRScannerFragment_to_establishmentDetailFragment

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
    public
        fun actionQRScannerFragmentToEstablishmentDetailFragment(establishmentDetailsArg: EstablishmentDetailsArg):
        NavDirections =
        ActionQRScannerFragmentToEstablishmentDetailFragment(establishmentDetailsArg)

    public fun actionQRScannerFragmentToSubscriptionsDetailsFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_QRScannerFragment_to_subscriptionsDetailsFragment)
  }
}
