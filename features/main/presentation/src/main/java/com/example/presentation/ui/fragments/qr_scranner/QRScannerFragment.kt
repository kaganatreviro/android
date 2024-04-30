package com.example.presentation.ui.fragments.qr_scranner

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core_ui.base.BaseFragment
import com.example.presentation.R
import com.example.presentation.databinding.QrscannerFragmentBinding
import me.dm7.barcodescanner.zbar.Result
import me.dm7.barcodescanner.zbar.ZBarScannerView

class QRScannerFragment: BaseFragment<QrscannerFragmentBinding,
        QRScannerViewModel>(R.layout.qrscanner_fragment), ZBarScannerView.ResultHandler {
    override val binding by viewBinding(QrscannerFragmentBinding::bind)
    override val viewModel by viewModels<QRScannerViewModel>()
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        TODO("Not yet implemented")

    }

    private fun checkCameraPermission() {
        when{
            ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED ->{
                        Toast.makeText(requireContext(), "Camera run", Toast.LENGTH_SHORT).show()
                    }
            shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) ->{
                Toast.makeText(requireContext(), "We need your permission", Toast.LENGTH_SHORT).show()
            }

            else ->{pLauncher.launch(Manifest.permission.CAMERA)}
        }
    }

    private fun registPermissionListener(){
        pLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()){
            if (it){
                Toast.makeText(requireContext(), "Camera run", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireContext(), "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        findNavController().popBackStack()
    }
}