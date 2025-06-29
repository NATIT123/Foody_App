package com.example.foodyapplication.data.modelJson.district

data class DistrictJson<T>(
    val data: T,
    val message: String,
    val status: String,
    val results:Int,
)