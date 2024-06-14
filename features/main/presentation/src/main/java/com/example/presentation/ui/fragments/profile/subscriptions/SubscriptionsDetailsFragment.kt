package com.example.presentation.ui.fragments.profile.subscriptions

import android.annotation.SuppressLint
import android.util.Log
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core_ui.base.BaseFragment
import com.example.core_ui.extensions.showShortToast
import com.example.domain.models.BuySubscription
import com.example.domain.models.Plan
import com.example.presentation.databinding.FragmentSubscriptionsDetailsBinding
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class SubscriptionsDetailsFragment : BaseFragment<FragmentSubscriptionsDetailsBinding, SubscriptionsViewModel>(),
SubscriptionAdapter.ItemClickListener{

    override val viewModel by activityViewModel<SubscriptionsViewModel>()
    override fun getViewBinding() = FragmentSubscriptionsDetailsBinding.inflate(layoutInflater)
    private lateinit var adapter: SubscriptionAdapter
    private lateinit var planId: BuySubscription
    private lateinit var paypalUrl: String

    override fun initialize() = with(binding) {
        rvPlans.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapter = SubscriptionAdapter(this@SubscriptionsDetailsFragment, subscriptionStatus)
        rvPlans.adapter = adapter
        getSubscriptionPlan()
        binding.btnSubscribe.isEnabled = false
    }

    override fun setupListeners() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnSubscribe.setOnClickListener {
            buySubscription()
        }
    }

    private fun buySubscription(){
        viewModel.buySubscriptionPlanById(planId)
    }

    private fun getSubscriptionPlan(){
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
                findNavController().navigate(SubscriptionsDetailsFragmentDirections.actionSubscriptionsDetailsFragmentToWebViewFragment(paypalUrl))
            },
            error = {
                Log.d("error", "Error " + it)
                showShortToast(it)
            }
        )
    }

    override fun onItemClick(item: Plan, index: Int) {
        planId = BuySubscription(item.id)
        adapter.selectItem(index)
        binding.btnSubscribe.isEnabled = true
    }

}