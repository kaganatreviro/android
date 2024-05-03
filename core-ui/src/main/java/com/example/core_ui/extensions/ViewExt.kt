package com.example.core_ui.extensions

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun EditText.isNotEmpty(): Boolean {
    return this.text.toString().trim().isNotEmpty()
}