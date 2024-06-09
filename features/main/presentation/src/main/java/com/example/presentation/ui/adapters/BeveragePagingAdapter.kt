package com.example.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.core_ui.extensions.gone
import com.example.domain.models.Beverage
import com.example.presentation.databinding.ItemBeverageBinding

class BeveragePagingAdapter(
    private val onItemClick: (id: Int) -> Unit
) :
    PagingDataAdapter<Beverage, BeveragePagingAdapter.ProductsViewHolder>(diffCallBack) {

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProductsViewHolder(
        ItemBeverageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    inner class ProductsViewHolder(private val binding: ItemBeverageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(beverage: Beverage) = with(binding) {
            btnGetBeverage.gone()
            tvName.text = beverage.name
            tvEstablishmentName.text = beverage.establishment
        }
        init {
            binding.root.setOnClickListener {
                getItem(absoluteAdapterPosition)?.let { onItemClick(it.id) }
            }
        }
    }

    companion object {
        val diffCallBack = object : DiffUtil.ItemCallback<Beverage>() {
            override fun areItemsTheSame(oldItem: Beverage, newItem: Beverage): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Beverage, newItem: Beverage): Boolean {
                return oldItem == newItem
            }
        }
    }
}