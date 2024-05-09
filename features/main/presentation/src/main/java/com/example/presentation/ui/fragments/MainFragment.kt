package com.example.presentation.ui.fragments

import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core_ui.base.BaseFlowFragment
import com.example.presentation.R
import com.example.presentation.databinding.FragmentMainFlowBinding

class MainFragment : BaseFlowFragment(R.layout.fragment_main_flow, R.id.nav_host_main) {

    private val binding by viewBinding(FragmentMainFlowBinding::bind)

    override fun setupNavigation(navController: NavController) {
        binding.bottomNavView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment,
                R.id.searchFragment,
                R.id.QRScannerFragment,
                R.id.profileFragment
                -> binding.bottomNavView.isVisible = true

                else -> binding.bottomNavView.visibility = View.GONE
            }
        }
    }
}