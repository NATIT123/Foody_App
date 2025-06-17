package com.example.foodyapplication.ui.main.settings

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodyapplication.R
import com.example.foodyapplication.data.models.MenuItem
import com.example.foodyapplication.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding
    private lateinit var settingsAdapter: SettingsAdapter
    private lateinit var menuItems: List<MenuItem>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        getMenuItems()
        loadData()

    }

    private fun getMenuItems() {
        val context = requireContext() // hoặc `context!!` nếu chắc chắn không null

        menuItems = listOf(
            MenuItem.Item(
                R.drawable.ic_voucher,
                "Ví Voucher",
                ContextCompat.getColor(context, R.color.shopee_orange)
            ),
            MenuItem.Separator,

            MenuItem.Item(
                R.drawable.ic_payment,
                "Thanh toán",
                ContextCompat.getColor(context, R.color.Red)
            ),
            MenuItem.Item(
                R.drawable.ic_location,
                "Địa chỉ",
                ContextCompat.getColor(context, R.color.Green)
            ),
            MenuItem.Separator,

            MenuItem.Item(
                R.drawable.ic_share,
                "Mời bạn bè",
                ContextCompat.getColor(context, R.color.Blue)
            ),
            MenuItem.Item(
                R.drawable.ic_store,
                "Ứng dụng cho chủ quán",
                ContextCompat.getColor(context, R.color.shopee_orange)
            ),
            MenuItem.Separator,

            MenuItem.Item(
                R.drawable.ic_help,
                "Trung tâm Trợ giúp",
                ContextCompat.getColor(context, R.color.Teal)
            ),
            MenuItem.Item(
                R.drawable.ic_policy,
                "Chính sách",
                ContextCompat.getColor(context, R.color.Green)
            ),
            MenuItem.Item(
                R.drawable.ic_about,
                "Về ShopeeFood",
                ContextCompat.getColor(context, R.color.shopee_orange)
            )
        )
    }


    private fun loadData() {
        if (menuItems.isNotEmpty()) {
            settingsAdapter = SettingsAdapter(menuItems, ::listItemClicked)
            binding.recyclerMenu.apply {
                layoutManager = LinearLayoutManager(requireContext())
                addItemDecoration(
                    DividerItemDecoration(
                        requireContext(),
                        DividerItemDecoration.VERTICAL
                    )
                )
                adapter = settingsAdapter
            }
        }

    }

    private fun listItemClicked(selectedItem: MenuItem) {
        if (selectedItem is MenuItem.Item) {
            Toast.makeText(
                requireContext(),
                "Selected name is ${selectedItem.title}",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}