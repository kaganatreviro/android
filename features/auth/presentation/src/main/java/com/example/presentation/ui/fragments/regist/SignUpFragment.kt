package com.example.presentation.ui.fragments.regist

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core_ui.base.BaseFragment
import com.example.core_ui.extensions.dateFormatter
import com.example.core_ui.extensions.gone
import com.example.core_ui.extensions.showShortToast
import com.example.core_ui.extensions.visible
import com.example.domain.models.UserRegisterRequest
import com.example.presentation.R
import com.example.presentation.databinding.FragmentSignUpBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Calendar

class SignUpFragment :
    BaseFragment<FragmentSignUpBinding, SignUpViewModel>(R.layout.fragment_sign_up) {
    override val binding by viewBinding(FragmentSignUpBinding::bind)
    override val viewModel by viewModel<SignUpViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    override fun setupListeners() {
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.etPass.addTextChangedListener {
            validatePass(it.toString())
        }

        setBirthdayEditText()

        binding.btnNext.setOnClickListener {
            checkInputData()
        }
    }

    private fun checkInputData(): Unit = with(binding) {
        if (etEnterName.text.toString().isEmpty()) {
            showShortToast("Enter your name")
        } else if (etEnterBirth.text.toString().length < 10) {
            showShortToast("Enter your birthday")
        } else if (etEnterEmail.text.toString().isEmpty()) {
            showShortToast("Enter your email")
        } else if (etPass.text.toString().isEmpty()) {
            showShortToast("Enter password")
        } else if (etPass.text.toString() != etRePass.text.toString()) {
            showShortToast("Passwords do not match")
        } else {
            viewModel.userRegister(
                UserRegisterRequest(
                    name = etEnterName.text.toString(),
                    email = etEnterEmail.text.toString(),
                    datOfBirth = etEnterBirth.text.toString().dateFormatter(),
                    password = etPass.text.toString(),
                    passwordConfirm = etRePass.text.toString()
                )
            )
        }
    }

    override fun launchObservers() {
        viewModel.registerState.spectateUiState(
            loading = {
                binding.progressBar.visible()
            },
            success = {
                binding.progressBar.gone()
                showShortToast("Success register")
            },
            error = {
                binding.progressBar.gone()
                showShortToast(it)
            }
        )
    }

    @SuppressLint("ResourceAsColor")
    fun validatePass(password: String) {
        if (password.isEmpty()) {
            binding.tvPassValidLength.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    com.example.core_ui.R.color.correct_false_text
                )
            )
        } else {
            binding.tvPassValidLength.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    com.example.core_ui.R.color.correct_true_text
                )
            )
        }
        if (password.length < 8) {
            binding.tvPassValidUpLowCase.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    com.example.core_ui.R.color.correct_false_text
                )
            )
        } else {
            binding.tvPassValidUpLowCase.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    com.example.core_ui.R.color.correct_true_text
                )
            )
        }
    }

    override fun onBackPressed() {
        findNavController().popBackStack()
    }

    private fun setBirthdayEditText() {

        binding.etEnterBirth.addTextChangedListener(object : TextWatcher {

            private var current = ""
            private val ddmmyyyy = "DDMMYYYY"
            private val cal = Calendar.getInstance()

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString() != current) {
                    var clean = p0.toString().replace("[^\\d.]|\\.".toRegex(), "")
                    val cleanC = current.replace("[^\\d.]|\\.", "")

                    val cl = clean.length
                    var sel = cl
                    var i = 2
                    while (i <= cl && i < 6) {
                        sel++
                        i += 2
                    }
                    if (clean == cleanC) sel--

                    if (clean.length < 8) {
                        clean += ddmmyyyy.substring(clean.length)
                    } else {
                        var day = Integer.parseInt(clean.substring(0, 2))
                        var mon = Integer.parseInt(clean.substring(2, 4))
                        var year = Integer.parseInt(clean.substring(4, 8))

                        mon = if (mon < 1) 1 else if (mon > 12) 12 else mon
                        cal.set(Calendar.MONTH, mon - 1)
                        year = if (year < 1900) 1900 else if (year > 2100) 2100 else year
                        cal.set(Calendar.YEAR, year)

                        day = if (day > cal.getActualMaximum(Calendar.DATE)) cal.getActualMaximum(
                            Calendar.DATE
                        ) else day
                        clean = String.format("%02d%02d%02d", day, mon, year)
                    }

                    clean = String.format(
                        "%s/%s/%s", clean.substring(0, 2),
                        clean.substring(2, 4),
                        clean.substring(4, 8)
                    )

                    sel = if (sel < 0) 0 else sel
                    current = clean
                    binding.etEnterBirth.setText(current)
                    binding.etEnterBirth.setSelection(if (sel < current.count()) sel else current.count())
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable) {

            }
        })
    }
}