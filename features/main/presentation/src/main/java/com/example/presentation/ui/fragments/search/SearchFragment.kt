package com.example.presentation.ui.fragments.search

import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core_ui.base.BaseFragment
import com.example.presentation.R
import com.example.presentation.databinding.FragmentSearchBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>(R.layout.fragment_search) {
    override val binding by viewBinding(FragmentSearchBinding::bind)
    override val viewModel by viewModel<SearchViewModel>()
}