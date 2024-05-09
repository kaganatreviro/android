package com.example.presentation.ui.fragments

<<<<<<< HEAD
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
=======
import androidx.core.net.toUri
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
>>>>>>> 735401d03cfcf3642c9c71492df0120e343646af
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core.Constants.DEEPLINK_NAV_TO_AUTH_MODULE
import com.example.core.either.Either
import com.example.core_ui.base.BaseFlowFragment
import com.example.core_ui.extensions.showShortToast
import com.example.presentation.R
import com.example.presentation.databinding.FragmentMainFlowBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class MainFragment : BaseFlowFragment(R.layout.fragment_main_flow, R.id.nav_host_main) {

    private val binding by viewBinding(FragmentMainFlowBinding::bind)
    private val mainViewModel by activityViewModel<MainViewModel>()

    override fun setupNavigation(navController: NavController) {
        binding.bottomNavView.setupWithNavController(navController)

<<<<<<< HEAD
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
=======
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.navigateToAuthModule.collect {
                    when(it) {
                        is Either.Right -> {
                            navigateToAuth()
                        }
                        is Either.Left ->{
                            showShortToast(it.value)
                        }
                        else -> {}
                    }
                }
            }
        }
    }

    private fun navigateToAuth() {
        val request = NavDeepLinkRequest.Builder
            .fromUri(DEEPLINK_NAV_TO_AUTH_MODULE.toUri())
            .build()
        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.nav_graph_bottom_nav, false)
            .build()
        findNavController().navigate(request, navOptions)
>>>>>>> 735401d03cfcf3642c9c71492df0120e343646af
    }
}