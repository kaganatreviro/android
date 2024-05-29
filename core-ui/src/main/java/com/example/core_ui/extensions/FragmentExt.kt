package com.example.core_ui.extensions

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.R
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
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

// Extension function to hide the keyboard for Activity
fun Activity.hideKeyboard(view: View) {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

// Extension function to hide the keyboard for Fragment
fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

// Extension function to set up touch listener for hiding the keyboard in Fragment
@SuppressLint("ClickableViewAccessibility")
fun Fragment.setupUIToHideKeyboardOnTouch(view: View) {
    if (view !is EditText) {
        view.setOnTouchListener { _, _ ->
            hideKeyboard()
            view.clearFocus()
            false
        }
    }

    if (view is ViewGroup) {
        for (i in 0 until view.childCount) {
            val innerView = view.getChildAt(i)
            setupUIToHideKeyboardOnTouch(innerView)
        }
    }
}
