package com.example.core_ui.extensions

import android.widget.Toast
import androidx.appcompat.R
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun Fragment.showShortToast(text: String) {
    Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
}
 fun Fragment.showSimpleDialog(title: String, message:String){
    MaterialAlertDialogBuilder(requireContext(),
        R.style.AlertDialog_AppCompat)
        .setMessage(message)
        .setTitle(title)
        .setPositiveButton("OK") { dialog, which ->
            dialog.dismiss()
        }
        .show()
}