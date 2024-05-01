package com.example.presentation.ui.fragments.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.R
import com.example.presentation.databinding.ItemRestauranBinding

class RestauranAdapter
//    RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
//
//    var items: MutableList<RestauranModel> = mutableListOf()
//
//    @GlideModule
//    class MyAppGlideModule : AppGlideModule()
//
//    interface ItemClickListener {
//        fun onItemClick(item: RestauranModel, index: Int)
//    }
//
//    override fun getItemViewType(position: Int): Int {
//        return if (items.isEmpty()) 0 else 1
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
//        when (viewType) {
//            0 -> EmptyHolder.create(parent)
//            1 -> Holder.create(parent, clickListener)
//            else -> throw IllegalStateException("Unknown view")
//        }
//
//    override fun getItemCount(): Int = if (items.isEmpty()) 1 else items.size
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        when (holder) {
//            is Holder -> {
//                holder.bind(items[position], position)
//            }
//        }
//    }
//
//    class EmptyHolder private constructor(view: View) : RecyclerView.ViewHolder(view) {
//        companion object {
//            fun create(parent: ViewGroup): EmptyHolder {
//                val view = LayoutInflater.from(parent.context)
//                    .inflate(R.layout.empty, parent, false)
//                return EmptyHolder(view)
//            }
//        }
//    }
//
//    class Holder private constructor(private val binding: ItemRestauranBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//
//        lateinit var item: RestauranModel
//        var index: Int = 0
//
//        @SuppressLint("SetTextI18n", "CheckResult")
//        fun bind(_item: RestauranModel, position: Int) {
//            item = _item
//            index = position
//
//            item.apply {
//                binding.tvName.text = item
//                binding.tvHappyTime.text = item
//                Glide.with(itemView)
//                    .load()
//                    .into(binding.ivRestImage)
//            }
//        }
//
//        companion object {
//            fun create(parent: ViewGroup, clickListener: ItemClickListener): Holder {
//                val binding = ItemRestauranBinding
//                    .inflate(LayoutInflater.from(parent.context), parent, false)
//                return Holder(binding).apply {
//                    itemView.setOnClickListener {
//                        clickListener.onItemClick(item, index)
//                    }
//                }
//            }
//        }
//    }
}