package com.example.foodyapplication.ui.main.order

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class OrdersPagerAdapter(activity: Fragment) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ComingOrderFragment()
            1 -> PlaceholderFragment("Deal đã mua")
            2 -> PlaceholderFragment("Lịch sử")
            3 -> PlaceholderFragment("Đánh giá")
            4 -> PlaceholderFragment("Đơn nạp")
            else -> PlaceholderFragment("Không xác định")
        }
    }
}
