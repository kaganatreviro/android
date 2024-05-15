package com.example.presentation.ui.fragments.establishment

import android.annotation.SuppressLint
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core_ui.base.BaseFragment
import com.example.core_ui.extensions.loadImageWithGlide
import com.example.core_ui.extensions.setImageWithGlide
import com.example.core_ui.extensions.showShortToast
import com.example.presentation.databinding.FragmentEstablishmentDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class EstablishmentDetailFragment :
    BaseFragment<FragmentEstablishmentDetailBinding, EstablishmentDetailViewModel>() {
    override fun getViewBinding() = FragmentEstablishmentDetailBinding.inflate(layoutInflater)
    override val viewModel by viewModel<EstablishmentDetailViewModel>()
    private val args: EstablishmentDetailFragmentArgs by navArgs()
    private val menuAdapter: EstablishmentMenuAdapter by lazy {
        EstablishmentMenuAdapter()
    }

    @SuppressLint("SetTextI18n")
    override fun initialize(): Unit = with(binding) {
        rvBeveragesMenu.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rvBeveragesMenu.adapter = menuAdapter

        getEstablishmentDetailsById()
    }

    override fun setupListeners(): Unit = with(binding) {
        btnBack.setOnClickListener { findNavController().popBackStack() }
        scrollView.post {
            val scrollDistance = tvMenu.top - scrollView.scrollY
            scrollView.smoothScrollBy(0, scrollDistance)
        }
    }

    private fun getEstablishmentMenuById() {
        val param = args.establishmentId
        viewModel.getEstablishmentMenuById(param)
    }

    private fun getEstablishmentDetailsById() {
        val param = args.establishmentId
        viewModel.getEstablishmentDetailsById(param)
    }

    @SuppressLint("NotifyDataSetChanged", "SetTextI18n")
    override fun launchObservers() = with(binding) {
        viewModel.establishmentDetailsState.spectateUiState(
            success = {
                getEstablishmentMenuById()

                if(it.logo.isEmpty())
                    ivEstImage.setImageWithGlide(com.example.core_ui.R.drawable.ic_logo)
                    else ivEstImage.loadImageWithGlide(it.logo)
                tvName.text = it.name
                tvAddress.text = it.address
                tvDesc.text = it.description
                tvLocation.text = it.location.type
                tvPhoneNumber.text = it.phoneNumber
                tvTitleHappyHoursTime.text =
                    getString(com.example.core_ui.R.string.happy_time) + " " + it.happyHoursStart + " from " + it.happyHoursEnd
            },
            error = {
                showShortToast(it)
            }
        )

        viewModel.establishmentMenuState.spectateUiState(
            success = {
                menuAdapter.submitList(it)
                menuAdapter.notifyDataSetChanged()
            },
            error = {
                showShortToast(it)
            }
        )
    }

    override fun onBackPressed() {
        findNavController().popBackStack()
    }
}