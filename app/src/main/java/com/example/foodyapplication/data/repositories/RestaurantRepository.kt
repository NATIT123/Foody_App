package com.example.foodyapplication.data.repositories

import com.example.foodyapplication.base.network.NetworkResult
import com.example.foodyapplication.data.services.RestaurantRemoteService
import com.example.foodyapplication.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RestaurantRepository @Inject constructor(
    private val restaurantRemoteService: RestaurantRemoteService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend fun getAllRestaurantsByRates() = withContext(dispatcher) {
        when (val result = restaurantRemoteService.getAllRestaurantsByRates()) {
            is NetworkResult.Success -> result.data.data
            is NetworkResult.Error -> throw result.exception
        }
    }

    suspend fun getAllRestaurantsByCity(cityId: String) = withContext(dispatcher) {
        when (val result = restaurantRemoteService.getAllRestaurantsByCity(cityId)) {
            is NetworkResult.Success -> result.data.data
            is NetworkResult.Error -> throw result.exception
        }
    }

    suspend fun getAllRestaurantsTopDeals() = withContext(dispatcher) {
        when (val result = restaurantRemoteService.getAllRestaurantsTopDeals()) {
            is NetworkResult.Success -> result.data.data
            is NetworkResult.Error -> throw result.exception
        }
    }

    suspend fun getAllRestaurantsByFields() = withContext(dispatcher) {
        when (val result = restaurantRemoteService.getAllRestaurantsByFields()) {
            is NetworkResult.Success -> result.data.data
            is NetworkResult.Error -> throw result.exception
        }
    }

    suspend fun getAllRestaurantsNearest() = withContext(dispatcher) {
        when (val result = restaurantRemoteService.getAllRestaurantsNearest()) {
            is NetworkResult.Success -> result.data.data
            is NetworkResult.Error -> throw result.exception
        }
    }

    suspend fun getAllRestaurantsByViews() = withContext(dispatcher) {
        when (val result = restaurantRemoteService.getAllRestaurantsByViews()) {
            is NetworkResult.Success -> result.data.data
            is NetworkResult.Error -> throw result.exception
        }
    }

    suspend fun getAllRestaurantsByOptions(cityId: String, categoryId: String) =
        withContext(dispatcher) {
            when (val result =
                restaurantRemoteService.getAllRestaurantsByOptions(cityId, categoryId)) {
                is NetworkResult.Success -> result.data.data
                is NetworkResult.Error -> throw result.exception
            }
        }

    suspend fun getRestaurantById() = withContext(dispatcher) {
        when (val result = restaurantRemoteService.getRestaurantById()) {
            is NetworkResult.Success -> result.data.data
            is NetworkResult.Error -> throw result.exception
        }
    }
}
