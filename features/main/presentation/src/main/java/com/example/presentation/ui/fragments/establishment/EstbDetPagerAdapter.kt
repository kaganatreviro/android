package com.example.presentation.ui.fragments.establishment

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.domain.models.EstablishmentDetailsArg
import com.example.presentation.ui.fragments.establishment.feedback.FeedbackFragment
import com.example.presentation.ui.fragments.establishment.menu.MenuFragment

class EstbDetPagerAdapter(private val args: EstablishmentDetailsArg, fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) MenuFragment(args) else FeedbackFragment(args)
    }
}