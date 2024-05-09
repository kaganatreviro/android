package com.example.presentation.ui.fragments.home

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core_ui.base.BaseFragment
import com.example.core_ui.extensions.gone
import com.example.core_ui.extensions.showShortToast
import com.example.core_ui.extensions.visible
import com.example.domain.models.EstablishmentDetails
import com.example.presentation.R
import com.example.presentation.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home),
    EstablishmentAdapter.ItemClickListener {

    override val binding by viewBinding(FragmentHomeBinding::bind)
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
            loading = {
                binding.progressBar.visible()
            },
            success = {
                binding.progressBar.gone()
                adapter.items = it.toMutableList()
                adapter.notifyDataSetChanged()
            },
            error = {
                binding.progressBar.gone()
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