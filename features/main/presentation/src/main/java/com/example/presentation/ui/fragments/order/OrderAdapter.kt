package com.example.presentation.ui.fragments.order

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.Order
import com.example.presentation.databinding.ItemOrderBinding

enum class OrderStatus{
    PENDING, CANCELLED, IN_PREPARATION, COMPLETED
}

class OrderAdapter(private val context: Context) : ListAdapter<Order, OrderAdapter.OrderViewHolder>(callback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = OrderViewHolder(
        ItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class OrderViewHolder(private val binding: ItemOrderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(order: Order): Unit = with(binding) {
            when(order.status){
                OrderStatus.CANCELLED.name.lowercase() -> {
                    tvStatus.background = AppCompatResources.getDrawable(context, com.example.core_ui.R.drawable.bg_order_status_cancelled)
                }
                OrderStatus.PENDING.name.lowercase() -> {
                    tvStatus.background = AppCompatResources.getDrawable(context, com.example.core_ui.R.drawable.bg_order_status_pending)
                }
                OrderStatus.IN_PREPARATION.name.lowercase() -> {
                    tvStatus.background = AppCompatResources.getDrawable(context, com.example.core_ui.R.drawable.bg_order_status_in_preparation)
                }
                OrderStatus.COMPLETED.name.lowercase() -> {
                    tvStatus.background = AppCompatResources.getDrawable(context, com.example.core_ui.R.drawable.bg_order_status_completed)
                }
            }
            tvBeverageName.text = order.beverageName
            tvEstablishmentName.text = order.establishmentName
            tvStatus.text = order.status
            tvCreateTime.text = order.orderDate
        }
    }

    companion object {
        val callback = object : DiffUtil.ItemCallback<Order>() {
            override fun areItemsTheSame(oldItem: Order, newItem: Order) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Order, newItem: Order) =
                oldItem == newItem
        }
    }
}