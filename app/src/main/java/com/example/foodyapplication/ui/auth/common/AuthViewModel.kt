package com.example.foodyapplication.ui.auth.common

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.foodyapplication.base.viewmodel.BaseViewModel
import com.example.foodyapplication.common.Event
import com.example.foodyapplication.common.TokenManager
import com.example.foodyapplication.data.modelJson.user.User
import com.example.foodyapplication.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val tokenManager: TokenManager,
) :
    BaseViewModel() {
    private val _user = MutableLiveData<User?>()

    val user: LiveData<User?>
        get() = _user

    private var _logoutSuccess = MutableLiveData<Event<Boolean>>()
    val logoutSuccess: LiveData<Event<Boolean>>
        get() = _logoutSuccess

    fun getInfoUser() {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            try {
                val userResponse = userRepository.getMe()
                Log.d("MyApp", "Full response: $userResponse")
                Log.d("MyApp", "Inner user: ${userResponse.data.data}")

                val user = userResponse.data.data
                _user.postValue(user)
            } catch (e: Exception) {
                Log.e("MyApp", "Error getting user: ${e.message}", e)
            }
        }
        registerJobFinish()
    }

    fun logOut() {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            try {
                userRepository.logOut()
                clearUser()
                tokenManager.clearToken()
                _logoutSuccess.postValue(Event(true))
            } catch (e: Exception) {
                Log.e("MyApp", "Error getting user: ${e.message}", e)
            }
        }
        registerJobFinish()
    }

    fun clearUser() {
        _user.postValue(null)
    }

    fun setUser(user: User) {
        _user.postValue(user)
    }


}