package com.example.foodyapplication.ui.main.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.foodyapplication.R
import com.example.foodyapplication.data.models.MenuItem
import com.example.foodyapplication.data.models.PromoIcon
import com.example.foodyapplication.databinding.ItemIconBinding

class IconPagerAdapter(
    private val context: Context,
    private val pages: List<List<PromoIcon>>,
    private val onItemClick: (PromoIcon) -> Unit
) : RecyclerView.Adapter<IconPagerAdapter.PageViewHolder>() {

    inner class PageViewHolder(val layout: GridLayout) : RecyclerView.ViewHolder(layout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_page, parent, false) as GridLayout
        return PageViewHolder(view)
    }

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        holder.layout.removeAllViews()
        val inflater = LayoutInflater.from(context)

        for (item in pages[position]) {
            val binding: ItemIconBinding = DataBindingUtil.inflate(
                inflater, R.layout.item_icon, holder.layout, false
            )

            binding.promoIcon = item
            binding.executePendingBindings()

            binding.layoutItem.setOnClickListener {
                onItemClick(item)
            }
            holder.layout.addView(binding.root)
        }
    }


    override fun getItemCount(): Int = pages.size
}
