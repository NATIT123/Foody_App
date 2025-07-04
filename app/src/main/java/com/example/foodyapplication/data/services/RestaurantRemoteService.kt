package com.example.foodyapplication.data.services

import com.example.foodyapplication.base.network.BaseRemoteService
import com.example.foodyapplication.base.network.NetworkResult
import com.example.foodyapplication.data.apis.RestaurantAPI
import com.example.foodyapplication.data.modelJson.restaurant.DataRestaurant
import com.example.foodyapplication.data.modelJson.restaurant.RestaurantJson
import javax.inject.Inject

class RestaurantRemoteService @Inject constructor(
    private val restaurantAPI: RestaurantAPI
) : BaseRemoteService() {

    suspend fun getAllRestaurantsByRates(): NetworkResult<RestaurantJson<DataRestaurant>> {
        return callApi { restaurantAPI.getAllRestaurantsByRates() }
    }

    suspend fun getAllRestaurantsByCity(cityId: String): NetworkResult<RestaurantJson<DataRestaurant>> {
        return callApi { restaurantAPI.getAllRestaurantsByCity(cityId) }
    }

    suspend fun getAllRestaurantsTopDeals(): NetworkResult<RestaurantJson<DataRestaurant>> {
        return callApi { restaurantAPI.getAllRestaurantsTopDeals() }
    }

    suspend fun getAllRestaurantsByFields(): NetworkResult<RestaurantJson<DataRestaurant>> {
        return callApi { restaurantAPI.getAllRestaurantsByFields() }
    }

    suspend fun getAllRestaurantsNearest(): NetworkResult<RestaurantJson<DataRestaurant>> {
        return callApi { restaurantAPI.getAllRestaurantsNearest() }
    }

    suspend fun getAllRestaurantsByViews(): NetworkResult<RestaurantJson<DataRestaurant>> {
        return callApi { restaurantAPI.getAllRestaurantsByViews() }
    }

    suspend fun getAllRestaurantsByOptions(
        cityId: String,
        categoryId: String
    ): NetworkResult<RestaurantJson<DataRestaurant>> {
        return callApi { restaurantAPI.getAllRestaurantsByOptions(cityId, categoryId) }
    }

    suspend fun getRestaurantById(): NetworkResult<RestaurantJson<DataRestaurant>> {
        return callApi { restaurantAPI.getRestaurantById() }
    }
}
