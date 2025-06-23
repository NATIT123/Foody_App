package com.example.foodyapplication.data.services

import BaseRemoteService
import com.example.foodyapplication.base.network.NetworkResult
import com.example.foodyapplication.data.apis.UserAPI
import com.example.foodyapplication.data.modelJson.User.UserJson
import javax.inject.Inject

class UserRemoteService @Inject constructor(private val userAPI: UserAPI) : BaseRemoteService() {

    suspend fun login(): NetworkResult<UserJson> {
        return callApi { userAPI.login() }
    }
}