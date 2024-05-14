package com.example.presentation.ui.fragments.home

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core_ui.base.BaseFragment
import com.example.core_ui.extensions.showShortToast
import com.example.domain.models.EstablishmentDetails
import com.example.presentation.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(),
    EstablishmentAdapter.ItemClickListener {
    override fun getViewBinding() = FragmentHomeBinding.inflate(layoutInflater)
    override val viewModel by viewModel<HomeViewModel>()
    private lateinit var adapter: EstablishmentAdapter

    override fun setupListeners() = with(binding) {
        rvRestList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapter = EstablishmentAdapter(this@HomeFragment)
        rvRestList.adapter = adapter
        getEstablishmentList()
    }

    private fun getEstablishmentList() {
        viewModel.getEstablishmentList()
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
                item
            )
        )
    }
}