package com.example.presentation.ui.fragments.establishment.menu

import android.annotation.SuppressLint
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core_ui.base.BaseFragment
import com.example.core_ui.extensions.showSimpleDialog
import com.example.domain.models.Beverage
import com.example.domain.models.EstablishmentDetailsArg
import com.example.domain.models.Menu
import com.example.domain.models.OrderRequest
import com.example.presentation.databinding.FragmentMenuBinding
import com.example.presentation.ui.fragments.establishment.EstablishmentDetailFragmentDirections
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuFragment(private val args: EstablishmentDetailsArg): BaseFragment<FragmentMenuBinding, MenuViewModel>(), EstablishmentMenuAdapter.ItemClickListener  {
    override fun getViewBinding() = FragmentMenuBinding.inflate(layoutInflater)
    override val viewModel by viewModel<MenuViewModel>()
    private lateinit var categories: List<Menu>
    private lateinit var groupedEvents: Map<String, List<Menu>>
    private var menuAdapter: EstablishmentMenuAdapter? = null

    @SuppressLint("SetTextI18n")
    override fun initialize(): Unit = with(binding) {
        rvBeveragesMenu.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        getEstablishmentMenuById()
    }

    private fun getEstablishmentMenuById() {
        val param = args.establishmentId
        viewModel.getEstablishmentMenuById(param)
    }

    @SuppressLint("NotifyDataSetChanged", "SetTextI18n")
    override fun launchObservers() = with(binding) {
        viewModel.establishmentMenuState.spectateUiState(
            success = { menuList ->
                categories = menuList
                groupedEvents = categories.groupBy { it.category }
                setupAdapter(groupedEvents)
            },
            error = {
                groupedEvents = emptyMap<String, List<Menu>>().toMap()
                setupAdapter(groupedEvents)
                showSimpleDialog("Error", resources.getString(com.example.core_ui.R.string.not_menu_error))
            },
            showLoader = false
        )

        viewModel.orderState.spectateUiState(
            success = {
                showSimpleDialog("Success", "")
            },
            error = {
                showSimpleDialog(it, "")
            }
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupAdapter(groupedList: Map<String, List<Menu>>){
        menuAdapter = EstablishmentMenuAdapter(
            args.enabledButton,
            this@MenuFragment,
            groupedList
        )
        binding.rvBeveragesMenu.adapter = menuAdapter
        menuAdapter!!.notifyDataSetChanged()
    }

    override fun onItemClick(beverage: Beverage) {
        findNavController().navigate(
            EstablishmentDetailFragmentDirections.actionEstablishmentDetailFragmentToBeverageDetailsFragment(
                beverage
            )
        )
    }

    override fun onBuyBtnClick(beverageId: Int) {
        val param = OrderRequest(beverageId)
        viewModel.makeOrder(param)
    }
}