package com.example.foodyapplication.common

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppSharePreference @Inject constructor(
    @ApplicationContext context: Context
) {

    companion object {
        private const val PREF_NAME = "foody_prefs"
        private const val KEY_ACCESS_TOKEN = "access_token"
    }

    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        prefs.edit().putString(KEY_ACCESS_TOKEN, token).apply()

    }

    fun getToken(): String? {
        return prefs.getString(KEY_ACCESS_TOKEN, null)
    }

    fun clearToken() {
        prefs.edit().remove(KEY_ACCESS_TOKEN).apply()
    }
}
