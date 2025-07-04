package com.example.foodyapplication.ui.main.notification

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.foodyapplication.base.viewmodel.BaseViewModel
import com.example.foodyapplication.common.Event
import com.example.foodyapplication.data.modelJson.notification.Notification
import com.example.foodyapplication.data.repositories.NotificationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val notificationRepository: NotificationRepository
) : BaseViewModel() {

    private val _currentNotification = MutableLiveData<List<Notification?>>()
    val currentNotification: LiveData<List<Notification?>>
        get() = _currentNotification

    private val _notificationActionSuccess = MutableLiveData<Event<Boolean>>()
    val notificationActionSuccess: LiveData<Event<Boolean>>
        get() = _notificationActionSuccess

    fun getNotificationsByUser(userId: String) {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            try {
                val response = notificationRepository.getAllNotificationsByUser(userId)
                _currentNotification.postValue(response.data.data)
            } catch (e: Exception) {
                Log.e("MyApp", "Error getting notifications: ${e.message}", e)
            }
        }
        registerJobFinish()
    }

    fun addNotification() {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            try {
                notificationRepository.addNotification()
                _notificationActionSuccess.postValue(Event(true))
            } catch (e: Exception) {
                Log.e("MyApp", "Error adding notification: ${e.message}", e)
                _notificationActionSuccess.postValue(Event(false))
            }
        }
        registerJobFinish()
    }

    fun markAllNotifications(userId: String) {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            try {
                notificationRepository.markAllNotifications(userId)
                _notificationActionSuccess.postValue(Event(true))
            } catch (e: Exception) {
                Log.e("MyApp", "Error marking all notifications: ${e.message}", e)
                _notificationActionSuccess.postValue(Event(false))
            }
        }
        registerJobFinish()
    }
}
