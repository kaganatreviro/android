package com.example.presentation.ui.fragments.establishment.feedback

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.Feedback
import com.example.presentation.databinding.ItemFeedbackBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class FeedbackAdapter(private val listItems: MutableList<Feedback>, private val itemClickListener: ItemClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = listItems

    interface ItemClickListener {
        fun onItemClick(feedback: Feedback, answers: Int)
    }

    override fun getItemViewType(position: Int): Int {
        return if (items.isEmpty()) 0 else 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            0 -> EmptyHolder.create(parent)
            1 -> Holder.create(parent, itemClickListener)
            else -> throw IllegalStateException("Unknown view")
        }

    override fun getItemCount(): Int = if (items.isEmpty()) 1 else items.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is Holder -> {
                holder.bind(items[position], position)
            }
        }
    }

    class EmptyHolder private constructor(view: View) : RecyclerView.ViewHolder(view) {
        companion object {
            fun create(parent: ViewGroup): EmptyHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(com.example.presentation.R.layout.empty_fragment, parent, false)
                return EmptyHolder(view)
            }
        }
    }

    class Holder private constructor(private val binding: ItemFeedbackBinding) :
        RecyclerView.ViewHolder(binding.root) {

        lateinit var item: Feedback
        var index: Int = 0

        @RequiresApi(Build.VERSION_CODES.O)
        @SuppressLint("SetTextI18n", "CheckResult")
        fun bind(_item: Feedback, position: Int) {
            item = _item
            index = position

            val formatter: DateTimeFormatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX")
            val displayFormatter: DateTimeFormatter =
                DateTimeFormatter.ofPattern("yyyy:MM:dd, HH:mm")

            item.apply {
                binding.tvUserName.text = item.displayUser
                val dateTime = LocalDateTime.parse(item.createdAt, formatter)
                binding.tvPostTime.text = dateTime.format(displayFormatter)
                binding.tvFeedback.text = item.text
                binding.tvReplied.isVisible = item.answers > 0
                binding.tvReplied.text = "Answers: " + item.answers
            }
        }

        companion object {
            fun create(parent: ViewGroup, clickListener: ItemClickListener): Holder {
                val binding = ItemFeedbackBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                return Holder(binding).apply {
                    itemView.setOnClickListener {
                        clickListener.onItemClick(item, item.answers)
                    }
                }
            }
        }
    }
}