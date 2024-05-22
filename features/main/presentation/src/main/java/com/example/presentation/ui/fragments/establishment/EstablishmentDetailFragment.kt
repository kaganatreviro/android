package com.example.presentation.ui.fragments.establishment

import android.annotation.SuppressLint
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core_ui.base.BaseFragment
import com.example.core_ui.extensions.loadImageWithGlide
import com.example.core_ui.extensions.showShortToast
import com.example.core_ui.extensions.showSimpleDialog
import com.example.domain.models.OrderRequest
import com.example.presentation.databinding.FragmentEstablishmentDetailBinding
import kotlinx.coroutines.async
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.runBlocking
import org.koin.androidx.viewmodel.ext.android.viewModel

class EstablishmentDetailFragment :
    BaseFragment<FragmentEstablishmentDetailBinding, EstablishmentDetailViewModel>() {
    override fun getViewBinding() = FragmentEstablishmentDetailBinding.inflate(layoutInflater)
    override val viewModel by viewModel<EstablishmentDetailViewModel>()
    private val args: EstablishmentDetailFragmentArgs by navArgs()
    private val menuAdapter: EstablishmentMenuAdapter by lazy {
        EstablishmentMenuAdapter(requireContext(), args.enabledButton, ::onBeverageItemClick, ::onGetForFreeBtnClick)
    }

    @SuppressLint("SetTextI18n")
    override fun initialize(): Unit = with(binding) {
        rvBeveragesMenu.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rvBeveragesMenu.adapter = menuAdapter

        runBlocking {
            joinAll(
                async { getEstablishmentDetailsById() })
        }
    }

    override fun setupListeners(): Unit = with(binding) {
        btnBack.setOnClickListener { findNavController().popBackStack() }
        scrollView.post {
            val scrollDistance = tvMenu.top - scrollView.scrollY
            scrollView.smoothScrollBy(0, scrollDistance)
        }
    }

    private suspend fun getEstablishmentDetailsById() {
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

        viewModel.orderState.spectateUiState(
            success = {
                showSimpleDialog("Success", " ")
            },
            error = {
                showSimpleDialog(it, "")
            }
        )
    }

    override fun onBackPressed() {
        findNavController().popBackStack()
    }

    private fun onBeverageItemClick(id: Int) {
        findNavController().navigate(
            EstablishmentDetailFragmentDirections.actionEstablishmentDetailFragmentToBeverageDetailsFragment(
                id
            )
        )
    }

    private fun onGetForFreeBtnClick(id: Int){
        val param = OrderRequest(id)
        viewModel.makeOrder(param)
    }
}