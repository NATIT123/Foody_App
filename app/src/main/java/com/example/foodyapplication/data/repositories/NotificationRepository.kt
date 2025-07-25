package com.example.foodyapplication.data.repositories

import com.example.foodyapplication.base.network.NetworkResult
import com.example.foodyapplication.data.services.NotificationRemoteService
import com.example.foodyapplication.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NotificationRepository @Inject constructor(
    private val notificationRemoteService: NotificationRemoteService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend fun getAllNotificationsByUser(userId: String) = withContext(dispatcher) {
        when (val result = notificationRemoteService.getAllNotificationsByUser(userId)) {
            is NetworkResult.Success -> result.data
            is NetworkResult.Error -> throw result.exception
        }
    }

    suspend fun addNotification() = withContext(dispatcher) {
        when (val result = notificationRemoteService.addNotification()) {
            is NetworkResult.Success -> result.data
            is NetworkResult.Error -> throw result.exception
        }
    }

    suspend fun markAllNotifications(userId: String) = withContext(dispatcher) {
        when (val result = notificationRemoteService.markAllNotifications(userId)) {
            is NetworkResult.Success -> result.data
            is NetworkResult.Error -> throw result.exception
        }
    }
}
