package com.example.presentation.ui.fragments.establishment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core_ui.base.BaseFragment
import com.example.presentation.R
import com.example.presentation.databinding.FragmentEstablishmentDetailBinding

class EstablishmentDetailFragment : BaseFragment<FragmentEstablishmentDetailBinding, EstablishmentDetailViewModel>(R.layout.fragment_establishment_detail) {
    override val binding by viewBinding(FragmentEstablishmentDetailBinding::bind)
    override val viewModel by viewModels<EstablishmentDetailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        findNavController().popBackStack()
    }
}