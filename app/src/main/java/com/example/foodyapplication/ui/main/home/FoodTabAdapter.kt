package com.example.foodyapplication.ui.main.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class FoodTabAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> NewestFragment()
            1 -> NearMeFragment()
            2 -> BestSellersFragment()
            3 -> TopRatedFragment()
            else -> NewestFragment()
        }
    }
}
