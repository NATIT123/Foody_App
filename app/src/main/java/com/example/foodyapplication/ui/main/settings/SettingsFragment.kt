package com.example.foodyapplication.ui.main.settings

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodyapplication.R
import com.example.foodyapplication.activities.AuthActivity
import com.example.foodyapplication.activities.MainActivity
import com.example.foodyapplication.base.dialogs.ConfirmDialog
import com.example.foodyapplication.base.fragment.BaseFragment
import com.example.foodyapplication.common.EventObserver
import com.example.foodyapplication.data.models.MenuItem
import com.example.foodyapplication.databinding.FragmentSettingsBinding
import com.example.foodyapplication.ui.auth.common.AuthViewModel

class SettingsFragment : BaseFragment() {

    private lateinit var binding: FragmentSettingsBinding
    private lateinit var settingsAdapter: SettingsAdapter
    private lateinit var menuItems: MutableList<MenuItem>

    private val authViewModel by activityViewModels<AuthViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.authViewModel = authViewModel
        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        getMenuItems()
        loadData()

        binding.btnLoginRegister.setOnClickListener {
            startActivity(Intent(requireContext(), AuthActivity::class.java))
        }

        binding.btnLogout.setOnClickListener {
            showConfirmLogoutDialog()
        }

        authViewModel.logoutSuccess.observe(viewLifecycleOwner, EventObserver { isSuccess ->
            if (isSuccess) {
                startActivity(Intent(requireContext(), AuthActivity::class.java))
                requireActivity().finish()
            }
        })


    }

    private fun showConfirmLogoutDialog() {
        val dialog = ConfirmDialog(
            requireContext(),
            object : ConfirmDialog.ConfirmCallback {
                override fun negativeAction() {

                }

                override fun positiveAction() {
                    authViewModel.logOut()
                }
            },
            title = "Đăng xuất",
            message = "Bạn có chắc chắn muốn đăng xuất khỏi ứng dụng?",
            positiveButtonTitle = "Đồng ý",
            negativeButtonTitle = "Huỷ"
        )
        dialog.show()
    }

    private fun getMenuItems() {
        val context = requireContext()

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
        ).toMutableList()

        authViewModel.user.observe(viewLifecycleOwner) { user ->
            if (user != null)
                menuItems.add(
                    MenuItem.Item(
                        R.drawable.ic_setting,
                        "Cài đặt",
                        ContextCompat.getColor(context, R.color.Blue)
                    )
                )
        }
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
            if (selectedItem.title == "Địa chỉ") {
                findNavController().navigate(R.id.action_settingsFragment_to_deliveryAddressFragment)
            }
            Toast.makeText(
                requireContext(),
                "Selected name is ${selectedItem.title}",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}