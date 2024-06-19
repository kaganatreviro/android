package com.example.presentation.ui.fragments.profile.subscriptions

import android.annotation.SuppressLint
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core_ui.base.BaseFragment
import com.example.core_ui.base.BaseFragment.SubscriptionData.subscriptionStatus
import com.example.core_ui.base.BaseFragment.SubscriptionData.subscriptionsPlanId
import com.example.core_ui.extensions.showShortToast
import com.example.core_ui.extensions.showSimpleDialog
import com.example.domain.models.BuySubscription
import com.example.domain.models.BuySubscriptionResponse
import com.example.domain.models.Plan
import com.example.presentation.databinding.FragmentSubscriptionsDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SubscriptionsDetailsFragment :
    BaseFragment<FragmentSubscriptionsDetailsBinding, SubscriptionsViewModel>(),
    SubscriptionAdapter.ItemClickListener {

    override fun getViewBinding() = FragmentSubscriptionsDetailsBinding.inflate(layoutInflater)
    override val viewModel by viewModel<SubscriptionsViewModel>()
    private lateinit var adapter: SubscriptionAdapter
    private var planId: Int = 0
    private var planPrice: Double = 0.0
    private lateinit var paypalUrl: BuySubscriptionResponse

    override fun initialize() = with(binding) {
        rvPlans.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapter = SubscriptionAdapter(
            this@SubscriptionsDetailsFragment,
            subscriptionStatus,
            subscriptionsPlanId
        )
        rvPlans.adapter = adapter
        binding.btnSubscribe.isEnabled = false
        binding.btnSubscribe.isVisible = !subscriptionStatus
        getSubscriptionPlan()
    }

    override fun setupListeners() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnSubscribe.setOnClickListener {
            buySubscription()
        }
    }

    private fun buySubscription() {
        if (planPrice > 0) {
            viewModel.buySubscriptionPlanById(planId)
        } else {
            val param = BuySubscription(planId)
            viewModel.getFreeTrialPlan(param)
        }
    }

    private fun getSubscriptionPlan() {
        viewModel.getSubscriptionPlan()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun launchObservers() {
        viewModel.getSubscriptionPlanState.spectateUiState(
            success = {
                adapter.items = it.toMutableList()
                adapter.notifyDataSetChanged()
            },
            error = {
                showShortToast(it)
            }
        )

        viewModel.buySubscriptionPlanByIdState.spectateUiState(
            success = {
                paypalUrl = it
                findNavController().navigate(
                    SubscriptionsDetailsFragmentDirections.actionSubscriptionsDetailsFragmentToWebViewFragment(
                        paypalUrl
                    )
                )
            },
            error = {
                showSimpleDialog("", it)
            }
        )

        viewModel.getFreeTrialPlanState.spectateUiState(
            success = {
                showSimpleDialog("Success", "Free trial period connected")
                findNavController().popBackStack()
            },
            error = {
                showSimpleDialog("", it)
            }
        )
    }

    override fun onItemClick(item: Plan, index: Int) {
        planId = item.id
        planPrice = item.price.toDouble()
        adapter.selectItem(index)
        binding.btnSubscribe.isEnabled = true
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}