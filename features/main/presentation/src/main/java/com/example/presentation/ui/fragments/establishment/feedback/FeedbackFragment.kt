package com.example.presentation.ui.fragments.establishment.feedback

import android.annotation.SuppressLint
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core_ui.base.BaseFragment
import com.example.core_ui.extensions.showShortToast
import com.example.core_ui.extensions.showSimpleDialog
import com.example.domain.models.EstablishmentDetailsArg
import com.example.domain.models.Feedback
import com.example.domain.models.PostFeedback
import com.example.presentation.databinding.AddCommentBottomSheetBinding
import com.example.presentation.databinding.FragmentFeedbackBinding
import com.example.presentation.ui.fragments.establishment.EstablishmentDetailFragmentDirections
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class FeedbackFragment(private val args: EstablishmentDetailsArg) :
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
            if (addCommentDialogView.etFeedback.text.isNullOrEmpty()){
                showSimpleDialog("Error","The field must not be empty!")
            }else {
                val params =
                    PostFeedback(
                        args.establishmentId,
                        addCommentDialogView.etFeedback.text.toString()
                    )
                viewModel.postFeedback(params)
                bottomSheetDialog.dismiss()
            }
        }

        addCommentDialogView.tvCancel.setOnClickListener {
            bottomSheetDialog.dismiss()
            addCommentDialogView.etFeedback.text?.clear()
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
                adapter = FeedbackAdapter(listItems = it.reversed().toMutableList(), this@FeedbackFragment)
                rvFeedback.adapter = adapter
                adapter!!.notifyDataSetChanged()
            },
            error = {
                showSimpleDialog("Error", it)
            }
        )

        viewModel.postFeedbackState.spectateUiState(
            success = {
                showShortToast("Success")
                getFeedbackList()
                addCommentDialogView.etFeedback.text?.clear()
            },
            error = {
                showSimpleDialog("Error", it)
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