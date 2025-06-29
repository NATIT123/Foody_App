package com.example.foodyapplication.data.apis

import com.example.foodyapplication.common.DataLocal
import com.example.foodyapplication.data.modelJson.category.CategoryJson
import com.example.foodyapplication.data.modelJson.category.DataCategory
import com.example.foodyapplication.data.modelJson.notification.DataNotification
import com.example.foodyapplication.data.modelJson.notification.NotificationJson
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface NotificationAPI {
    @GET("${DataLocal.CATEGORY_PREFIX}/getNotificationsByUserId")
    suspend fun getAllNotificationsByUser(): Response<NotificationJson<DataNotification>>

    @POST("${DataLocal.CATEGORY_PREFIX}/addNotification")
    suspend fun addNotification(): Response<NotificationJson<DataNotification>>

    @POST("${DataLocal.CATEGORY_PREFIX}/makeAll/:userId")
    suspend fun markAllNotifications(): Response<NotificationJson<DataNotification>>
}