package com.example.foodyapplication.data.models

sealed class SettingItem {
    data class Section(val title: String) : SettingItem()
    data class Item(val title: String, val subtitle: String? = null) : SettingItem()
}
