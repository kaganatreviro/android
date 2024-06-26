package com.example.presentation.ui.fragments.beveage

import android.annotation.SuppressLint
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core_ui.extensions.showShortToast
import com.example.domain.models.Beverage
import com.example.core_ui.R
import com.example.core_ui.base.BaseBottomSheetDialogFragment
import com.example.presentation.databinding.BottomSheetBeverageDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class BeverageDetailsBottomSheet : BaseBottomSheetDialogFragment<BottomSheetBeverageDetailsBinding, BeverageDetailsViewModel>(
    com.example.presentation.R.layout.bottom_sheet_beverage_details) {

    override val viewModel by viewModel<BeverageDetailsViewModel>()
    override val binding by viewBinding(BottomSheetBeverageDetailsBinding::bind)
    private val args by navArgs<BeverageDetailsBottomSheetArgs>()

    override fun initialize() {
        setBeverageData(args.beverage)
    }

    @SuppressLint("SetTextI18n")
    private fun setBeverageData(beverage: Beverage): Unit = with(binding) {
        tvTitle.text = beverage.name
        tvPrice.text = "${beverage.price} KGS"
        tvDescription.text = beverage.description
        tvCategory.text = beverage.category
        val status = if (beverage.availabilityStatus) {
            getString(R.string.status_available)
        } else {
            getString(R.string.status_unavailable)
        }
        tvStatus.text = status
        tvEstablishment.text = beverage.establishment
    }
}