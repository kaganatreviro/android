package com.example.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.core_ui.extensions.loadImageWithGlide
import com.example.domain.models.Beverage
import com.example.presentation.databinding.ItemBeverageBinding

class BeveragesAdapter : ListAdapter<Beverage, BeveragesAdapter.BeverageViewHolder>(callback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BeverageViewHolder(
        ItemBeverageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: BeverageViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class BeverageViewHolder(private val binding: ItemBeverageBinding) :
        ViewHolder(binding.root) {

        fun onBind(beverage: Beverage): Unit = with(binding) {
            tvBeverageName.text = beverage.name
            ivBeverage.loadImageWithGlide(placeholder)
        }
    }

    companion object {
        const val placeholder =
            "https://cdn.lifehacker.ru/wp-content/uploads/2021/05/shutterstock_1920949604_1622475394-scaled-e1622475457390-1280x640.jpg"

        val callback = object : DiffUtil.ItemCallback<Beverage>() {
            override fun areItemsTheSame(oldItem: Beverage, newItem: Beverage) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Beverage, newItem: Beverage) =
                oldItem == newItem
        }
    }
}