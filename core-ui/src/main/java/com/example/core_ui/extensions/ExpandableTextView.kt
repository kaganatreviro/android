package com.example.core_ui.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import com.example.core_ui.R

class ExpandableTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr), View.OnClickListener {

    private var isExpanded = false
    private var collapsedMaxLines: Int = 3
    private var expandText: String = "Read More"
    private var collapseText: String = "Read Less"

    init {
        // Чтение атрибутов из XML, если они есть
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.ExpandableTextView, 0, 0)
            collapsedMaxLines = typedArray.getInt(R.styleable.ExpandableTextView_collapsedMaxLines, 3)
            expandText = typedArray.getString(R.styleable.ExpandableTextView_expandText) ?: "Read More"
            collapseText = typedArray.getString(R.styleable.ExpandableTextView_collapseText) ?: "Read Less"
            typedArray.recycle()
        }
        setOnClickListener(this)
        updateTextView()
    }

    override fun onClick(v: View?) {
        isExpanded = !isExpanded
        updateTextView()
    }

    @SuppressLint("SetTextI18n")
    private fun updateTextView() {
        if (isExpanded) {
            maxLines = Integer.MAX_VALUE
        } else {
            maxLines = collapsedMaxLines
        }
    }
}