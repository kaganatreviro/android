package com.example.presentation.ui.fragments.qr_scranner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        zbScanner = ZBarScannerView(requireContext())
        return zbScanner.rootView
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

    override fun onBackPressed() {
        super.onBackPressed()
        findNavController().popBackStack()
    }
}