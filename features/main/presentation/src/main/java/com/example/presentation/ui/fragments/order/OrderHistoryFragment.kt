package com.example.presentation.ui.fragments.order

import android.annotation.SuppressLint
import android.util.Log
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core_ui.base.BaseFragment
import com.example.core_ui.extensions.showShortToast
import com.example.presentation.databinding.FragmentOrderHistoryBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class OrderHistoryFragment : BaseFragment<FragmentOrderHistoryBinding, OrderHistoryViewModel>() {
    override fun getViewBinding() = FragmentOrderHistoryBinding.inflate(layoutInflater)
    override val viewModel by viewModel<OrderHistoryViewModel>()
    private val orderAdapter: OrderAdapter by lazy {
        OrderAdapter(requireContext())
    }

    override fun initialize() = with(binding) {
        rvOrder.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rvOrder.adapter = orderAdapter
        getOrderHistory()
    }

    override fun setupListeners() {
        binding.swipeRef.setOnRefreshListener {
            binding.swipeRef.isRefreshing = false
            getOrderHistory()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        findNavController().popBackStack()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun launchObservers() {
        viewModel.orderHistoryState.spectateUiState(
            success = {
                orderAdapter.submitList(it)
                orderAdapter.notifyDataSetChanged()
                binding.tvEmptyTitle.isVisible = it.isEmpty()
            },
            error = {
                showShortToast(it)
            }
        )
    }

    private fun getOrderHistory() {
        viewModel.getOrderHistory()
    }
}