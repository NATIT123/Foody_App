package com.example.foodyapplication.ui.main.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.foodyapplication.R
import com.example.foodyapplication.databinding.FragmentOrderBinding
import com.google.android.material.tabs.TabLayoutMediator


class OrderFragment : Fragment() {
    private lateinit var binding: FragmentOrderBinding
    private lateinit var ordersPagerAdapter: OrdersPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOrderBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ordersPagerAdapter = OrdersPagerAdapter(this)
        binding.viewPager.adapter = ordersPagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Đang đến"
                1 -> "Deal đã mua"
                2 -> "Lịch sử"
                3 -> "Đánh giá"
                4 -> "Đơn nạp"
                else -> ""
            }
        }.attach()
    }
}