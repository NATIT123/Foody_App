package com.example.foodyapplication.ui.main.changepassword

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.foodyapplication.base.viewmodel.BaseViewModel
import com.example.foodyapplication.common.Event
import com.example.foodyapplication.common.TokenManager
import com.example.foodyapplication.data.models.User
import com.example.foodyapplication.data.models.UserPassword
import com.example.foodyapplication.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val tokenManager: TokenManager
) : BaseViewModel() {

    private var _updatePasswordSuccess = MutableLiveData<Event<Boolean>>()
    val updatePasswordSuccess: LiveData<Event<Boolean>>
        get() = _updatePasswordSuccess

    private var _currentUser = MutableLiveData<UserPassword?>()
    val currentUser: LiveData<UserPassword?>
        get() = _currentUser

    fun initUser() {
        _currentUser.postValue(UserPassword())
    }

    fun updatePassword(user: UserPassword) {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            try {
                userRepository.updatePassword(user)
                tokenManager.clearToken()
                _updatePasswordSuccess.postValue(Event(true))
            } catch (e: Exception) {
                Log.e("MyApp", "Error getting user: ${e.message}", e)
            }
        }
        registerJobFinish()
    }


    fun updatePasswordAction() {
        currentUser.value?.let { user ->
            updatePassword(user)
        }
    }

    fun onPasswordChanged(
        text: CharSequence?,
        start: Int,
        before: Int,
        count: Int
    ) {
        val password = text?.toString()
        val user = currentUser.value
        if (user?.password != password) {
            user?.password = password
            _currentUser.postValue(user)
        }
    }

    fun onNewPasswordChanged(
        text: CharSequence?,
        start: Int,
        before: Int,
        count: Int
    ) {
        val newPassword = text?.toString()
        val user = currentUser.value
        if (user?.newPassword != newPassword) {
            user?.newPassword = newPassword
            _currentUser.postValue(user)
        }
    }

    fun onConfirmPasswordChanged(
        text: CharSequence?,
        start: Int,
        before: Int,
        count: Int
    ) {
        val confirmPassword = text?.toString()
        val user = currentUser.value
        if (user?.confirmPassword != confirmPassword) {
            user?.confirmPassword = confirmPassword
            _currentUser.postValue(user)
        }
    }
}