package com.example.presentation.ui.fragments.establishment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.Menu
import com.example.presentation.R
import com.example.presentation.databinding.ItemBeverageBinding
import com.example.presentation.databinding.ItemBeverageCategoryTitleBinding

class EstablishmentMenuAdapter(
    private val enabledAction: Boolean,
    private val itemClickListener: ItemClickListener,
    private val groupedCategory: Map<String, List<Menu>>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val categories = groupedCategory.keys.toList()

    interface ItemClickListener {
        fun onItemClick(beverageId: Int)
        fun onBuyBtnClick(beverageId: Int)
    }

    override fun getItemCount(): Int {
        var itemCount = 0
        groupedCategory.values.forEach { itemCount += it.size + 1 } // +1 for the date header
        return itemCount
    }

    override fun getItemViewType(position: Int): Int {
        // Define view types for headers and items
        if (categories.isEmpty()) return VIEW_TYPE_EMPTY
        else {
            var currentPosition = 0
            categories.forEach { category ->
                if (position == currentPosition) return VIEW_TYPE_HEADER
                currentPosition++
                val menuList = groupedCategory[category] ?: emptyList()
                menuList.forEach {
                    if (position == currentPosition) return VIEW_TYPE_ITEM
                    currentPosition++
                }
            }
            throw IllegalStateException("Invalid position")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            VIEW_TYPE_EMPTY -> EmptyHolder.create(parent)
            VIEW_TYPE_ITEM -> MenuItemViewHolder.create(parent, itemClickListener)
            VIEW_TYPE_HEADER -> CategoryHeaderViewHolder.create(parent)
            else -> throw IllegalStateException("Unknown view")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (categories.isNotEmpty()) {
            var currentPosition = 0
            categories.forEach { category ->
                if (currentPosition == position) {
                    (holder as CategoryHeaderViewHolder).bind(category)
                    return
                }
                currentPosition++
                val menuList = groupedCategory[category] ?: emptyList()
                menuList.forEach { menuItem ->
                    if (currentPosition == position) {
                        (holder as MenuItemViewHolder).bind(menuItem, position, enabledAction)
                        return
                    }
                    currentPosition++
                }
            }
        }
    }

    class EmptyHolder(view: View) : RecyclerView.ViewHolder(view) {
        companion object {
            fun create(parent: ViewGroup): EmptyHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.empty_fragment, parent, false)
                return EmptyHolder(view)
            }
        }
    }

    class CategoryHeaderViewHolder(private val binding: ItemBeverageCategoryTitleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: String) {
            binding.tvCategoryTitle.text = category.toString() // Format as needed
        }

        companion object {
            fun create(parent: ViewGroup): CategoryHeaderViewHolder {
                val binding = ItemBeverageCategoryTitleBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                return CategoryHeaderViewHolder(binding)
            }
        }
    }

    class MenuItemViewHolder(private val binding: ItemBeverageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        lateinit var item: Menu
        var index: Int = 0

        fun bind(menu: Menu, position: Int, enabledAction: Boolean) {
            binding.btnGetBeverage.isEnabled = enabledAction
            item = menu
            index = position

            item.apply {
                binding.tvPrice.text = item.price.toString()
                binding.tvName.text = item.name
            }
        }

        companion object {
            fun create(parent: ViewGroup, clickListener: ItemClickListener): MenuItemViewHolder {
                val binding = ItemBeverageBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                return MenuItemViewHolder(binding).apply {
                    itemView.setOnClickListener {
                        clickListener.onItemClick(item.id)
                    }
                    binding.btnGetBeverage.setOnClickListener {
                        clickListener.onBuyBtnClick(item.id)
                    }
                }
            }
        }
    }

    companion object {
        private val VIEW_TYPE_HEADER = 1
        private val VIEW_TYPE_ITEM = 2
        private val VIEW_TYPE_EMPTY = 0
    }
}