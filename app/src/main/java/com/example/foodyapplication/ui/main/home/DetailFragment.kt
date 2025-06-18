package com.example.foodyapplication.ui.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.foodyapplication.R
import com.example.foodyapplication.databinding.FragmentDetailBinding
import com.google.android.material.tabs.TabLayoutMediator


class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTabsWithViewPagerWithDetail()
    }


    private fun setupTabsWithViewPagerWithDetail() {
        val adapter = DetailTabAdapter(requireActivity())
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Thực đơn"
                1 -> "Ảnh & video"
                2 -> "Bình luận"
                else -> "Bản đồ"
            }
        }.attach()
    }
}