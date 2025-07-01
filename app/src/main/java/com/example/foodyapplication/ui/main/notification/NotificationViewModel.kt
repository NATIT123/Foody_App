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
class NotificationViewModel @Inject constructor(private val notificationRepository: NotificationRepository) :
    BaseViewModel() {

    private var _currentNotification = MutableLiveData<List<Notification?>>()
    val currentNotification: LiveData<List<Notification?>>
        get() = _currentNotification

    fun getNotificationsByUser(userId: String) {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            try {
                val response = notificationRepository.getAllNotificationsByUser(userId)
                _currentNotification.postValue(response.data.data)
            } catch (e: Exception) {
                Log.e("MyApp", "Error getting notification: ${e.message}", e)
            }
        }
        registerJobFinish()
    }


}