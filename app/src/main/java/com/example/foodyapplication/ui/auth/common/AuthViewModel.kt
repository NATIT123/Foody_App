package com.example.foodyapplication.ui.auth.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.foodyapplication.base.viewmodel.BaseViewModel
import com.example.foodyapplication.common.Event
import com.example.foodyapplication.data.modelJson.User.User
import com.example.foodyapplication.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.foodyapplication.data.models.User as UserModel

@HiltViewModel
class AuthViewModel @Inject constructor(private val userRepository: UserRepository) :
    BaseViewModel() {
    private val _user = MutableLiveData<User>()

    val user: LiveData<User>
        get() = _user

    fun getUser() {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            val userResponse = userRepository.getMe()
            _user.postValue(userResponse.data.user)
        }
        registerJobFinish()
    }
}