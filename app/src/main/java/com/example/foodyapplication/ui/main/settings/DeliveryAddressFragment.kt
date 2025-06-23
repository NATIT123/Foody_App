package com.example.foodyapplication.ui.main.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.foodyapplication.R
import com.example.foodyapplication.databinding.FragmentDeliveryAddressBinding


class DeliveryAddressFragment : Fragment() {


    private lateinit var binding: FragmentDeliveryAddressBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDeliveryAddressBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addAddress.setOnClickListener {
            findNavController().navigate(R.id.action_deliveryAddressFragment_to_addAddressFragment)

        }

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }


}