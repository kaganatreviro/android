package com.example.presentation.ui.fragments.establishment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core_ui.base.BaseFragment
import com.example.presentation.R
import com.example.presentation.databinding.FragmentEstablishmentDetailBinding
import com.example.presentation.databinding.FragmentHomeBinding
import com.example.presentation.ui.fragments.main.HomeViewModel

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