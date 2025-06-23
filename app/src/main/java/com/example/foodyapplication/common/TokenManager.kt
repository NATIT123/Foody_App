package com.example.foodyapplication.common

interface TokenManager {
    fun saveToken(token: String)
    fun getToken(): String?
    fun clearToken()
}
