package com.example.presentation.ui.fragments.establishment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core_ui.base.BaseFragment
import com.example.core_ui.extensions.gone
import com.example.core_ui.extensions.loadImageWithGlide
import com.example.core_ui.extensions.showShortToast
import com.example.core_ui.extensions.visible
import com.example.presentation.R
import com.example.presentation.databinding.FragmentEstablishmentDetailBinding
import com.example.presentation.ui.adapters.BeveragesAdapter
import com.example.presentation.ui.fragments.home.EstablishmentAdapter

class EstablishmentDetailFragment :
    BaseFragment<FragmentEstablishmentDetailBinding, EstablishmentDetailViewModel>(R.layout.fragment_establishment_detail) {
    override val binding by viewBinding(FragmentEstablishmentDetailBinding::bind)
    override val viewModel by viewModels<EstablishmentDetailViewModel>()
    private val args: EstablishmentDetailFragmentArgs by navArgs()
    private val menuAdapter: EstablishmentMenuAdapter by lazy {
        EstablishmentMenuAdapter()
    }

    @SuppressLint("SetTextI18n")
    override fun initialize(): Unit = with(binding) {
        rvBeveragesMenu.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rvBeveragesMenu.adapter = menuAdapter

        ivEstImage.loadImageWithGlide(args.establishment.logo)
        tvName.text = args.establishment.name
        tvAddress.text = args.establishment.address
        tvDesc.text = args.establishment.description
        tvLocation.text = args.establishment.location.type
        tvPhoneNumber.text = args.establishment.phoneNumber
        tvTitleHappyHoursTime.text =
            getString(com.example.core_ui.R.string.happy_time) + " " + args.establishment.happyHoursStart + " from " + args.establishment.happyHoursEnd

        getEstablishmentMenuById()
    }

    private fun getEstablishmentMenuById() {
        val param = args.establishment.id
        viewModel.getEstablishmentMenuById(param)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun launchObservers() {
        viewModel.establishmentMenuState.spectateUiState(
            loading = {
                binding.progressBar.visible()
            },
            success = {
                binding.progressBar.gone()
                menuAdapter.submitList(it)
                menuAdapter.notifyDataSetChanged()
            },
            error = {
                binding.progressBar.gone()
                showShortToast(it)
            }
        )
    }

    override fun onBackPressed() {
        findNavController().popBackStack()
    }
}