package com.example.foodyapplication.data.modelJson.city

data class CityJson<T>(
    val data: T,
    val message: String,
    val status: String,
    val results:Int,
)