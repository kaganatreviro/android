package com.example.presentation.ui.fragments.establishment

import android.annotation.SuppressLint
import androidx.core.widget.NestedScrollView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.core_ui.R
import com.example.core_ui.base.BaseFragment
import com.example.core_ui.extensions.loadImageWithGlide
import com.example.core_ui.extensions.showShortToast
import com.example.presentation.databinding.FragmentEstablishmentDetailBinding
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class EstablishmentDetailFragment :
    BaseFragment<FragmentEstablishmentDetailBinding, EstablishmentDetailViewModel>(){
    override fun getViewBinding() = FragmentEstablishmentDetailBinding.inflate(layoutInflater)
    override val viewModel by viewModel<EstablishmentDetailViewModel>()
    private val args: EstablishmentDetailFragmentArgs by navArgs()
    private val pagerAdapter: EstbDetPagerAdapter by lazy {
        EstbDetPagerAdapter(args, this)
    }

    @SuppressLint("SetTextI18n")
    override fun initialize(): Unit = with(binding) {
        getEstablishmentDetailsById()

        binding.viewPager.adapter = pagerAdapter
    }

    override fun setupListeners(): Unit = with(binding) {
        btnBack.setOnClickListener { findNavController().popBackStack() }
    }

    private fun getEstablishmentDetailsById() {
        val param = args.establishmentId
        viewModel.getEstablishmentDetailsById(param)
    }

    @SuppressLint("NotifyDataSetChanged", "SetTextI18n")
    override fun launchObservers() = with(binding) {
        viewModel.establishmentDetailsState.spectateUiState(
            success = {
                ivEstImage.loadImageWithGlide(it.logo)
                tvName.text = it.name
                tvAddress.text = it.address
                tvDesc.text = it.description
                tvLocation.text = it.location.type
                tvPhoneNumber.text = it.phoneNumber
                tvTitleHappyHoursTime.text =
                    getString(com.example.core_ui.R.string.happy_time) + " " + it.happyHoursStart + " to " + it.happyHoursEnd
                setupTabBar(it.feedbackCount)
            },
            error = {
                showShortToast(it)
            }
        )
    }

    private fun setupTabBar(feedbackCount: String){
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.menu_tab_title)
                1 -> tab.text = getString(R.string.feedback_tab_title) + " " + feedbackCount
            }
        }.attach()
    }

    override fun onBackPressed() {
        findNavController().popBackStack()
    }
}