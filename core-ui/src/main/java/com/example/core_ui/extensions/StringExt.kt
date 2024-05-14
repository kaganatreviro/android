package com.example.core_ui.extensions

import com.example.core.Constants.DatePattern
import java.text.SimpleDateFormat
import java.util.Locale

fun String.dateFormatter(
    inputFormat: String = DatePattern.ddMMyyyy,
    outputFormat: String = DatePattern.yyyyMMdd
): String =
    try {
        SimpleDateFormat(inputFormat, Locale.getDefault()).parse(this)
            ?.let { SimpleDateFormat(outputFormat, Locale.getDefault()).format(it) } ?: ""
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }