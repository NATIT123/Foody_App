package com.example.foodyapplication.ui.main.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.foodyapplication.R
import com.example.foodyapplication.data.models.MenuItem
import com.example.foodyapplication.data.models.PromoIcon
import com.example.foodyapplication.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var icons: List<PromoIcon>
    private lateinit var iconPagerAdapter: IconPagerAdapter

    private val cities = listOf("TP. HCM", "Hà Nội", "Đà Nẵng")
    private val categories = listOf("Món Việt", "Món Hàn", "Ăn vặt", "Nước uống")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadData()

        setupSpinners()
        setupTabsWithViewPagerWithExplore()
        setupTabsWithViewPagerWithShouldEat()
        setupTabsWithViewPagerWithComment()

        //Slider Image
        val bannerImages = listOf(
            R.drawable.banner,
            R.drawable.banner,
            R.drawable.banner
        )

        val bannerAdapter = BannerAdapter(bannerImages)
        binding.bannerViewPager.adapter = bannerAdapter
        binding.bannerIndicator.setViewPager(binding.bannerViewPager)


        ////Auto Slide
        val handler = Handler(Looper.getMainLooper())
        val runnable = object : Runnable {
            override fun run() {
                val current = binding.bannerViewPager.currentItem
                val next = if (current == bannerImages.lastIndex) 0 else current + 1
                binding.bannerViewPager.setCurrentItem(next, true)
                handler.postDelayed(this, 5000)
            }
        }
        handler.postDelayed(runnable, 5000)


        ///Search
        binding.layoutSearch.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }
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

        //Detail Fragment
        binding.tvExplore.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
        }
    }

    private fun listItemClicked(selectedItem: PromoIcon) {
        Toast.makeText(
            requireContext(),
            "Selected name is ${selectedItem.title}",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun setupSpinners() {
        val cityAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, cities)
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCity.adapter = cityAdapter

        val categoryAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, categories)
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCategory.adapter = categoryAdapter

        binding.spinnerCity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(requireContext(), "City: ${cities[position]}", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        binding.spinnerCategory.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    Toast.makeText(
                        requireContext(),
                        "Category: ${categories[position]}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
    }

    private fun setupTabsWithViewPagerWithExplore() {
        val adapter = FoodTabAdapter(requireActivity())
        binding.viewPagerFood.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPagerFood) { tab, position ->
            tab.text = when (position) {
                0 -> "Mới nhất"
                1 -> "Gần tôi"
                2 -> "Bán chạy"
                3 -> "Đánh giá"
                else -> ""
            }
        }.attach()
    }

    private fun setupTabsWithViewPagerWithShouldEat() {
        val adapter = ExploreTabAdapter(requireActivity())
        binding.viewPagerExplore.adapter = adapter

        TabLayoutMediator(binding.tabLayoutExplore, binding.viewPagerExplore) { tab, position ->
            tab.text = when (position) {
                0 -> "Mới nhất"
                1 -> "Gần tôi"
                else -> ""
            }
        }.attach()
    }

    private fun setupTabsWithViewPagerWithComment() {
        val adapter = CommentTabAdapter(requireActivity())
        binding.viewPagerComment.adapter = adapter

        TabLayoutMediator(binding.tabLayoutComment, binding.viewPagerComment) { tab, position ->
            tab.text = when (position) {
                0 -> "Mới nhất"
                1 -> "Của tôi"
                else -> ""
            }
        }.attach()
    }


}