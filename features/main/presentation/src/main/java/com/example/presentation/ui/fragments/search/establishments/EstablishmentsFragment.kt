package com.example.presentation.ui.fragments.search.establishments

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.os.Build
import android.text.TextPaint
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat.*
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.core_ui.base.BaseFragment
import com.example.core_ui.extensions.showShortToast
import com.example.domain.models.EstablishmentDetailsArg
import com.example.presentation.R
import com.example.presentation.databinding.FragmentEstablishmentsBinding
import com.example.presentation.ui.fragments.search.SearchFragmentDirections
import com.example.presentation.ui.fragments.search.SearchViewModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
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

    @RequiresApi(Build.VERSION_CODES.O)
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
        viewModel.establishmentsState.spectateNewUiState(
            showLoader = false,
            success = { establishments ->
                googleMap.clear()
                googleMap.setInfoWindowAdapter(CustomInfoWindowAdapter(requireContext()))
                establishments.forEach { establishment ->
                    googleMap.addMarker {
                        icon(bitmapDescriptorFromVectorWithText(requireContext(), com.example.core_ui.R.drawable.ic_establishment, establishment.name))
                        title(establishment.name)
                        position(
                            LatLng(
                                establishment.location.coordinates[1].toDouble(),
                                establishment.location.coordinates[0].toDouble()
                            )
                        )
                        snippet(
                            "${establishment.happyHoursStart.dropLast(3)} - ${
                                establishment.happyHoursEnd.dropLast(3)}"
                        )
                    }?.tag = establishment.id
                }

                googleMap.setOnInfoWindowClickListener {
                    it.tag?.let { establishmentId ->
                        findNavController().navigate(
                            SearchFragmentDirections.actionSearchFragmentToEstablishmentBottomSheet(
                                EstablishmentDetailsArg(
                                    establishmentId.toString().toInt(), false
                                )
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

    private fun bitmapDescriptorFromVectorWithText(context: Context, vectorResId: Int, text: String): BitmapDescriptor {
        val vectorDrawable = ContextCompat.getDrawable(context, vectorResId)!!
        vectorDrawable.setBounds(0, 0, vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight)

        val width = vectorDrawable.intrinsicWidth
        val height = vectorDrawable.intrinsicHeight
        val textPadding = 10
        val textPaddingSides = 5
        val textSize = 28f

        val paint = Paint().apply {
            this.textSize = textSize
            this.color = Color.BLACK
            this.typeface = Typeface.DEFAULT_BOLD
            this.textAlign = Paint.Align.CENTER
        }

        val textWidth = paint.measureText(text)
        val availableWidth = width - 2 * textPaddingSides

        var finalText = text
        if (textWidth > availableWidth) {
            val ellipsis = "..."
            while (paint.measureText(finalText + ellipsis) > availableWidth && finalText.isNotEmpty()) {
                finalText = finalText.substring(0, finalText.length - 1)
            }
            finalText += ellipsis
        }

        val textHeight = paint.descent() - paint.ascent()
        val bitmap = Bitmap.createBitmap(width, height + textPadding + textHeight.toInt(), Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)

        vectorDrawable.draw(canvas)
        val xPos = width / 2f
        val yPos = height + textPadding - paint.ascent()
        canvas.drawText(finalText, xPos, yPos, paint)

        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }
}