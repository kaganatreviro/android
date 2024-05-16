package com.example.presentation.ui.fragments.qr_scranner

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.core_ui.base.BaseFragment
import com.example.presentation.databinding.QrscannerFragmentBinding
import me.dm7.barcodescanner.zbar.Result
import me.dm7.barcodescanner.zbar.ZBarScannerView
import org.koin.androidx.viewmodel.ext.android.viewModel

class QRScannerFragment : BaseFragment<QrscannerFragmentBinding,
        QRScannerViewModel>(), ZBarScannerView.ResultHandler {
    override fun getViewBinding() = QrscannerFragmentBinding.inflate(layoutInflater)
    override val viewModel by viewModel<QRScannerViewModel>()
    private lateinit var zbScanner: ZBarScannerView
    private lateinit var pLauncher: ActivityResultLauncher<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        zbScanner = ZBarScannerView(requireContext())
        return zbScanner.rootView
    }

    override fun setupListeners() {
        registPermissionListener()
        checkCameraPermission()
    }

    override fun onResume() {
        super.onResume()
        zbScanner.setResultHandler(this)
        zbScanner.startCamera()
    }

    override fun onPause() {
        super.onPause()
        zbScanner.stopCamera()
    }

    override fun handleResult(result: Result?) {
        Log.d("log" , "result = ${result?.contents!!.toInt()}")
        val param = result?.contents!!.toInt()
        findNavController().navigate(
            QRScannerFragmentDirections.actionQRScannerFragmentToEstablishmentDetailFragment(
                param
            )
        )
    }

    private fun checkCameraPermission() {
        when {
            ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED -> {
                Toast.makeText(requireContext(), "Camera run", Toast.LENGTH_SHORT).show()
            }

            shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> {
                Toast.makeText(requireContext(), "We need your permission", Toast.LENGTH_SHORT)
                    .show()
            }

            else -> {
                pLauncher.launch(Manifest.permission.CAMERA)
            }
        }
    }

    private fun registPermissionListener() {
        pLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) {
            if (it) {
                Toast.makeText(requireContext(), "Camera run", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onBackPressed() {
        findNavController().popBackStack()
    }
}