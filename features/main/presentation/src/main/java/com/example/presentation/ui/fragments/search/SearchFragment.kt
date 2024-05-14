package com.example.presentation.ui.fragments.search

import com.example.core_ui.base.BaseFragment
import com.example.presentation.databinding.FragmentSearchBinding
import com.example.presentation.ui.adapters.SearchPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment :
    BaseFragment<FragmentSearchBinding, SearchViewModel>() {
    override fun getViewBinding() = FragmentSearchBinding.inflate(layoutInflater)
    override val viewModel by viewModel<SearchViewModel>()
    private val pagerAdapter: SearchPagerAdapter by lazy {
        SearchPagerAdapter(this)
    }

    override fun initialize() {
        binding.viewPager.adapter = pagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(com.example.core_ui.R.string.tab_title_1)
                1 -> tab.text = getString(com.example.core_ui.R.string.tab_title_2)
            }
        }.attach()
    }
}