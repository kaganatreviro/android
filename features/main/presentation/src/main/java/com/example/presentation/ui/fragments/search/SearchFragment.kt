package com.example.presentation.ui.fragments.search

import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.view.isVisible
import androidx.viewpager2.widget.ViewPager2
import com.example.core_ui.base.BaseFragment
import com.example.core_ui.R
import com.example.presentation.databinding.FragmentSearchBinding
import com.example.presentation.ui.adapters.SearchPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>() {

    override fun getViewBinding() = FragmentSearchBinding.inflate(layoutInflater)
    override val viewModel by activityViewModel<SearchViewModel>()
    private val pagerAdapter: SearchPagerAdapter by lazy {
        SearchPagerAdapter(this)
    }

    override fun initialize() {
        binding.viewPager.adapter = pagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.tab_title_1)
                1 -> tab.text = getString(R.string.tab_title_2)
            }
        }.attach()
    }

    override fun setupListeners(): Unit = with(binding) {
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tvAvailabilityStatus.isVisible = position == 0
            }
        })

        searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.beverageSearch(newText)
                return true
            }
        })

        tvAvailabilityStatus.setOnClickListener {
            if (tvAvailabilityStatus.text == getString(R.string.status_available)) {
                tvAvailabilityStatus.text = getString(R.string.status_all)
            } else {
                tvAvailabilityStatus.text = getString(R.string.status_available)
            }
            viewModel.filterBeveragesByStatus(tvAvailabilityStatus.text.toString())
        }
    }
}