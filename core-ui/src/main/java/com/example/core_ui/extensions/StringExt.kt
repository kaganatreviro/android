package com.example.core_ui.extensions

import java.text.SimpleDateFormat
import java.util.Locale

fun String.dateFormatter(inputFormat: String = "dd/MM/yyyy", outputFormat: String = "yyyy-MM-dd"): String =
    try {
        SimpleDateFormat(inputFormat, Locale.getDefault()).parse(this)
            ?.let { SimpleDateFormat(outputFormat, Locale.getDefault()).format(it) } ?: ""
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }