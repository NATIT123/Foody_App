package com.example.foodyapplication.data.apis

import com.example.foodyapplication.common.DataLocal
import com.example.foodyapplication.data.modelJson.food.DataFood
import com.example.foodyapplication.data.modelJson.food.FoodJson
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface FoodAPI {

    @GET("${DataLocal.FOOD_PREFIX}/getFoodsByRestaurant/{restaurantId}")
    suspend fun getFoodsByRestaurant(
        @Path("restaurantId") restaurantId: String
    ): Response<FoodJson<DataFood>>

    @Multipart
    @POST("${DataLocal.FOOD_PREFIX}/addFood")
    suspend fun addFood(
        @Part image: MultipartBody.Part,
        @Part("restaurantId") restaurantId: RequestBody,
        @Part("name") name: RequestBody,
        @Part("price") price: RequestBody,
        @Part("description") description: RequestBody
    ): Response<FoodJson<DataFood>>
}
