package com.example.presentation.ui.fragments.search.establishments

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.core_ui.base.BaseFragment
import com.example.presentation.R
import com.example.presentation.databinding.FragmentEstablishmentsBinding
import com.example.presentation.ui.fragments.search.SearchViewModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.ktx.addMarker
import com.google.maps.android.ktx.awaitMap
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class EstablishmentsFragment : BaseFragment<FragmentEstablishmentsBinding,SearchViewModel>(), OnMapReadyCallback {

    override val viewModel by activityViewModel<SearchViewModel>()
    override fun getViewBinding() = FragmentEstablishmentsBinding.inflate(layoutInflater)

    private lateinit var mMap: GoogleMap

    override fun initialize() {
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                val mapFragment: SupportMapFragment? =
                    childFragmentManager.findFragmentById(R.id.google_map) as? SupportMapFragment
                val googleMap: GoogleMap? = mapFragment?.awaitMap()
                googleMap?.addMarker {
                    position(LatLng(42.8768247052714, 74.57927470362905))
                    title("Ebat")
                }
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val point = LatLng(42.8768247052714, 74.57927470362905)
        mMap.addMarker(MarkerOptions().position(point).title("Юр академия"))
    }
}