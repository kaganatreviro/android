package com.example.presentation.ui.fragments.home

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.Constants
import com.example.core_ui.base.BaseFragment
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

    override fun initialize() = with(binding) {
        rvRestList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapter = EstablishmentAdapter(this@HomeFragment)
        rvRestList.adapter = adapter
        getEstablishmentList()
        checkSubscriptionStatus()
    }

    override fun setupListeners() {
        binding.swipeRef.setOnRefreshListener {
            binding.swipeRef.isRefreshing = false
            getEstablishmentList()
            checkSubscriptionStatus()
        }
    }

    private fun getEstablishmentList() {
        viewModel.getEstablishmentList()
    }

    private fun checkSubscriptionStatus() {
        viewModel.checkSubscriptionStatus()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun launchObservers() {
        viewModel.establishmentListState.spectateUiState(
            success = {
                adapter.items = it.toMutableList()
                adapter.notifyDataSetChanged()
            },
            error = {
                showShortToast(it)
            }
        )

        viewModel.checkSubscriptionStatusState.spectateUiState(
            success = {
                binding.tvSubsStatusValue.isEnabled = it.isActive
                subscriptionStatus = it.isActive
                if (it.isActive)
                    binding.tvSubsStatusTitle.text =
                        resources.getString(com.example.core_ui.R.string.subs_status_active)
                else binding.tvSubsStatusTitle.text =
                    resources.getString(com.example.core_ui.R.string.subs_status_inactive)
            },
            error = {
                binding.tvSubsStatusValue.isEnabled = false
                subscriptionStatus = false
                binding.tvSubsStatusTitle.text =
                    resources.getString(com.example.core_ui.R.string.subs_status_inactive)
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
}