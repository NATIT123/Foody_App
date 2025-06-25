package com.example.foodyapplication.data.modelJson.user

data class UserJson<T>(
    val access_token: String,
    val data: T,
    val message: String,
    val status: String
)