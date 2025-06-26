package com.example.foodyapplication.ui.main.settings

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.foodyapplication.base.viewmodel.BaseViewModel
import com.example.foodyapplication.common.Event
import com.example.foodyapplication.data.models.UserPassword
import com.example.foodyapplication.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val userRepository: UserRepository) :
    BaseViewModel() {
    private var _updateAvatarSuccess = MutableLiveData<Event<Boolean>>()
    val updateAvatarSuccess: LiveData<Event<Boolean>>
        get() = _updateAvatarSuccess


    var latestAvatarUrl: String? = null

    fun updatePhoto(photo: MultipartBody.Part) {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            try {
                val response = userRepository.uploadPhoto(photo)
                latestAvatarUrl = response.data.photo
                _updateAvatarSuccess.postValue(Event(true))
            } catch (e: Exception) {
                Log.e("MyApp", "Error getting user: ${e.message}", e)
            }
        }
        registerJobFinish()
    }
}