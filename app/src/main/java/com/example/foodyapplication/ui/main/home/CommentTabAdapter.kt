package com.example.foodyapplication.ui.main.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class CommentTabAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> NewestCommentFragment()
            1 -> MyCommentFragment()
            else -> NewestCommentFragment()
        }
    }
}
