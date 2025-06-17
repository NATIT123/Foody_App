package com.example.foodyapplication.ui.main.settings

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foodyapplication.R
import com.example.foodyapplication.data.models.MenuItem
import com.example.foodyapplication.databinding.ItemAccountOptionBinding

class SettingsAdapter(
    private val items: List<MenuItem>,
    private val onItemClick: (MenuItem) -> Unit
) : RecyclerView.Adapter<SettingsAdapter.MenuViewHolder>() {

    sealed class MenuViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        class ItemViewHolder(private val binding: ItemAccountOptionBinding) :
            MenuViewHolder(binding.root) {
            fun bind(menuItem: MenuItem.Item, clickListener: (MenuItem) -> Unit) {
                binding.menuItem = menuItem
                binding.executePendingBindings()
                binding.layoutItem.setOnClickListener {
                    clickListener(menuItem)
                }
            }
        }

        class SeparatorViewHolder(view: View) : MenuViewHolder(view)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        return if (viewType == 0) {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding: ItemAccountOptionBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.item_account_option, parent, false)

            MenuViewHolder.ItemViewHolder(binding)
        } else {
            val view = View(parent.context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, 24
                )
                setBackgroundColor(Color.parseColor("#F2F2F2"))
            }
            MenuViewHolder.SeparatorViewHolder(view)
        }
    }


    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val item = items[position]
        when (holder) {
            is MenuViewHolder.ItemViewHolder -> {
                holder.bind(item as MenuItem.Item, onItemClick)
            }

            is MenuViewHolder.SeparatorViewHolder -> {

            }
        }
    }


    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is MenuItem.Item -> 0
            is MenuItem.Separator -> 1
        }
    }
}
