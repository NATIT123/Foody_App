package com.example.foodyapplication.ui.main.settings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodyapplication.R
import com.example.foodyapplication.data.models.UserInfoItem

class UserInfoAdapter(
    private val items: List<UserInfoItem>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_FIELD = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is UserInfoItem.Header -> TYPE_HEADER
            is UserInfoItem.Field -> TYPE_FIELD
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_HEADER) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_user_info_header, parent, false)
            HeaderViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_user_info_field, parent, false)
            FieldViewHolder(view)
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = items[position]) {
            is UserInfoItem.Header -> (holder as HeaderViewHolder).bind(item)
            is UserInfoItem.Field -> (holder as FieldViewHolder).bind(item)
        }
    }

    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val avatar: TextView = itemView.findViewById(R.id.avatar)
        private val changeAvatar: TextView = itemView.findViewById(R.id.tvChangeAvatar)

        fun bind(item: UserInfoItem.Header) {
            avatar.text = item.username.take(2).lowercase()
            changeAvatar.setOnClickListener {
                // Show avatar picker
            }
        }
    }

    inner class FieldViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val label: TextView = itemView.findViewById(R.id.tvLabel)
        private val value: TextView = itemView.findViewById(R.id.tvValue)
        private val arrow: ImageView = itemView.findViewById(R.id.imageView)

        fun bind(item: UserInfoItem.Field) {
            label.text = item.label
            value.text = item.value ?: "Cập nhật ngay"
            arrow.visibility = if (item.editable) View.VISIBLE else View.INVISIBLE

            itemView.setOnClickListener {
                // Handle click to edit if editable
            }
        }
    }
}
