package com.example.presentation.ui.fragments.establishment.feedback

import android.annotation.SuppressLint
import android.util.Log
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core_ui.base.BaseFragment
import com.example.core_ui.extensions.showSimpleDialog
import com.example.presentation.databinding.FragmentFeedbackDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FeedbackDetailsFragment : BaseFragment<FragmentFeedbackDetailsBinding, FeedbackViewModel>(), FeedbackAdapter.ItemClickListener {
    override fun getViewBinding() = FragmentFeedbackDetailsBinding.inflate(layoutInflater)
    override val viewModel by viewModel<FeedbackViewModel>()
    private val args: FeedbackDetailsFragmentArgs by navArgs()
    lateinit var adapter: FeedbackAdapter

    override fun initialize(): Unit = with(binding) {
        rvFeedbackAnswer.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapter = FeedbackAdapter(this@FeedbackDetailsFragment)
        rvFeedbackAnswer.adapter = adapter

        getFeedbackAnswer()
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
        viewModel.getFeedbackAnswers(args.feedbackId)
    }

    override fun onItemClick(feedbackId: Int, answers: Boolean) {}
}