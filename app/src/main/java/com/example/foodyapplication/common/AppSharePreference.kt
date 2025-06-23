package com.example.foodyapplication.common

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppSharePreference @Inject constructor(@ApplicationContext private val context: Context) {
    companion object {
        private const val APP_SHARE_KEY = "com.example.foodyapplication"
        private const val KEY_ACCESS_TOKEN = "access_token"
    }

    private fun getSharedPreferences(): SharedPreferences? {
        return context.getSharedPreferences(APP_SHARE_KEY, Context.MODE_PRIVATE)
    }

    fun saveToken(token: String) {
        val prefs = getSharedPreferences()
        prefs?.edit()?.putString(KEY_ACCESS_TOKEN, token)?.apply()
    }


    fun getToken(): String? {
        val prefs = getSharedPreferences()
        return prefs?.getString(KEY_ACCESS_TOKEN, null)
    }

    fun clearToken() {
        val prefs = getSharedPreferences()
        prefs?.edit()?.clear()?.apply()
    }


}