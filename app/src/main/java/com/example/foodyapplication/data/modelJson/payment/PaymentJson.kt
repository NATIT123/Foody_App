package com.example.foodyapplication.data.modelJson.payment

data class PaymentJson<T>(
    val data: T,
    val message: String,
    val status: String,
    val results:Int,
)