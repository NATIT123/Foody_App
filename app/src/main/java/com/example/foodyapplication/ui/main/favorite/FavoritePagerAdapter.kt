package com.example.foodyapplication.ui.main.favorite

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class FavoritePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FavoriteLatestFragment()
            1 -> FavoriteNearbyFragment()
            else -> FavoriteLatestFragment()
        }
    }
}
