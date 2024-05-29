package com.example.presentation.ui.fragments.search.beverages

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core_ui.base.BaseFragment
import com.example.presentation.databinding.FragmentBeveragesBinding
import com.example.presentation.ui.adapters.BeveragePagingAdapter
import com.example.presentation.ui.fragments.search.SearchFragmentDirections
import com.example.presentation.ui.fragments.search.SearchViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class BeveragesFragment : BaseFragment<FragmentBeveragesBinding, SearchViewModel>() {

    override fun getViewBinding() = FragmentBeveragesBinding.inflate(layoutInflater)
    override val viewModel by activityViewModel<SearchViewModel>()
    private val beverageAdapter: BeveragePagingAdapter by lazy {
        BeveragePagingAdapter(::onBeverageItemClick)
    }

    override fun initialize() {
        binding.rvBeverages.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = beverageAdapter
        }
    }

    override fun launchObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getBeverages().collect { pagingData ->
                    beverageAdapter.submitData(pagingData)
                }
            }
        }
    }

    private fun onBeverageItemClick(id: Int) {
        findNavController().navigate(
            SearchFragmentDirections.actionSearchFragmentToBeverageDetailsFragment(id)
        )
    }
}