package com.example.foodyapplication.ui.main.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodyapplication.R
import com.example.foodyapplication.base.fragment.BaseFragment
import com.example.foodyapplication.data.models.MenuItem
import com.example.foodyapplication.data.models.SettingItem
import com.example.foodyapplication.databinding.FragmentSettingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : BaseFragment() {

    private lateinit var binding: FragmentSettingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val menuItems = listOf(
            SettingItem.Section("CÀI ĐẶT TÀI KHOẢN"),
            SettingItem.Item("Thông tin & Liên hệ"),
            SettingItem.Item("Mật khẩu"),
            SettingItem.Section("Cài đặt ứng dụng"),
            SettingItem.Item("Đổi ngôn ngữ", "Tiếng Việt"),
            SettingItem.Item("Cài đặt thông báo"),
            SettingItem.Section("Hỗ Trợ"),
            SettingItem.Item("Yêu cầu xoá tài khoản")
        )

        binding.recyclerSettings.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = SettingAdapter(menuItems, ::listItemClicked)
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }

    }

    private fun listItemClicked(selectedItem: SettingItem) {
        if (selectedItem is SettingItem.Item) {
            if (selectedItem.title == "Mật khẩu") {
                navigateToPage(R.id.action_settingFragment_to_changePasswordFragment)
            } else if (selectedItem.title == "Thông tin & Liên hệ") {
                navigateToPage(R.id.action_settingFragment_to_userInfoFragment)
            }
        }
    }
}