package com.example.foodyapplication.data.repositories

import com.example.foodyapplication.base.network.NetworkResult
import com.example.foodyapplication.data.services.UserRemoteService
import com.example.foodyapplication.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userRemoteService: UserRemoteService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend fun login() = withContext(dispatcher) {
        when (val result = userRemoteService.login()) {
            is NetworkResult.Success -> {
                result.data.data.user
            }

            is NetworkResult.Error -> {
                throw result.exception
            }
        }
    }

    suspend fun register() = withContext(dispatcher) {
        when (val result = userRemoteService.login()) {
            is NetworkResult.Success -> {
                result.data.data.user
            }

            is NetworkResult.Error -> {
                throw result.exception
            }
        }
    }
}