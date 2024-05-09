package com.example.presentation.ui.fragments.establishment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.Menu
import com.example.presentation.databinding.ItemBeverageInMenuBinding

class EstablishmentMenuAdapter: ListAdapter<Menu, EstablishmentMenuAdapter.MenuViewHolder>(callback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MenuViewHolder(
        ItemBeverageInMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class MenuViewHolder(private val binding: ItemBeverageInMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(menu: Menu): Unit = with(binding) {
            tvName.text = menu.name
            tvPrice.text = menu.price.toString()
        }
    }

    companion object {
        val callback = object : DiffUtil.ItemCallback<Menu>() {
            override fun areItemsTheSame(oldItem: Menu, newItem: Menu) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Menu, newItem: Menu) =
                oldItem == newItem
        }
    }
}