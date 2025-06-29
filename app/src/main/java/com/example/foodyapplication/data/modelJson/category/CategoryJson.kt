package com.example.foodyapplication.data.modelJson.category

data class CategoryJson<T>(
    val data: T,
    val message: String,
    val status: String,
    val results:Int,
)