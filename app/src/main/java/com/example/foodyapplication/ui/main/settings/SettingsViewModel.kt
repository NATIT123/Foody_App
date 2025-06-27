package com.example.foodyapplication.ui.main.settings

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.foodyapplication.base.viewmodel.BaseViewModel
import com.example.foodyapplication.common.Event
import com.example.foodyapplication.data.models.User
import com.example.foodyapplication.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val userRepository: UserRepository) :
    BaseViewModel() {
    private var _updateAvatarSuccess = MutableLiveData<Event<Boolean>>()
    val updateAvatarSuccess: LiveData<Event<Boolean>>
        get() = _updateAvatarSuccess

    private var _currentUser = MutableLiveData<User?>()
    val currentUser: LiveData<User?>
        get() = _currentUser

    private var _updateNameSuccess = MutableLiveData<Event<Boolean>>()
    val updateNameSuccess: LiveData<Event<Boolean>>
        get() = _updateNameSuccess

    private var _updatePhoneSuccess = MutableLiveData<Event<Boolean>>()
    val updatePhoneSuccess: LiveData<Event<Boolean>>
        get() = _updatePhoneSuccess


    private var _updateAddressSuccess = MutableLiveData<Event<Boolean>>()
    val updateAddressSuccess: LiveData<Event<Boolean>>
        get() = _updateAddressSuccess


    private val _latestAvatarUrl = MutableLiveData<String>()
    val latestAvatarUrl: LiveData<String>
        get() = _latestAvatarUrl

    fun initUserFullName(fullName: String) {
        _currentUser.postValue(User(fullname = fullName))
    }

    fun initUserPhone(phone: String) {
        _currentUser.postValue(User(phone = phone))
    }

    fun initUserAddress(address: String) {
        _currentUser.postValue(User(address = address))
    }

    fun updatePhoto(photo: MultipartBody.Part) {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            try {
                val response = userRepository.uploadPhoto(photo)
                _latestAvatarUrl?.postValue(response.data.photo)
                Log.d("SettingsViewModel", "Avatar URL received: ${response.data.photo}")
                _updateAvatarSuccess.postValue(Event(true))
            } catch (e: Exception) {
                Log.e("MyApp", "Error getting user: ${e.message}", e)
            }
        }
        registerJobFinish()
    }

    private fun updateUser(user: User) {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            try {
                userRepository.updateMe(user)
                _updateNameSuccess.postValue(Event(true))
                _updatePhoneSuccess.postValue(Event(true))
                _updateAddressSuccess.postValue(Event(true))
            } catch (e: Exception) {
                Log.e("MyApp", "Error getting user: ${e.message}", e)
            }
        }
        registerJobFinish()
    }

    fun actionUpdateMe() {
        currentUser.value?.let { user ->
            updateUser(user)
        }
    }

    fun onFullNameChanged(
        text: CharSequence?,
        start: Int,
        before: Int,
        count: Int
    ) {
        val fullName = text?.toString()
        val user = currentUser.value
        if (user?.fullname != fullName) {
            user?.fullname = fullName
            _currentUser.postValue(user)
        }
    }

    fun onPhoneChanged(
        text: CharSequence?,
        start: Int,
        before: Int,
        count: Int
    ) {
        val phone = text?.toString()
        val user = currentUser.value
        if (user?.phone != phone) {
            user?.phone = phone
            _currentUser.postValue(user)
        }
    }

    fun onAddressChanged(
        text: CharSequence?,
        start: Int,
        before: Int,
        count: Int
    ) {
        val address = text?.toString()
        val user = currentUser.value
        if (user?.address != address) {
            user?.address = address
            _currentUser.postValue(user)
        }
    }
}