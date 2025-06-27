package com.example.foodyapplication.data.modelJson.user

data class User(

    val _id: String,
    val active: Boolean,
    var address: String,
    val confirmPassword: String,
    val createdAt: String,
    val email: String,
    var fullname: String,
    val password: String,
    var phone: String,
    var photo: String,
    val refreshToken: String,
    val role: String,
    val type: String,
    val updatedAt: String
)