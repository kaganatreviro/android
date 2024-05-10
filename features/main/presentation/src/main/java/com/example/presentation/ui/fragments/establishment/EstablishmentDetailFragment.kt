package com.example.presentation.ui.fragments.establishment

import android.annotation.SuppressLint
import android.util.Log
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

    override fun setupListeners(): Unit = with(binding) {
        btnBack.setOnClickListener { findNavController().popBackStack() }
        scrollView.post {
            val scrollDistance = tvMenu.top - scrollView.scrollY
            scrollView.smoothScrollBy(0, scrollDistance)
        }
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
                Log.d("log", "Error $it")
                showShortToast(it)
            }
        )
    }

    override fun onBackPressed() {
        findNavController().popBackStack()
    }
}