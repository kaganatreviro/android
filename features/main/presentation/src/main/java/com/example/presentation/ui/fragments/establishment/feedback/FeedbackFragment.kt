package com.example.presentation.ui.fragments.establishment.feedback

import android.annotation.SuppressLint
import android.util.Log
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core_ui.base.BaseFragment
import com.example.core_ui.extensions.hideKeyboard
import com.example.core_ui.extensions.showShortToast
import com.example.domain.models.Feedback
import com.example.domain.models.PostFeedback
import com.example.presentation.databinding.AddCommentBottomSheetBinding
import com.example.presentation.databinding.FragmentFeedbackBinding
import com.example.presentation.ui.fragments.establishment.EstablishmentDetailFragmentArgs
import com.example.presentation.ui.fragments.establishment.EstablishmentDetailFragmentDirections
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class FeedbackFragment(private val args: EstablishmentDetailFragmentArgs) :
    BaseFragment<FragmentFeedbackBinding, FeedbackViewModel>(), FeedbackAdapter.ItemClickListener {
    override fun getViewBinding() = FragmentFeedbackBinding.inflate(layoutInflater)
    override val viewModel by viewModel<FeedbackViewModel>()
    private var adapter: FeedbackAdapter? = null
    private lateinit var bottomSheetDialog: BottomSheetDialog
    private lateinit var addCommentDialogView: AddCommentBottomSheetBinding

    @SuppressLint("SetTextI18n")
    override fun initialize(): Unit = with(binding) {
        rvFeedback.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        setupAddCommentBottomSheet()
        getFeedbackList()
    }

    override fun setupListeners(): Unit = with(binding) {
        btnShowEditor.setOnClickListener {
            showBottomSheet()
        }
    }

    private fun setupAddCommentBottomSheet() {
        addCommentDialogView = AddCommentBottomSheetBinding.inflate(layoutInflater)
        bottomSheetDialog =
            BottomSheetDialog(requireContext(), com.example.core_ui.R.style.BottomSheetDialogTheme)
        bottomSheetDialog.setContentView(addCommentDialogView.root)

        addCommentDialogView.btnAccept.setOnClickListener {
            val params =
                PostFeedback(args.establishmentId, addCommentDialogView.etFeedback.text.toString())
            viewModel.postFeedback(params)
            bottomSheetDialog.dismiss()
        }

        addCommentDialogView.tvCancel.setOnClickListener {
            bottomSheetDialog.dismiss()
        }
    }

    private fun showBottomSheet() {
        bottomSheetDialog.show()
    }

    private fun getFeedbackList() {
        viewModel.getEstablishmentFeedbackList(args.establishmentId)
    }

    @SuppressLint("NotifyDataSetChanged", "SetTextI18n")
    override fun launchObservers() = with(binding) {
        viewModel.establishmentFeedbackListState.spectateUiState(
            success = {
                adapter = FeedbackAdapter(listItems = it.toMutableList(), this@FeedbackFragment)
                rvFeedback.adapter = adapter
                adapter!!.notifyDataSetChanged()
            },
            error = {
                showShortToast(it)
            }
        )

        viewModel.postFeedbackState.spectateUiState(
            success = {
                showShortToast("Success")
                getFeedbackList()
            },
            error = {
                showShortToast(it)
            }
        )
    }

    override fun onItemClick(feedback: Feedback, answers: Int) {
        findNavController().navigate(
            EstablishmentDetailFragmentDirections.actionEstablishmentDetailFragmentToFeedbackDetailsFragment(
                feedback
            )
        )
    }
}