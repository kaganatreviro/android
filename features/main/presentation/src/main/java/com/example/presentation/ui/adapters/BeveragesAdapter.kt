package com.example.presentation.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.core_ui.extensions.gone
import com.example.core_ui.extensions.visible
import com.example.domain.models.Beverage
import com.example.core_ui.R
import com.example.presentation.databinding.ItemBeverageBinding

class BeveragesAdapter(
    private val context: Context,
    private val onItemClick: (beverageId: Int) -> Unit
) : ListAdapter<Beverage, BeveragesAdapter.BeverageViewHolder>(callback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BeverageViewHolder(
        ItemBeverageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: BeverageViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class BeverageViewHolder(private val binding: ItemBeverageBinding) :
        ViewHolder(binding.root) {

        fun onBind(beverage: Beverage): Unit = with(binding) {
            val availabilityStatus = if (beverage.availabilityStatus) {
                tvAvailabilityStatus.isEnabled = true
                context.getString(R.string.status_available)
            } else {
                tvAvailabilityStatus.isEnabled = false
                context.getString(R.string.status_unavailable)
            }
            tvAvailabilityStatus.visible()
            btnGetBeverage.gone()
            tvAvailabilityStatus.text = availabilityStatus
            tvName.text = beverage.name
            tvPrice.text = beverage.price
        }
        init {
            binding.root.setOnClickListener {
                onItemClick(getItem(adapterPosition).id)
            }
        }
    }

    companion object {
        val callback = object : DiffUtil.ItemCallback<Beverage>() {
            override fun areItemsTheSame(oldItem: Beverage, newItem: Beverage) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Beverage, newItem: Beverage) =
                oldItem == newItem
        }
    }
}