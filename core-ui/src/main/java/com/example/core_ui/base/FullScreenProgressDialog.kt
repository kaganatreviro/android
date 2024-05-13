package com.example.core_ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.core_ui.R
import com.example.core_ui.databinding.FullscreenProgressBinding

class FullScreenProgressDialog : DialogFragment() {

    lateinit var binding: FullscreenProgressBinding

    private var onCancel: (() -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AlertDialog_FullScreenDialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FullscreenProgressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.apply {
            onCancel?.let { setOnCancelListener { it() } }
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            window?.setLayout(width, height)
        }

    }

    fun setOnCancelListener(onCancel: () -> Unit) {
        this.onCancel = onCancel
    }

}