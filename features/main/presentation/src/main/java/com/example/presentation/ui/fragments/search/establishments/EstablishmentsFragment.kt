package com.example.presentation.ui.fragments.search.establishments

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat.*
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.core_ui.base.BaseFragment
import com.example.core_ui.extensions.showShortToast
import com.example.presentation.R
import com.example.presentation.databinding.FragmentEstablishmentsBinding
import com.example.presentation.ui.fragments.search.SearchViewModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.ktx.addMarker
import com.google.maps.android.ktx.awaitMap
import com.google.maps.android.ktx.awaitMapLoad
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class EstablishmentsFragment : BaseFragment<FragmentEstablishmentsBinding, SearchViewModel>() {

    override val viewModel by activityViewModel<SearchViewModel>()
    override fun getViewBinding() = FragmentEstablishmentsBinding.inflate(layoutInflater)

    private var gMap: GoogleMap? = null
    private var locationPermissionRequest: ActivityResultLauncher<Array<String>>? = null

    override fun initialize() {
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.google_map) as SupportMapFragment

        registPermissionListener()
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                val googleMap = mapFragment.awaitMap()
                googleMap.awaitMapLoad()

                setupGoogleMap(googleMap)
            }
        }
    }

    private fun setupGoogleMap(googleMap: GoogleMap) {
        gMap = googleMap
        if (checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED ||
            checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            googleMap.isMyLocationEnabled = true
        } else {
            requestLocationPermission()
        }
        addMarkers(googleMap)
    }

    private fun addMarkers(googleMap: GoogleMap) {
        viewModel.establishmentsState.spectateUiState(
            showLoader = false,
            success = { establishments ->
                googleMap.clear()
                establishments.forEach { establishment ->
                    googleMap.addMarker {
                        title(establishment.name)
                        position(
                            LatLng(
                                establishment.location.coordinates[0].toDouble(),
                                establishment.location.coordinates[1].toDouble()
                            )
                        )
                    }
                }
            }
        )
    }

    @SuppressLint("MissingPermission")
    private fun registPermissionListener() {
        locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    gMap?.isMyLocationEnabled = true
                }

                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    gMap?.isMyLocationEnabled = true
                }

                else -> {
                    showShortToast("No location access granted")
                }
            }
        }
        requestLocationPermission()
    }

    private fun requestLocationPermission() {
        locationPermissionRequest?.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }
}