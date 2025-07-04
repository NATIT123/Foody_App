package com.example.foodyapplication.data.repositories

import com.example.foodyapplication.base.network.NetworkResult
import com.example.foodyapplication.data.modelJson.food.DataFood
import com.example.foodyapplication.data.modelJson.food.FoodJson
import com.example.foodyapplication.data.services.FoodRemoteService
import com.example.foodyapplication.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class FoodRepository @Inject constructor(
    private val foodRemoteService: FoodRemoteService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend fun getFoodsByRestaurant(restaurantId: String): FoodJson<DataFood> =
        withContext(dispatcher) {
            when (val result = foodRemoteService.getFoodsByRestaurant(restaurantId)) {
                is NetworkResult.Success -> result.data
                is NetworkResult.Error -> throw result.exception
            }
        }

    suspend fun addFood(
        image: MultipartBody.Part,
        restaurantId: RequestBody,
        name: RequestBody,
        price: RequestBody,
        description: RequestBody
    ): FoodJson<DataFood> = withContext(dispatcher) {
        when (val result =
            foodRemoteService.addFood(image, restaurantId, name, price, description)) {
            is NetworkResult.Success -> result.data
            is NetworkResult.Error -> throw result.exception
        }
    }
}
