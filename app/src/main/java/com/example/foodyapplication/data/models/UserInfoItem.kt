package com.example.foodyapplication.data.models

sealed class UserInfoItem {
    data class Header(val username: String, val avatarUrl: String?) : UserInfoItem()
    data class Field(
        val icon: Int,
        val label: String,
        val value: String?,
        val editable: Boolean = true
    ) :
        UserInfoItem()
}
