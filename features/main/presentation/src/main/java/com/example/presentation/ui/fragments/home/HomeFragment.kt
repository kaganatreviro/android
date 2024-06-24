package com.example.presentation.ui.fragments.home

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.Constants
import com.example.core.either.NetworkError
import com.example.core_ui.base.BaseFragment
import com.example.core_ui.base.BaseFragment.SubscriptionData.subscriptionEndDate
import com.example.core_ui.base.BaseFragment.SubscriptionData.subscriptionStatus
import com.example.core_ui.base.BaseFragment.SubscriptionData.subscriptionsPlanId
import com.example.core_ui.base.BaseFragment.SubscriptionData.subscriptionsPlanName
import com.example.core_ui.extensions.showShortToast
import com.example.domain.models.EstablishmentDetails
import com.example.presentation.R
import com.example.presentation.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(),
    EstablishmentAdapter.ItemClickListener {
    override fun getViewBinding() = FragmentHomeBinding.inflate(layoutInflater)
    override val viewModel by viewModel<HomeViewModel>()
    private lateinit var adapter: EstablishmentAdapter

    @SuppressLint("SuspiciousIndentation")
    override fun setupListeners() = with(binding) {
        rvRestList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapter = EstablishmentAdapter(this@HomeFragment)
        rvRestList.adapter = adapter
        getEstablishmentList()
        setupView()
    }

    private fun setupView(){
        if (subscriptionStatus) {
            binding.tvSubsStatusTitle.text =
                resources.getString(com.example.core_ui.R.string.subs_status_active)
        }else{
            checkSubscriptionStatus()
        }
    }

    override fun onResume() {
        super.onResume()
        setupView()
    }

    override fun initialize() {
        binding.swipeRef.setOnRefreshListener {
            binding.swipeRef.isRefreshing = false
            getEstablishmentList()
        }
    }

    private fun checkSubscriptionStatus(){
        viewModel.checkSubscriptionStatus()
    }

    private fun getEstablishmentList() {
        viewModel.getEstablishmentList()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun launchObservers() {
        viewModel.establishmentListState.spectateNewUiState(
            success = {
                adapter.items = it.toMutableList()
                adapter.notifyDataSetChanged()
            },
            error = {
                when(it) {
                    is NetworkError.AuthApi -> {
                        if (it.errorResponse.code == 401) {
                            navigateToAuth()
                        }
                        showShortToast(it.errorResponse.message)
                    }
                    is NetworkError.Api -> {
                        showShortToast(it.error)
                    }
                    else -> {}
                }
            }
        )

        viewModel.checkSubscriptionStatusState.spectateUiState(
            success = {
                subscriptionStatus = it.isActive
                subscriptionsPlanName = it.plan.name
                subscriptionEndDate = it.endDate
                subscriptionsPlanId = it.plan.id
                binding.tvSubsStatusTitle.text =
                    resources.getString(com.example.core_ui.R.string.subs_status_active)
            },
            error = {
                binding.tvSubsStatusTitle.text =
                    resources.getString(com.example.core_ui.R.string.subs_status_inactive)
                subscriptionStatus = false
                subscriptionsPlanName = ""
                subscriptionEndDate = ""
            }
        )
    }

    override fun onBackPressed() {
        if (backPressedTime + doubleBackPressInterval > System.currentTimeMillis()) {
            requireActivity().finish()
        } else {
            Toast.makeText(
                requireContext(),
                "Нажмите еще раз для выхода",
                Toast.LENGTH_SHORT
            )
                .show()
        }
        backPressedTime = System.currentTimeMillis()
    }

    override fun onItemClick(item: EstablishmentDetails, index: Int) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToEstablishmentDetailFragment(
                item.id, false
            )
        )
    }

    private fun navigateToAuth() {
        val request = NavDeepLinkRequest.Builder
            .fromUri(Constants.Deeplink.DEEPLINK_NAV_TO_AUTH_MODULE.toUri())
            .build()
        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.nav_graph_main, false)
            .build()
        findNavController().navigate(request, navOptions)
    }
}