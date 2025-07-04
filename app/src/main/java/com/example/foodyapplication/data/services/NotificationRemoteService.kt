package com.example.foodyapplication.data.services

import com.example.foodyapplication.base.network.BaseRemoteService
import com.example.foodyapplication.base.network.NetworkResult
import com.example.foodyapplication.data.apis.NotificationAPI
import com.example.foodyapplication.data.modelJson.notification.DataNotification
import com.example.foodyapplication.data.modelJson.notification.NotificationJson
import javax.inject.Inject

class NotificationRemoteService @Inject constructor(
    private val notificationAPI: NotificationAPI
) : BaseRemoteService() {

    suspend fun getAllNotificationsByUser(userId: String): NetworkResult<NotificationJson<DataNotification>> {
        return callApi { notificationAPI.getAllNotificationsByUser(userId) }
    }

    suspend fun addNotification(): NetworkResult<NotificationJson<DataNotification>> {
        return callApi { notificationAPI.addNotification() }
    }

    suspend fun markAllNotifications(userId: String): NetworkResult<NotificationJson<DataNotification>> {
        return callApi { notificationAPI.markAllNotifications(userId) }
    }
}
