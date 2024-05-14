package com.example.core_ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.core_ui.databinding.DialogFullScreenProgressBinding

class TestLoadingFragment : DialogFragment() {

    private lateinit var binding: DialogFullScreenProgressBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogFullScreenProgressBinding.inflate(layoutInflater)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            setBackgroundDrawableResource(android.R.color.transparent)
            setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
    }

    companion object {
        const val TAG = "FullscreenLoadingDialog"
    }
}

fun FragmentManager.showLoading() {
    val existingDialog = findFragmentByTag(TestLoadingFragment.TAG) as? TestLoadingFragment
    if (existingDialog == null || !existingDialog.isAdded) {
        TestLoadingFragment().show(this, TestLoadingFragment.TAG)
    }
}

fun FragmentManager.hideLoading() {
    (findFragmentByTag(TestLoadingFragment.TAG) as? TestLoadingFragment)
        ?.run { dismiss() }
}