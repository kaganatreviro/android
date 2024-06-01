package com.example.presentation.ui.fragments.establishment.feedback

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core_ui.base.BaseFragment
import com.example.core_ui.extensions.showSimpleDialog
import com.example.domain.models.Feedback
import com.example.presentation.databinding.FragmentFeedbackDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class FeedbackDetailsFragment : BaseFragment<FragmentFeedbackDetailsBinding, FeedbackViewModel>(), FeedbackAdapter.ItemClickListener {
    override fun getViewBinding() = FragmentFeedbackDetailsBinding.inflate(layoutInflater)
    override val viewModel by viewModel<FeedbackViewModel>()
    private val args: FeedbackDetailsFragmentArgs by navArgs()
    lateinit var adapter: FeedbackAdapter

    @RequiresApi(Build.VERSION_CODES.O)
    override fun initialize(): Unit = with(binding) {
        rvFeedbackAnswer.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapter = FeedbackAdapter(this@FeedbackDetailsFragment)
        rvFeedbackAnswer.adapter = adapter

        setupView()
        getFeedbackAnswer()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setupView(){
        val formatter: DateTimeFormatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX")
        val displayFormatter: DateTimeFormatter =
            DateTimeFormatter.ofPattern("yyyy:MM:dd, HH:mm")
        val dateTime = LocalDateTime.parse(args.feedback.createdAt, formatter)
        binding.tvPostTime.text = dateTime.format(displayFormatter)
        binding.tvUserName.text = args.feedback.displayUser
        binding.tvFeedback.text = args.feedback.text
    }

    override fun setupListeners() {
       binding.btnBack.setOnClickListener { findNavController().popBackStack() }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun launchObservers() {
       viewModel.feedbackAnswersState.spectateUiState(
           success = {
               adapter.items = it.toMutableList()
               adapter.notifyDataSetChanged()
           },
           error = {
               Log.d("error", "error - $it")
               showSimpleDialog(it, "")
           }
       )
    }

    private fun getFeedbackAnswer(){
        viewModel.getFeedbackAnswers(args.feedback.id)
    }

    override fun onItemClick(feedback: Feedback, answers: Boolean) {}
}