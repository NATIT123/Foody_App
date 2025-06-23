package com.example.foodyapplication.ui.main.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.foodyapplication.R
import com.example.foodyapplication.databinding.FragmentAddAddressBinding


class AddAddressFragment : Fragment() {

    private lateinit var binding: FragmentAddAddressBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddAddressBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvPickAddress.setOnClickListener {
            findNavController().navigate(R.id.action_addAddressFragment_to_pickLocationFragment)
        }

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }


}