package com.example.foodyapplication.data.services

import com.example.foodyapplication.base.network.BaseRemoteService
import com.example.foodyapplication.base.network.NetworkResult
import com.example.foodyapplication.data.apis.FavoriteRestaurantAPI
import com.example.foodyapplication.data.modelJson.favorite.DataFavorite
import com.example.foodyapplication.data.modelJson.favorite.FavoriteJson
import javax.inject.Inject

class FavoriteRemoteService @Inject constructor(
    private val favoriteRestaurantAPI: FavoriteRestaurantAPI
) : BaseRemoteService() {

    suspend fun getAllFavoriteRestaurants(): NetworkResult<FavoriteJson<DataFavorite>> {
        return callApi { favoriteRestaurantAPI.getAllFavoriteRestaurants() }
    }

    suspend fun getFavoriteRestaurantByUserId(userId: String): NetworkResult<FavoriteJson<DataFavorite>> {
        return callApi { favoriteRestaurantAPI.getFavoriteRestaurantByUserId(userId) }
    }

    suspend fun deleteFavoriteRestaurantById(id: String): NetworkResult<FavoriteJson<DataFavorite>> {
        return callApi { favoriteRestaurantAPI.deleteFavoriteRestaurantById(id) }
    }
}
