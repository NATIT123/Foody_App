package com.example.foodyapplication.common

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenManagerImpl @Inject constructor(
    private val appSharePreference: AppSharePreference
) : TokenManager {

    override fun saveToken(token: String) {
        appSharePreference.saveToken(token)
        DataLocal.USER_TOKEN = token
    }

    override fun getToken(): String? {
        val token = appSharePreference.getToken()
        DataLocal.USER_TOKEN = token ?: ""
        return token
    }

    override fun clearToken() {
        appSharePreference.clearToken()
        DataLocal.USER_TOKEN = ""
    }
}
