package com.example.presentation.ui.fragments.order

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.Order
import com.example.presentation.databinding.ItemOrderBinding
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

enum class OrderStatus{
    PENDING, CANCELLED, IN_PREPARATION, COMPLETED
}

class OrderAdapter(private val context: Context) : ListAdapter<Order, OrderAdapter.OrderViewHolder>(callback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = OrderViewHolder(
        ItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class OrderViewHolder(private val binding: ItemOrderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @RequiresApi(Build.VERSION_CODES.O)
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX")
        @RequiresApi(Build.VERSION_CODES.O)
        val displayFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy:MM:dd, HH:mm")

        @RequiresApi(Build.VERSION_CODES.O)
        fun onBind(order: Order): Unit = with(binding) {
            when(order.status){
                OrderStatus.CANCELLED.name.lowercase() -> {
                    tvStatus.text = "Cancelled"
                    tvStatus.background = AppCompatResources.getDrawable(context, com.example.core_ui.R.drawable.bg_order_status_cancelled)
                }
                OrderStatus.PENDING.name.lowercase() -> {
                    tvStatus.text = "Pending"
                    tvStatus.background = AppCompatResources.getDrawable(context, com.example.core_ui.R.drawable.bg_order_status_pending)
                }
                OrderStatus.IN_PREPARATION.name.lowercase() -> {
                    tvStatus.text = "In preparation"
                    tvStatus.background = AppCompatResources.getDrawable(context, com.example.core_ui.R.drawable.bg_order_status_in_preparation)
                }
                OrderStatus.COMPLETED.name.lowercase() -> {
                    tvStatus.text = "Completed"
                    tvStatus.background = AppCompatResources.getDrawable(context, com.example.core_ui.R.drawable.bg_order_status_completed)
                }
            }
            tvBeverageName.text = order.beverageName
            tvEstablishmentName.text = order.establishmentName

            val dateTime = LocalDateTime.parse(order.orderDate, formatter)
            tvCreateTime.text = dateTime.format(displayFormatter)
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