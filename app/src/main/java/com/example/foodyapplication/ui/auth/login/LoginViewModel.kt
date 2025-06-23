package com.example.foodyapplication.ui.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.foodyapplication.base.viewmodel.BaseViewModel
import com.example.foodyapplication.common.Event
import com.example.foodyapplication.data.modelJson.User.User
import com.example.foodyapplication.data.repositories.UserRepository
import com.example.foodyapplication.data.models.User as UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(private val userRepository: UserRepository) :
    BaseViewModel() {


    private var _loginUserSuccess = MutableLiveData<Event<Boolean>>()
    val loginUserSuccess: LiveData<Event<Boolean>>
        get() = _loginUserSuccess

    private var _currentUser = MutableLiveData<UserModel?>()
    val currentUser: LiveData<UserModel?>
        get() = _currentUser


    private val _user = MutableLiveData<User>()

    val user: LiveData<User>
        get() = _user


    private fun loginUser(user: UserModel) {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            _user.postValue(userRepository.login())
            _loginUserSuccess.postValue(Event(true))
        }
        registerJobFinish()
    }

    fun loginAction() {
        currentUser.value?.let { user ->
            loginUser(user)
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




}