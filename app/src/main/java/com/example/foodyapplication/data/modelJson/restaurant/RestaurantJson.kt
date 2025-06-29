package com.example.foodyapplication.data.modelJson.restaurant

data class RestaurantJson<T>(
    val data: T,
    val message: String,
    val status: String,
    val results:Int,
)