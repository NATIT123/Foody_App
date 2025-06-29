package com.example.foodyapplication.data.modelJson.food

data class FoodJson<T>(
    val data: T,
    val message: String,
    val status: String,
    val results:Int,
)