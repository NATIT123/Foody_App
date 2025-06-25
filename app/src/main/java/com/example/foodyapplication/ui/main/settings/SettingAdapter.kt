package com.example.foodyapplication.ui.main.settings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodyapplication.R
import com.example.foodyapplication.data.models.SettingItem

class SettingAdapter(private val items: List<SettingItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_SECTION = 0
        private const val TYPE_ITEM = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is SettingItem.Section -> TYPE_SECTION
            is SettingItem.Item -> TYPE_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_SECTION) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_setting_section, parent, false)
            SectionViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_setting_row, parent, false)
            ItemViewHolder(view)
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        if (holder is SectionViewHolder && item is SettingItem.Section) {
            holder.bind(item)
        } else if (holder is ItemViewHolder && item is SettingItem.Item) {
            holder.bind(item)
        }
    }

    inner class SectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvSection: TextView = itemView.findViewById(R.id.tvSection)
        fun bind(section: SettingItem.Section) {
            tvSection.text = section.title
        }
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private val tvSubtitle: TextView = itemView.findViewById(R.id.tvSubtitle)

        fun bind(item: SettingItem.Item) {
            tvTitle.text = item.title
            if (item.subtitle != null) {
                tvSubtitle.visibility = View.VISIBLE
                tvSubtitle.text = item.subtitle
            } else {
                tvSubtitle.visibility = View.GONE
            }

            itemView.setOnClickListener {
                // handle click
            }
        }
    }
}
