package com.example.foodyapplication.ui.main.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.foodyapplication.base.fragment.BaseFragment
import com.example.foodyapplication.common.EventObserver
import com.example.foodyapplication.databinding.FragmentChangeAddressBinding
import com.example.foodyapplication.databinding.FragmentChangeNameBinding
import com.example.foodyapplication.ui.auth.common.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangeAddressFragment : BaseFragment() {

    private lateinit var binding: FragmentChangeAddressBinding


    private val settingsViewModel by activityViewModels<SettingsViewModel>()

    private val authViewModel by activityViewModels<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authViewModel.user.value?.let { settingsViewModel.initUserAddress(it.address) }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentChangeAddressBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.authViewModel = authViewModel
        binding.settingsViewModel = settingsViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        settingsViewModel.updateAddressSuccess.observe(viewLifecycleOwner, EventObserver { isSuccess ->
            if (isSuccess) {
                val address = settingsViewModel.currentUser.value?.address
                val currentUser = authViewModel.user.value
                if (address != null) {
                    currentUser?.address = address
                    if (currentUser != null) {
                        authViewModel.setUser(currentUser)
                        findNavController().popBackStack()
                    }
                }
            }
        })

    }
}