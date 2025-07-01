package com.example.foodyapplication.data.services

import com.example.foodyapplication.base.network.BaseRemoteService
import com.example.foodyapplication.base.network.NetworkResult
import com.example.foodyapplication.data.apis.CategoryAPI
import com.example.foodyapplication.data.apis.CityAPI
import com.example.foodyapplication.data.apis.NotificationAPI
import com.example.foodyapplication.data.apis.UserAPI
import com.example.foodyapplication.data.modelJson.category.CategoryJson
import com.example.foodyapplication.data.modelJson.category.DataCategory
import com.example.foodyapplication.data.modelJson.city.CityJson
import com.example.foodyapplication.data.modelJson.city.DataCity
import com.example.foodyapplication.data.modelJson.notification.DataNotification
import com.example.foodyapplication.data.modelJson.notification.NotificationJson
import com.example.foodyapplication.data.modelJson.user.DataLogin
import com.example.foodyapplication.data.modelJson.user.DataPhoto
import com.example.foodyapplication.data.modelJson.user.DataUpdate
import com.example.foodyapplication.data.modelJson.user.DataUser
import com.example.foodyapplication.data.modelJson.user.UserJson
import com.example.foodyapplication.data.models.User
import com.example.foodyapplication.data.models.UserPassword
import okhttp3.MultipartBody
import javax.inject.Inject

class NotificationRemoteService @Inject constructor(private val notificationAPI: NotificationAPI) :
    BaseRemoteService() {

    suspend fun getAllNotificationsByUser(userId: String): NetworkResult<NotificationJson<DataNotification>> {
        return callApi { notificationAPI.getAllNotificationsByUser(userId) }
    }
}