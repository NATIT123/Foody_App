package com.example.foodyapplication.ui.main.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodyapplication.R
import com.example.foodyapplication.data.models.NotificationItem
import com.example.foodyapplication.databinding.FragmentNotificationBinding


class NotificationFragment : Fragment() {

    private lateinit var binding: FragmentNotificationBinding
    private lateinit var notificationAdapter: NotificationAdapter
    private lateinit var listNotifications: List<NotificationItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotificationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }


    private fun loadData() {
        listNotifications = listOf(
            NotificationItem(
                R.drawable.ic_voucher,
                "[HCMC] Trà sữa GIẢM 30.000Đ",
                "Gong Cha, Passio,...",
                "18/06/2025 13:30"
            ),
            NotificationItem(
                R.drawable.ic_voucher,
                "[DN, CT] Giảm TỚI 25.000Đ",
                "Nhập mã T4GIAM18...",
                "18/06/2025 10:30"
            )
        )

        notificationAdapter = NotificationAdapter(listNotifications)
        binding.rvNotifications.apply {
            adapter = notificationAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }


}