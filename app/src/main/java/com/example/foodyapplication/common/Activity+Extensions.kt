package com.example.foodyapplication.common

import android.app.Activity
import com.example.foodyapplication.CustomApplication

val Activity.customApplication: CustomApplication
get() = application as CustomApplication