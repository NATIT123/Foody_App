package com.example.foodyapplication.common

import android.util.Log
import com.example.foodyapplication.BuildConfig

object Logger {

    fun log(tag: String, message: String) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, message)
        }
    }

}