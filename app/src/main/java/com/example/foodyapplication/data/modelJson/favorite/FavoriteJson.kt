package com.example.foodyapplication.data.modelJson.favorite

data class FavoriteJson<T>(
    val data: T,
    val message: String,
    val status: String,
    val results:Int,
)