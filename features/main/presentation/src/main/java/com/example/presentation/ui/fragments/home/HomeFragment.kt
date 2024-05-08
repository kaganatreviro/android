package com.example.presentation.ui.fragments.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core_ui.base.BaseFragment
import com.example.core_ui.extensions.gone
import com.example.core_ui.extensions.showShortToast
import com.example.core_ui.extensions.visible
import com.example.domain.models.EstablishmentList
import com.example.presentation.R
import com.example.presentation.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home),
    EstablishmentAdapter.ItemClickListener {

    override val binding by viewBinding(FragmentHomeBinding::bind)
    override val viewModel by viewModels<HomeViewModel>()
    private lateinit var adapter: EstablishmentAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() = with(binding) {
        rvRestList.layoutManager = GridLayoutManager(requireContext(), 2)
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

            },
            success = {
                adapter.items = mutableListOf(it)
                adapter.notifyDataSetChanged()
            },
            error = {

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

    override fun onItemClick(item: EstablishmentList, index: Int) {
        findNavController().navigate()
    }
}