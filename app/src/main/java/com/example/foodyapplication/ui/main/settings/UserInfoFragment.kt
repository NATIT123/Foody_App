package com.example.foodyapplication.ui.main.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodyapplication.base.fragment.BaseFragment
import com.example.foodyapplication.data.models.UserInfoItem
import com.example.foodyapplication.databinding.FragmentUserInfoBinding
import com.example.foodyapplication.ui.auth.common.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserInfoFragment : BaseFragment() {

    private lateinit var binding: FragmentUserInfoBinding

    private val authViewModel by activityViewModels<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val user = authViewModel.user.value!! // replace with real user

        val infoItems = listOf(
            UserInfoItem.Header(user.fullname, user.photo),
            UserInfoItem.Field("Tên đăng nhập", user.fullname, false),
            UserInfoItem.Field("Số điện thoại", user.phone),
            UserInfoItem.Field("Tên", user.fullname),
            UserInfoItem.Field("Email", user.email),
            UserInfoItem.Field("Giới tính", user.fullname ?: "Cập nhật ngay"),
            UserInfoItem.Field("Ngày sinh", user.fullname ?: "Cập nhật ngay"),
            UserInfoItem.Field("Nghề nghiệp", user.fullname ?: "Cập nhật ngay")
        )

        binding.recyclerUserInfo.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = UserInfoAdapter(infoItems)
        }
    }
}
