package com.example.presentation.ui.fragments.profile.edit_profile

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.example.core.Constants
import com.example.core.either.NetworkError
import com.example.core_ui.R
import com.example.core_ui.base.BaseFragment
import com.example.core_ui.extensions.dateFormatter
import com.example.core_ui.extensions.getFileFromUri
import com.example.core_ui.extensions.loadImageWithGlide
import com.example.core_ui.extensions.setupDateTextWatcher
import com.example.core_ui.extensions.showShortToast
import com.example.core_ui.ui.NewUIState
import com.example.core_ui.ui.UIState
import com.example.domain.models.User
import com.example.presentation.databinding.FragmentEditProfileBinding
import com.example.presentation.ui.fragments.profile.ProfileViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.getScopeName
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

class EditProfileFragment : BaseFragment<FragmentEditProfileBinding, EditProfileViewModel>() {

    override val viewModel by viewModel<EditProfileViewModel>()
    override fun getViewBinding() = FragmentEditProfileBinding.inflate(layoutInflater)

    private var imageFile: File? = null

    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                binding.ivUserAvatar.setImageURI(uri)
                val originalFile = uri.getFileFromUri(requireContext())
                originalFile?.let {
                    imageFile = if (it.length() > 3 * 1024 * 1024) {
                        compressImageTo3MB( requireContext(),it)
                    } else {
                        it
                    }
                }
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
        (viewModel.userState.value as? NewUIState.Success)?.let { user ->
            if (user.data.name == etUserName.text.toString() &&
                user.data.dateOfBirth == etDate.text.toString().dateFormatter() &&
                imageFile == null
            ) {
                showShortToast(getString(R.string.data_has_not_been_changed))
            } else if (etUserName.text.toString().isEmpty()) {
                showShortToast(getString(R.string.username_empty_error_message))
            } else if (etUserName.text.toString().all { it.isDigit() }) {
                showShortToast(getString(R.string.username_digits_error_message))
            } else {
                viewModel.updateData(
                    name = etUserName.text.toString(),
                    date = etDate.text.toString().dateFormatter(),
                    avatar = imageFile,
                )
            }
        }
    }

    override fun launchObservers() {
        viewModel.userState.spectateNewUiState (
            success = { user ->
                setUserData(user)
            },
            error = {
                when(it) {
                    is NetworkError.Api -> {
                        showShortToast(it.error)
                    }
                    is NetworkError.AuthApi -> {
                        if (it.errorResponse.code == 413)
                            showShortToast(getString(R.string.avatar_size_error_message))
                    }
                    is NetworkError.Timeout -> {}
                }
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
    private fun compressImageTo3MB(context: Context, file: File): File {
        val maxFileSize = 3 * 1024 * 1024
        val bitmap = BitmapFactory.decodeFile(file.absolutePath)
        var quality = 100
        var compressedData: ByteArray

        do {
            val outputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
            compressedData = outputStream.toByteArray()
            quality -= 5
        } while (compressedData.size > maxFileSize && quality > 0)

        val compressedFile = File(context.cacheDir, "compressed_${System.currentTimeMillis()}.jpg")
        FileOutputStream(compressedFile).use { it.write(compressedData) }

        return compressedFile
    }
}