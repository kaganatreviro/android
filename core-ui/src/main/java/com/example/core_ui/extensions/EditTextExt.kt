package com.example.core_ui.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.util.Calendar

fun EditText.setupDateTextWatcher() {
    val ddmmyyyy = "DDMMYYYY"
    val cal = Calendar.getInstance()
    var current = ""

    this.addTextChangedListener(object : TextWatcher {
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if (p0.toString() != current) {
                var clean = p0.toString().replace("[^\\d.]|\\.".toRegex(), "")
                val cleanC = current.replace("[^\\d.]|\\.", "")

                val cl = clean.length
                var sel = cl
                var i = 2
                while (i <= cl && i < 6) {
                    sel++
                    i += 2
                }
                if (clean == cleanC) sel--

                if (clean.length < 8) {
                    clean += ddmmyyyy.substring(clean.length)
                } else {
                    var day = Integer.parseInt(clean.substring(0, 2))
                    var mon = Integer.parseInt(clean.substring(2, 4))
                    var year = Integer.parseInt(clean.substring(4, 8))

                    mon = if (mon < 1) 1 else if (mon > 12) 12 else mon
                    cal.set(Calendar.MONTH, mon - 1)
                    year = if (year < 1900) 1900 else if (year > 2100) 2100 else year
                    cal.set(Calendar.YEAR, year)

                    day = if (day > cal.getActualMaximum(Calendar.DATE)) cal.getActualMaximum(
                        Calendar.DATE
                    ) else day
                    clean = String.format("%02d%02d%02d", day, mon, year)
                }

                clean = String.format(
                    "%s/%s/%s", clean.substring(0, 2),
                    clean.substring(2, 4),
                    clean.substring(4, 8)
                )

                sel = if (sel < 0) 0 else sel
                current = clean
                this@setupDateTextWatcher.setText(current)
                this@setupDateTextWatcher.setSelection(if (sel < current.count()) sel else current.count())
            }
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(p0: Editable) {}
    })
}