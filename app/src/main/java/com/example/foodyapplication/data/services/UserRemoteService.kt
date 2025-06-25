package com.example.foodyapplication.data.services

import com.example.foodyapplication.base.network.BaseRemoteService
import com.example.foodyapplication.base.network.NetworkResult
import com.example.foodyapplication.data.apis.UserAPI
import com.example.foodyapplication.data.modelJson.user.DataLogin
import com.example.foodyapplication.data.modelJson.user.DataUser
import com.example.foodyapplication.data.modelJson.user.UserJson
import com.example.foodyapplication.data.models.User
import javax.inject.Inject

class UserRemoteService @Inject constructor(private val userAPI: UserAPI) : BaseRemoteService() {

    suspend fun login(user: User): NetworkResult<UserJson<DataLogin>> {
        return callApi { userAPI.login(user) }
    }

    suspend fun register(): NetworkResult<UserJson<DataLogin>> {
        return callApi { userAPI.register() }
    }

    suspend fun getMe(): NetworkResult<UserJson<DataUser>> {
        return callApi { userAPI.getMe() }
    }

    suspend fun logOut(): NetworkResult<UserJson<Unit>> {
        return callApi { userAPI.logOut() }
    }
}