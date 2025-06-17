package com.example.foodyapplication.ui.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.foodyapplication.R
import com.example.foodyapplication.data.models.MenuItem
import com.example.foodyapplication.data.models.PromoIcon
import com.example.foodyapplication.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var icons: List<PromoIcon>
    private lateinit var iconPagerAdapter: IconPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadData()
    }

    private fun loadData() {
        icons = listOf(
            PromoIcon(R.drawable.ic_help, "Mua 2 Tặng 2"),
            PromoIcon(R.drawable.ic_store, "Mart Giảm 80k"),
            PromoIcon(R.drawable.ic_help, "Ăn Chiều Giảm 40k"),
            PromoIcon(R.drawable.ic_voucher, "Freeship Xtra"),
            PromoIcon(R.drawable.ic_hide, "Ăn Ngon Rẻ"),
            PromoIcon(R.drawable.ic_share, "No Bụng Giảm 70K"),
            PromoIcon(R.drawable.ic_voucher, "Tặng món 1k"),
            PromoIcon(R.drawable.ic_share, "Deal 0Đ"),
            PromoIcon(
                R.drawable.ic_share, "Deal 0Đ"
            ),
            PromoIcon(R.drawable.ic_share, "Deal 0Đ"),
            PromoIcon(R.drawable.ic_share, "Deal 0Đ"),
            PromoIcon(R.drawable.ic_share, "Deal 0Đ")
        )
        val chunked = icons.chunked(8)
        iconPagerAdapter = IconPagerAdapter(requireContext(), chunked, ::listItemClicked)
        binding.viewPagerIcons.adapter = iconPagerAdapter

        // Indicator
        TabLayoutMediator(binding.tabIndicator, binding.viewPagerIcons) { _, _ -> }.attach()
    }

    private fun listItemClicked(selectedItem: PromoIcon) {
        Toast.makeText(
            requireContext(),
            "Selected name is ${selectedItem.title}",
            Toast.LENGTH_LONG
        ).show()
    }

}