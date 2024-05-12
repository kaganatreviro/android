package com.example.presentation.ui.fragments.search.beverages

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core_ui.base.BaseFragment
import com.example.core_ui.extensions.gone
import com.example.core_ui.extensions.showShortToast
import com.example.core_ui.extensions.visible
import com.example.presentation.databinding.FragmentBeveragesBinding
import com.example.presentation.ui.adapters.BeveragesAdapter
import com.example.presentation.ui.fragments.search.SearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class BeveragesFragment :
    BaseFragment<FragmentBeveragesBinding, SearchViewModel>() {
    override fun getViewBinding() = FragmentBeveragesBinding.inflate(layoutInflater)
    override val viewModel by viewModel<SearchViewModel>()
    private val beverageAdapter: BeveragesAdapter by lazy {
        BeveragesAdapter()
    }

    override fun initialize() {
        binding.rvBeverages.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = beverageAdapter
        }
    }

    override fun launchObservers() {
        viewModel.beveragesState.spectateUiState(
            error = {
                showShortToast(it)
                hideDialog()
            },
            loading = {
                showDialog()
            },
            success = {
                hideDialog()
                beverageAdapter.submitList(it)
            }
        )
    }
}