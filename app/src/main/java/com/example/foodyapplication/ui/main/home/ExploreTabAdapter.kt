package com.example.foodyapplication.ui.main.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ExploreTabAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ExploreDealsFragment()
            1 -> ExploreRestaurantsFragment()
            else -> ExploreDealsFragment()
        }
    }
}