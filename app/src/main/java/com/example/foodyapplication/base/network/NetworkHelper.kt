package com.example.foodyapplication.base.network

import com.example.foodyapplication.common.DataLocal


object NetworkHelper {

    fun getDefaultHeader(): Map<String, String> {
        val headers = mutableMapOf<String, String>()
        headers["Content-Type"] = "application/json"
//        headers["Authorization"] = "Bearer ${BuildConfig.ACCESS_TOKEN}"
        return headers.toMap()
    }

    fun getDefaultHeaderForUser(): Map<String, String> {
        val headers = mutableMapOf<String, String>()
        headers["Content-Type"] = "application/json"
        headers["Authorization"] = "Bearer ${DataLocal.USER_TOKEN}"
        return headers.toMap()
    }

}