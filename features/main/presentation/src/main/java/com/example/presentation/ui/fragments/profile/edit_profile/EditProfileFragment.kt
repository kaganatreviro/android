package com.example.presentation.ui.fragments.profile.edit_profile

import android.util.Log
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.example.core.Constants
import com.example.core.either.Either
import com.example.core_ui.base.BaseFragment
import com.example.core_ui.extensions.dateFormatter
import com.example.core_ui.extensions.getFileFromUri
import com.example.core_ui.extensions.loadImageWithGlide
import com.example.core_ui.extensions.setupDateTextWatcher
import com.example.core_ui.extensions.showShortToast
import com.example.core_ui.ui.UIState
import com.example.domain.models.User
import com.example.core_ui.R
import com.example.presentation.databinding.FragmentEditProfileBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class EditProfileFragment : BaseFragment<FragmentEditProfileBinding, EditProfileViewModel>() {

    override val viewModel by viewModel<EditProfileViewModel>()
    override fun getViewBinding() = FragmentEditProfileBinding.inflate(layoutInflater)

    private var imageFile: File? = null

    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                binding.ivUserAvatar.setImageURI(uri)
                imageFile = uri.getFileFromUri(requireContext())
            }
        }

    override fun setupListeners() {
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.btnEditAvatar.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
        binding.btnUpdate.setOnClickListener {
            updateUserData()
        }
        binding.etDate.setupDateTextWatcher()
    }

    private fun updateUserData(): Unit = with(binding) {
        (viewModel.userState.value as? UIState.Success)?.let { user ->
            if (user.data.name == etUserName.text.toString() &&
                user.data.dateOfBirth == etDate.text.toString().dateFormatter() &&
                imageFile == null
            ) {
                showShortToast(getString(R.string.data_has_not_been_changed))
            } else {
                viewModel.updateData(
                    name = etUserName.text.toString(),
                    date = "2020-02-02",
                    avatar = imageFile,
                )
            }
        }
    }

    override fun launchObservers() {
        viewModel.userState.spectateUiState(
            loading = {
                showDialog()
            },
            success = { user ->
                hideDialog()
                setUserData(user)
            },
            error = {
                hideDialog()
            }
        )

        viewModel.updateUserDataState.spectateUiState(
            loading = {

            },
            success = {
                Log.e("ololo", "success")
            },
            error = {
                Log.e("ololo", "error $it")
            }
        )
    }

    private fun setUserData(user: User): Unit = with(binding) {
        user.avatar?.let { avatar ->
            ivUserAvatar.loadImageWithGlide(avatar)
        }
        etUserName.setText(user.name)
        etDate.setText(
            user.dateOfBirth?.dateFormatter(
                Constants.DatePattern.yyyyMMdd,
                Constants.DatePattern.ddMMyyyy
            )
        )
    }
}