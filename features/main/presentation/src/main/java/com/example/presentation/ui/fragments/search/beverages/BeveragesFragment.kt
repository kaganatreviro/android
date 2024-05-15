package com.example.presentation.ui.fragments.search.beverages

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core_ui.base.BaseFragment
import com.example.core_ui.extensions.gone
import com.example.core_ui.extensions.showShortToast
import com.example.core_ui.extensions.visible
import com.example.presentation.R
import com.example.presentation.databinding.FragmentBeveragesBinding
import com.example.presentation.ui.adapters.BeveragesAdapter
import com.example.presentation.ui.fragments.search.SearchFragmentDirections
import com.example.presentation.ui.fragments.search.SearchViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class BeveragesFragment : BaseFragment<FragmentBeveragesBinding, SearchViewModel>() {

    override fun getViewBinding() = FragmentBeveragesBinding.inflate(layoutInflater)
    override val viewModel by activityViewModel<SearchViewModel>()
    private val beverageAdapter: BeveragesAdapter by lazy {
        BeveragesAdapter(requireContext() ,::onBeverageItemClick)
    }

    override fun initialize() {
        binding.rvBeverages.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = beverageAdapter
        }
    }

    override fun launchObservers() {
        viewModel.beveragesState.spectateUiState(
            showLoader = false,
            loading = {
                binding.progressBar.visible()
            },
            error = {
                binding.progressBar.gone()
                showShortToast(it)
            },
            success = {
                binding.progressBar.gone()
                beverageAdapter.submitList(it)
            }
        )
    }

    private fun onBeverageItemClick(id: Int) {
        findNavController().navigate(
            SearchFragmentDirections.actionSearchFragmentToBeverageDetailsFragment(id)
        )
    }
}