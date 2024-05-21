package com.example.presentation.ui.fragments.establishment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.Menu
import com.example.presentation.databinding.ItemBeverageBinding
import com.example.presentation.ui.fragments.qr_scranner.QRScannerFragment

class EstablishmentMenuAdapter(
    private val context: Context,
    private val onItemClick: (beverageId: Int) -> Unit,
    private val onGetForFreeBtnClick: (beverageId: Int) -> Unit
) : ListAdapter<Menu, EstablishmentMenuAdapter.MenuViewHolder>(callback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MenuViewHolder(
        ItemBeverageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class MenuViewHolder(private val binding: ItemBeverageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var flag = EstablishmentDetailFragment().paymentAction
        fun onBind(menu: Menu): Unit = with(binding) {
            tvName.text = menu.name
            tvPrice.text = menu.price.toString()
            binding.btnGetBeverage.isEnabled = flag
        }

        init {
            binding.root.setOnClickListener {
                onItemClick(getItem(adapterPosition).id)
            }
            binding.btnGetBeverage.setOnClickListener {
                onGetForFreeBtnClick(getItem(adapterPosition).id)
            }
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