package com.example.presentation.ui.fragments.profile.subscriptions

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.Plan
import com.example.presentation.R
import com.example.presentation.databinding.ItemSubscriptionPlanBinding

class SubscriptionAdapter(
    private val clickListener: ItemClickListener,
    private val subscriptionStatus: Boolean,
    private val subscriptionsPlanId: Int
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: MutableList<Plan> = mutableListOf()
    private var selectedPosition = -1 // Хранит индекс выбранного элемента

    interface ItemClickListener {
        fun onItemClick(item: Plan, index: Int)
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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is Holder -> {
                holder.bind(
                    items[position],
                    position,
                    subscriptionStatus,
                    position == selectedPosition,
                    planId = subscriptionsPlanId
                )
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

    class Holder private constructor(private val binding: ItemSubscriptionPlanBinding) :
        RecyclerView.ViewHolder(binding.root) {

        lateinit var item: Plan
        var index: Int = 0

        @RequiresApi(Build.VERSION_CODES.O)
        @SuppressLint("SetTextI18n", "CheckResult")
        fun bind(
            _item: Plan,
            position: Int,
            subscriptionStatus: Boolean,
            isSelected: Boolean,
            planId: Int
        ) {
            item = _item
            index = position

            item.apply {
                // Управление видимостью элементов
                binding.ivYourPlan.isVisible = item.id == planId
                binding.tvYourPlan.isVisible = item.id == planId
                itemView.isSelected = isSelected
                binding.tvPlanDesc.isVisible = isSelected
                binding.line1.isVisible = isSelected
                binding.rbChecked.isVisible = !subscriptionStatus
                binding.rbChecked.isChecked = isSelected // Установить состояние RadioButton
                binding.rbChecked.isEnabled = false
                binding.tvName.text = item.name
                binding.tvPriceAndDuration.text = item.price + " KGS / " + item.duration
                binding.tvPlanDesc.text = item.description
            }
        }

        companion object {
            fun create(parent: ViewGroup, clickListener: ItemClickListener): Holder {
                val binding = ItemSubscriptionPlanBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                return Holder(binding).apply {
                    itemView.setOnClickListener {
                        clickListener.onItemClick(item, index)
                    }
                }
            }
        }
    }

    fun selectItem(position: Int) {
        val previousPosition = selectedPosition
        selectedPosition = position
        notifyItemChanged(previousPosition)
        notifyItemChanged(selectedPosition)
    }
}