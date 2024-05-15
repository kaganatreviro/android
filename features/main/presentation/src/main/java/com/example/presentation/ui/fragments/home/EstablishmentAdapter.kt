package com.example.presentation.ui.fragments.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core_ui.extensions.loadImageWithGlide
import com.example.core_ui.extensions.setImageWithGlide
import com.example.domain.models.EstablishmentDetails
import com.example.presentation.R
import com.example.presentation.databinding.ItemEstablishmentBinding

class EstablishmentAdapter(private val clickListener: ItemClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items: MutableList<EstablishmentDetails> = mutableListOf()

    interface ItemClickListener {
        fun onItemClick(item: EstablishmentDetails, index: Int)
    }

    override fun getItemViewType(position: Int): Int {
        return if (items.isEmpty()) 0 else 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            0 -> EmptyHolder.create(parent)
            1 -> Holder.create(parent, clickListener)
            else -> throw IllegalStateException("Unknown view")
        }

    override fun getItemCount(): Int = if (items.isEmpty()) 1 else items.size

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
                    .inflate(R.layout.empty_fragment, parent, false)
                return EmptyHolder(view)
            }
        }
    }

    class Holder private constructor(private val binding: ItemEstablishmentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        lateinit var item: EstablishmentDetails
        var index: Int = 0

        @SuppressLint("SetTextI18n", "CheckResult")
        fun bind(_item: EstablishmentDetails, position: Int) {
            item = _item
            index = position

            item.apply {
                binding.tvName.text = item.name
                binding.tvHappyTime.text = "in " +
                        item.happyHoursStart + " from " + item.happyHoursEnd
                if(item.logo.isEmpty())
                    binding.ivRestImage.setImageWithGlide(com.example.core_ui.R.drawable.ic_logo)
                else binding.ivRestImage.loadImageWithGlide(item.logo)
            }
        }

        companion object {
            fun create(parent: ViewGroup, clickListener: ItemClickListener): Holder {
                val binding = ItemEstablishmentBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                return Holder(binding).apply {
                    itemView.setOnClickListener {
                        clickListener.onItemClick(item, index)
                    }
                }
            }
        }
    }
}