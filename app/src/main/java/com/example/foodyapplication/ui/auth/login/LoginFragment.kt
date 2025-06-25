package com.example.foodyapplication.ui.auth.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.foodyapplication.R
import com.example.foodyapplication.activities.MainActivity
import com.example.foodyapplication.base.fragment.BaseFragment
import com.example.foodyapplication.common.AppSharePreference
import com.example.foodyapplication.common.EventObserver
import com.example.foodyapplication.common.TokenManager
import com.example.foodyapplication.databinding.FragmentLoginBinding
import com.example.foodyapplication.ui.auth.common.AuthViewModel
import com.example.foodyapplication.ui.main.common.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class LoginFragment : BaseFragment() {

    private val viewModel by viewModels<LoginViewModel>()

    private lateinit var binding: FragmentLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        viewModel.initUser()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.loginViewModel = viewModel
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_toolbar, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.tvForgotPassword.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
        }

        viewModel.loginUserSuccess.observe(viewLifecycleOwner, EventObserver { isSuccess ->
            if (isSuccess) {
                val intent = Intent(requireActivity(), MainActivity::class.java)
                startActivity(intent)
            }
        })
//        Listen exception event
        registerAllExceptionEvent(viewModel, viewLifecycleOwner)
        //when show loading is false, process bar is hide
        registerObserverLoadingEvent(viewModel, viewLifecycleOwner)
    }


}