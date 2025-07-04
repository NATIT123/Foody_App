package com.example.foodyapplication.data.services

import com.example.foodyapplication.base.network.BaseRemoteService
import com.example.foodyapplication.base.network.NetworkResult
import com.example.foodyapplication.data.apis.FoodAPI
import com.example.foodyapplication.data.modelJson.food.DataFood
import com.example.foodyapplication.data.modelJson.food.FoodJson
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class FoodRemoteService @Inject constructor(
    private val foodAPI: FoodAPI
) : BaseRemoteService() {

    suspend fun getFoodsByRestaurant(restaurantId: String): NetworkResult<FoodJson<DataFood>> {
        return callApi { foodAPI.getFoodsByRestaurant(restaurantId) }
    }

    suspend fun addFood(
        image: MultipartBody.Part,
        restaurantId: RequestBody,
        name: RequestBody,
        price: RequestBody,
        description: RequestBody
    ): NetworkResult<FoodJson<DataFood>> {
        return callApi {
            foodAPI.addFood(image, restaurantId, name, price, description)
        }
    }
}
