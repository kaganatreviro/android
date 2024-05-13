package com.example.presentation.ui.fragments.profile.edit_profile

import androidx.navigation.fragment.findNavController
import com.example.core_ui.base.BaseFragment
import com.example.presentation.databinding.FragmentEditProfileBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditProfileFragment : BaseFragment<FragmentEditProfileBinding, EditProfileViewModel>() {

    override val viewModel by viewModel<EditProfileViewModel>()
    override fun getViewBinding() = FragmentEditProfileBinding.inflate(layoutInflater)


    override fun setupListeners() {
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}