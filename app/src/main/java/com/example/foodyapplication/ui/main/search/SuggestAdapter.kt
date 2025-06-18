package com.example.foodyapplication.ui.main.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodyapplication.R

class SuggestAdapter(private val items: List<Pair<String, Int>>) :
    RecyclerView.Adapter<SuggestAdapter.SuggestViewHolder>() {

    class SuggestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgFood: ImageView = itemView.findViewById(R.id.imgFood)
        val txtFoodName: TextView = itemView.findViewById(R.id.txtFoodName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuggestViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_suggest_food, parent, false)
        return SuggestViewHolder(view)
    }

    override fun onBindViewHolder(holder: SuggestViewHolder, position: Int) {
        val (name, imgRes) = items[position]
        holder.imgFood.setImageResource(imgRes)
        holder.txtFoodName.text = name
    }

    override fun getItemCount(): Int = items.size
}
