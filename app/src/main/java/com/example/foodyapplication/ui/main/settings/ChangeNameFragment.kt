package com.example.foodyapplication.ui.main.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.foodyapplication.base.fragment.BaseFragment
import com.example.foodyapplication.common.EventObserver
import com.example.foodyapplication.databinding.FragmentChangeNameBinding
import com.example.foodyapplication.ui.auth.common.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangeNameFragment : BaseFragment() {

    private lateinit var binding: FragmentChangeNameBinding


    private val settingsViewModel by activityViewModels<SettingsViewModel>()

    private val authViewModel by activityViewModels<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authViewModel.user.value?.let { settingsViewModel.initUserFullName(it.address) }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentChangeNameBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.authViewModel = authViewModel
        binding.settingsViewModel = settingsViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        settingsViewModel.updateNameSuccess.observe(viewLifecycleOwner, EventObserver { isSuccess ->
            if (isSuccess) {
                val fullName = settingsViewModel.currentUser.value?.fullname
                val currentUser = authViewModel.user.value
                if (fullName != null) {
                    currentUser?.fullname = fullName
                    if (currentUser != null) {
                        authViewModel.setUser(currentUser)
                        findNavController().popBackStack()
                    }
                }
            }
        })

    }
}