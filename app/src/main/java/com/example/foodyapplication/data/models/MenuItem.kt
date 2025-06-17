package com.example.foodyapplication.data.models

sealed class MenuItem {
    data class Item(val icon: Int, val title: String, val color: Int) : MenuItem()
    data object Separator : MenuItem()
}

