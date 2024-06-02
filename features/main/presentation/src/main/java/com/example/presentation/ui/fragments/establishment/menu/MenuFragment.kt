package com.example.presentation.ui.fragments.establishment.menu

import android.annotation.SuppressLint
import android.util.Log
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core_ui.base.BaseFragment
import com.example.core_ui.extensions.showShortToast
import com.example.core_ui.extensions.showSimpleDialog
import com.example.domain.models.Menu
import com.example.domain.models.OrderRequest
import com.example.presentation.databinding.FragmentMenuBinding
import com.example.presentation.ui.fragments.establishment.EstablishmentDetailFragmentArgs
import com.example.presentation.ui.fragments.establishment.EstablishmentDetailFragmentDirections
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuFragment(private val args: EstablishmentDetailFragmentArgs): BaseFragment<FragmentMenuBinding, MenuViewModel>(), EstablishmentMenuAdapter.ItemClickListener  {
    override fun getViewBinding() = FragmentMenuBinding.inflate(layoutInflater)
    override val viewModel by viewModel<MenuViewModel>()

    private lateinit var categories: List<Menu>
    private lateinit var groupedEvents: Map<String, List<Menu>>
    private lateinit var menuAdapter: EstablishmentMenuAdapter

    @SuppressLint("SetTextI18n")
    override fun initialize(): Unit = with(binding) {
        rvBeveragesMenu.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        getEstablishmentMenuById()
    }

    override fun setupListeners() {
//        binding.swipeRef.setOnRefreshListener { getEstablishmentMenuById() }
    }

    private fun getEstablishmentMenuById() {
        val param = args.establishmentId
        viewModel.getEstablishmentMenuById(param)
    }

    @SuppressLint("NotifyDataSetChanged", "SetTextI18n")
    override fun launchObservers() = with(binding) {
        viewModel.establishmentMenuState.spectateUiState(
            success = { menuList ->
//                binding.swipeRef.isRefreshing = false
                categories = menuList
                groupedEvents = categories.groupBy { it.category }
                menuAdapter = EstablishmentMenuAdapter(
                    args.enabledButton,
                    this@MenuFragment,
                    groupedEvents
                )
                rvBeveragesMenu.adapter = menuAdapter
            },
            error = {
//                binding.swipeRef.isRefreshing = false
                showShortToast(it)
            },
            showLoader = false
        )

        viewModel.orderState.spectateUiState(
            success = {
                showSimpleDialog("Success", "")
            },
            error = {
                Log.d("error", "error - $it")
                showSimpleDialog(it, "")
            }
        )
    }

    override fun onItemClick(beverageId: Int) {
        findNavController().navigate(
            EstablishmentDetailFragmentDirections.actionEstablishmentDetailFragmentToBeverageDetailsFragment(
                beverageId
            )
        )
    }

    override fun onBuyBtnClick(beverageId: Int) {
        val param = OrderRequest(beverageId)
        viewModel.makeOrder(param)
    }
}