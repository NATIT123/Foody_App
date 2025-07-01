package com.example.foodyapplication.data.repositories

import com.example.foodyapplication.base.network.NetworkResult
import com.example.foodyapplication.data.models.User
import com.example.foodyapplication.data.models.UserPassword
import com.example.foodyapplication.data.services.CategoryRemoteService
import com.example.foodyapplication.data.services.CityRemoteService
import com.example.foodyapplication.data.services.NotificationRemoteService
import com.example.foodyapplication.data.services.UserRemoteService
import com.example.foodyapplication.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import javax.inject.Inject

class NotificationRepository @Inject constructor(
    private val notificationRemoteService: NotificationRemoteService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend fun getAllNotificationsByUser(userId: String) = withContext(dispatcher) {
        when (val result = notificationRemoteService.getAllNotificationsByUser(userId)) {
            is NetworkResult.Success -> {
                result.data
            }

            is NetworkResult.Error -> {
                throw result.exception
            }
        }
    }
}