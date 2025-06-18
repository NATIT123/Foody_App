package com.example.foodyapplication.ui.main.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class DetailTabAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> MenuFragment()
        1 -> PhotosFragment()
        2 -> CommentsFragment()
        else -> MapFragment()
    }
}