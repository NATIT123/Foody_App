package com.example.foodyapplication.data.modelJson.notification

data class NotificationJson<T>(
    val data: T,
    val message: String,
    val status: String,
    val results:Int,
)