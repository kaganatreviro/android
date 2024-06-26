package com.example.presentation.ui.fragments.establishment

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.core_ui.R
import com.example.core_ui.base.BaseFragment
import com.example.core_ui.extensions.loadImageWithGlide
import com.example.core_ui.extensions.showShortToast
import com.example.presentation.databinding.FragmentEstablishmentDetailBinding
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class EstablishmentDetailFragment :
    BaseFragment<FragmentEstablishmentDetailBinding, EstablishmentDetailViewModel>() {
    override fun getViewBinding() = FragmentEstablishmentDetailBinding.inflate(layoutInflater)
    override val viewModel by viewModel<EstablishmentDetailViewModel>()
    private val args: EstablishmentDetailFragmentArgs by navArgs()
    private val pagerAdapter: EstbDetPagerAdapter by lazy {
        EstbDetPagerAdapter(args.establishmentDetailsArg, this)
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
        val param = args.establishmentDetailsArg.establishmentId
        viewModel.getEstablishmentDetailsById(param)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("NotifyDataSetChanged", "SetTextI18n")
    override fun launchObservers() = with(binding) {
        val formatterWithSeconds = DateTimeFormatter.ofPattern("HH:mm:ss")
        val formatterWithoutSeconds = DateTimeFormatter.ofPattern("HH:mm")

        viewModel.establishmentDetailsState.spectateUiState(
            success = { establishment ->
                val happyHoursStart = LocalTime.parse(establishment.happyHoursStart, formatterWithSeconds)
                val happyHoursEnd = LocalTime.parse(establishment.happyHoursEnd, formatterWithSeconds)

                ivEstImage.loadImageWithGlide(establishment.logo)
                tvName.text = establishment.name
                tvAddress.text = establishment.address
                tvDesc.text = establishment.description
                tvPhoneNumber.text = establishment.phoneNumber
                tvUserEmail.text = establishment.email
                tvTitleHappyHoursTime.text =
                    getString(com.example.core_ui.R.string.happy_time) + " " + happyHoursStart.format(
                        formatterWithoutSeconds
                    ) + " to " + happyHoursEnd.format(formatterWithoutSeconds)
                setupTabBar(establishment.feedbackCount)
            },
            error = {
                showShortToast(it)
            }
        )
    }

    private fun setupTabBar(feedbackCount: String) {
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.menu_tab_title)
                1 -> tab.text = getString(R.string.feedback_tab_title) + " (" + feedbackCount + ")"
            }
        }.attach()
    }

    override fun onBackPressed() {
        findNavController().popBackStack()
    }
}