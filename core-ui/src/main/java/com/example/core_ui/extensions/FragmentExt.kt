package com.example.core_ui.extensions

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.showShortToast(text: String) {
    Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
}