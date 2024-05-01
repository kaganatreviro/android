package com.example.presentation.ui.fragments.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.establishment.EstablishmentResponse
import com.example.presentation.R
import com.example.presentation.databinding.ItemEstablishmentBinding

class EstablishmentAdapter(private val clickListener: ItemClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items: MutableList<EstablishmentResponse> = mutableListOf()

//    @GlideModule
//    class MyAppGlideModule : AppGlideModule()

    interface ItemClickListener {
        fun onItemClick(item: EstablishmentResponse, index: Int)
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

        lateinit var item: EstablishmentResponse
        var index: Int = 0

        @SuppressLint("SetTextI18n", "CheckResult")
        fun bind(_item: EstablishmentResponse, position: Int) {
            item = _item
            index = position

            item.apply {
                binding.tvName.text = item.name
                binding.tvHappyTime.text = "Happy Hours in " +
                        item.happyhoursStart + "from " + item.happyhoursEnd
//                Glide.with(itemView)
//                    .load()
//                    .into(binding.ivRestImage)
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