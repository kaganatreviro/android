package com.example.presentation.ui.fragments.establishment.feedback

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core_ui.base.BaseFragment
import com.example.core_ui.extensions.showShortToast
import com.example.core_ui.extensions.showSimpleDialog
import com.example.domain.models.Feedback
import com.example.domain.models.PostFeedback
import com.example.domain.models.PostFeedbackInAnswers
import com.example.presentation.databinding.FragmentFeedbackDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class FeedbackDetailsFragment : BaseFragment<FragmentFeedbackDetailsBinding, FeedbackViewModel>(),
    FeedbackAdapter.ItemClickListener {
    override fun getViewBinding() = FragmentFeedbackDetailsBinding.inflate(layoutInflater)
    override val viewModel by viewModel<FeedbackViewModel>()
    private val args: FeedbackDetailsFragmentArgs by navArgs()
    private var adapter: FeedbackAdapter? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun initialize(): Unit = with(binding) {
        rvFeedbackAnswer.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        setupView()
        getFeedbackAnswer()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setupView() {
        binding.etInputAnswer.isVisible = getUserEmail() == args.feedback.user
        binding.btnSend.isVisible = getUserEmail() == args.feedback.user
        val formatter: DateTimeFormatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX")
        val displayFormatter: DateTimeFormatter =
            DateTimeFormatter.ofPattern("yyyy:MM:dd, HH:mm")
        val dateTime = LocalDateTime.parse(args.feedback.createdAt, formatter)
        binding.tvPostTime.text = dateTime.format(displayFormatter)
        binding.tvUserName.text = args.feedback.displayUser
        binding.tvFeedback.text = args.feedback.text
    }

    private fun getUserEmail(): String? {
        return viewModel.getUserEmail()
    }

    override fun setupListeners() {
        binding.btnBack.setOnClickListener { findNavController().popBackStack() }
        binding.btnSend.setOnClickListener {
            postNewFeedbackInAnswers()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun launchObservers() {
        viewModel.feedbackAnswersState.spectateUiState(
            success = {
                adapter = FeedbackAdapter(it.reversed().toMutableList(), this@FeedbackDetailsFragment)
                binding.rvFeedbackAnswer.adapter = adapter
                adapter!!.notifyDataSetChanged()
            },
            error =
            {
                showSimpleDialog(it, "")
            }
        )

        viewModel.postFeedbackInAnswersState.spectateUiState(
            success =
            {
                getFeedbackAnswer()
                binding.etInputAnswer.text?.clear()
            },
            error =
            {
                showSimpleDialog(it, "")
            }
        )
    }

    private fun getFeedbackAnswer() {
        viewModel.getFeedbackAnswers(args.feedback.id)
    }

    private fun postNewFeedbackInAnswers() {
        if (checkEmptyField()) {
            showSimpleDialog("Error","The field must not be empty!")
        } else {
            val params =
                PostFeedbackInAnswers(args.feedback.id, binding.etInputAnswer.text.toString())
            viewModel.postFeedbackInAnswers(params)
        }
    }

    private fun checkEmptyField(): Boolean {
        return binding.etInputAnswer.text.toString().isEmpty()
    }

    override fun onItemClick(feedback: Feedback, answers: Int) {}
}