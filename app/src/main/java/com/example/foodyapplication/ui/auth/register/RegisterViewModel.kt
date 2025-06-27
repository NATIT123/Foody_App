package com.example.foodyapplication.ui.auth.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.foodyapplication.base.viewmodel.BaseViewModel
import com.example.foodyapplication.common.Event
import com.example.foodyapplication.data.modelJson.user.User
import com.example.foodyapplication.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.foodyapplication.data.models.User as UserModel


@HiltViewModel
class RegisterViewModel @Inject constructor(private val userRepository: UserRepository) :
    BaseViewModel() {

    private var _registerUserSuccess = MutableLiveData<Event<Boolean>>()
    val registerUserSuccess: LiveData<Event<Boolean>>
        get() = _registerUserSuccess

    private var _currentUser = MutableLiveData<UserModel?>()
    val currentUser: LiveData<UserModel?>
        get() = _currentUser


    private fun registerUser(user: UserModel) {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            _registerUserSuccess.postValue(Event(true))
        }
        registerJobFinish()
    }

    fun registerAction() {
        currentUser.value?.let { user ->
            registerUser(user)
        }
    }

    fun onEmailChanged(
        text: CharSequence?,
        start: Int,
        before: Int,
        count: Int
    ) {
        val email = text?.toString()
        val user = currentUser.value
        if (user?.email != email) {
            user?.email = email
            _currentUser.postValue(user)
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