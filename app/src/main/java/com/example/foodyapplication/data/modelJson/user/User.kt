package com.example.foodyapplication.data.modelJson.user

data class User(

    val _id: String,
    val active: Boolean,
    val address: String,
    val confirmPassword: String,
    val createdAt: String,
    val email: String,
    val fullname: String,
    val password: String,
    val phone: String,
    var photo: String,
    val refreshToken: String,
    val role: String,
    val type: String,
    val updatedAt: String
)