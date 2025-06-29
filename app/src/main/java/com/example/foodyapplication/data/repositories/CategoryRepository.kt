package com.example.foodyapplication.data.repositories

import com.example.foodyapplication.base.network.NetworkResult
import com.example.foodyapplication.data.models.User
import com.example.foodyapplication.data.models.UserPassword
import com.example.foodyapplication.data.services.CategoryRemoteService
import com.example.foodyapplication.data.services.CityRemoteService
import com.example.foodyapplication.data.services.UserRemoteService
import com.example.foodyapplication.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val categoryRemoteService: CategoryRemoteService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend fun getAllCategories() = withContext(dispatcher) {
        when (val result = categoryRemoteService.getAllCategories()) {
            is NetworkResult.Success -> {
                result.data
            }

            is NetworkResult.Error -> {
                throw result.exception
            }
        }
    }
}