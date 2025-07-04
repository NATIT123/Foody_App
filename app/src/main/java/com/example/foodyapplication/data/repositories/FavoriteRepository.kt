package com.example.foodyapplication.data.repositories

import com.example.foodyapplication.base.network.NetworkResult
import com.example.foodyapplication.data.services.FavoriteRemoteService
import com.example.foodyapplication.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavoriteRepository @Inject constructor(
    private val favoriteRemoteService: FavoriteRemoteService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend fun getAllFavoriteRestaurants() = withContext(dispatcher) {
        when (val result = favoriteRemoteService.getAllFavoriteRestaurants()) {
            is NetworkResult.Success -> result.data
            is NetworkResult.Error -> throw result.exception
        }
    }

    suspend fun getFavoriteRestaurantByUserId(userId: String) = withContext(dispatcher) {
        when (val result = favoriteRemoteService.getFavoriteRestaurantByUserId(userId)) {
            is NetworkResult.Success -> result.data
            is NetworkResult.Error -> throw result.exception
        }
    }

    suspend fun deleteFavoriteRestaurantById(id: String) = withContext(dispatcher) {
        when (val result = favoriteRemoteService.deleteFavoriteRestaurantById(id)) {
            is NetworkResult.Success -> result.data
            is NetworkResult.Error -> throw result.exception
        }
    }
}
