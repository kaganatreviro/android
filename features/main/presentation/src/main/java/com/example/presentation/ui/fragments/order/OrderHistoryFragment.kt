package com.example.presentation.ui.fragments.order

import android.annotation.SuppressLint
import android.util.Log
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
        OrderAdapter()
    }

    override fun setupListeners() {
        binding.btnBack.setOnClickListener { findNavController().popBackStack() }

        getOrderHistory()
    }

    override fun initialize() = with(binding) {
        rvOrder.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rvOrder.adapter = orderAdapter

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
            },
            error = {
                showShortToast(it)
                Log.d("error", "Error = $it")
            }
        )
    }

    private fun getOrderHistory() {
        viewModel.getOrderHistory()
    }
}