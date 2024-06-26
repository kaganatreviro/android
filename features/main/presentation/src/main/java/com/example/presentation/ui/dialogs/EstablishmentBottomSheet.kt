package com.example.presentation.ui.dialogs

import android.annotation.SuppressLint
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core_ui.base.BaseBottomSheetDialogFragment
import com.example.core_ui.extensions.gone
import com.example.core_ui.extensions.loadImageWithGlide
import com.example.core_ui.extensions.showShortToast
import com.example.domain.models.Establishment
import com.example.presentation.R
import com.example.presentation.databinding.FragmentEstablishmentDetailBinding
import com.example.presentation.ui.fragments.establishment.EstablishmentDetailViewModel
import com.example.presentation.ui.fragments.establishment.EstbDetPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class EstablishmentBottomSheet: BaseBottomSheetDialogFragment<FragmentEstablishmentDetailBinding, EstablishmentDetailViewModel>(
    R.layout.fragment_establishment_detail, true) {

    override val binding by viewBinding(FragmentEstablishmentDetailBinding::bind)
    override val viewModel by viewModel<EstablishmentDetailViewModel>()
    private val args by navArgs<EstablishmentBottomSheetArgs>()
    private val pagerAdapter: EstbDetPagerAdapter by lazy {
        EstbDetPagerAdapter(args.establishmentDetailsArg, this)
    }

    override fun initialize() {
        binding.btnBack.gone()
        viewModel.getEstablishmentDetailsById(args.establishmentDetailsArg.establishmentId)
        binding.viewPager.adapter = pagerAdapter
    }

    override fun launchObservers(): Unit = with(binding) {
        viewModel.establishmentDetailsState.spectateUiState(
            success = { establishment ->
                setEstablishmentData(establishment)
            },
            error = {
                showShortToast(it)
            }
        )
    }

    @SuppressLint("SetTextI18n")
    private fun FragmentEstablishmentDetailBinding.setEstablishmentData(
        establishment: Establishment
    ) {
        ivEstImage.loadImageWithGlide(establishment.logo)
        tvName.text = establishment.name
        tvAddress.text = establishment.address
        tvDesc.text = establishment.description
        tvPhoneNumber.text = establishment.phoneNumber
        tvUserEmail.text = establishment.email
        tvTitleHappyHoursTime.text = "${getString(com.example.core_ui.R.string.happy_time)} ${
            establishment.happyHoursStart.dropLast(3)
        } to ${establishment.happyHoursEnd.dropLast(3)}"
        setupTabBar(establishment.feedbackCount)
    }

    private fun setupTabBar(feedbackCount: String) {
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(com.example.core_ui.R.string.menu_tab_title)
                1 -> tab.text = getString(com.example.core_ui.R.string.feedback_tab_title) + " (" + feedbackCount + ")"
            }
        }.attach()
    }
}