package com.example.presentation.ui.fragments

import androidx.navigation.NavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core_ui.base.BaseFlowFragment
import com.example.presentation.R
import com.example.presentation.databinding.FragmentMainFlowBinding

class MainFragment : BaseFlowFragment(R.layout.fragment_main_flow, R.id.nav_host_main) {

    private val binding by viewBinding(FragmentMainFlowBinding::bind)

    override fun setupNavigation(navController: NavController) {
        binding.bottomNavView.setupWithNavController(navController)
    }
}