package com.example.foodyapplication.ui.main.settings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodyapplication.R

class AddressSuggestionAdapter(
    private var addresses: List<String>,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<AddressSuggestionAdapter.AddressViewHolder>() {

    fun updateData(newAddresses: List<String>) {
        addresses = newAddresses
        notifyDataSetChanged()
    }

    inner class AddressViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvAddress: TextView = view.findViewById(R.id.tvAddress)

        init {
            view.setOnClickListener {
                val address = addresses[adapterPosition]
                onItemClick(address)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_address_suggestion, parent, false)
        return AddressViewHolder(view)
    }

    override fun getItemCount(): Int = addresses.size

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        holder.tvAddress.text = addresses[position]
    }
}