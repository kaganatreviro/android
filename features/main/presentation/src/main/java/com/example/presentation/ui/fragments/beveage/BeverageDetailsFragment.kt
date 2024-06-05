package com.example.presentation.ui.fragments.beveage

import android.annotation.SuppressLint
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.core_ui.base.BaseFragment
import com.example.core_ui.extensions.showShortToast
import com.example.domain.models.Beverage
import com.example.core_ui.R
import com.example.presentation.databinding.FragmentBeverageDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class BeverageDetailsFragment : BaseFragment<FragmentBeverageDetailsBinding, BeverageDetailsViewModel>() {

    override val viewModel by viewModel<BeverageDetailsViewModel>()
    override fun getViewBinding() = FragmentBeverageDetailsBinding.inflate(layoutInflater)
    private val args by navArgs<BeverageDetailsFragmentArgs>()

    override fun initialize() {
        viewModel.getBeverageById(args.beverageId)
    }

    override fun setupListeners() {
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun launchObservers() {
        viewModel.beverageState.spectateUiState(
            success = { beverage ->
                setBeverageData(beverage)
            },
            error = {
                showShortToast(it)
            }
        )
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