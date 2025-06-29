package com.example.foodyapplication.data.apis

import com.example.foodyapplication.common.DataLocal
import com.example.foodyapplication.data.modelJson.city.CityJson
import com.example.foodyapplication.data.modelJson.city.DataCity
import com.example.foodyapplication.data.modelJson.restaurant.DataRestaurant
import com.example.foodyapplication.data.modelJson.restaurant.RestaurantJson
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface RestaurantAPI {
    @GET("${DataLocal.RESTAURANT_PREFIX}/fetchRestaurantsByRate")
    suspend fun getAllRestaurantsByRates(): Response<RestaurantJson<DataRestaurant>>

    @GET("${DataLocal.RESTAURANT_PREFIX}/getRestaurantByCity/city/:cityId")
    suspend fun getAllRestaurantsByCity(): Response<RestaurantJson<DataRestaurant>>

    @POST("${DataLocal.RESTAURANT_PREFIX}/getRestaurantTopDeals")
    suspend fun getAllRestaurantsTopDeals(): Response<RestaurantJson<DataRestaurant>>

    @POST("${DataLocal.RESTAURANT_PREFIX}/getRestaurantByFields")
    suspend fun getAllRestaurantsByFields(): Response<RestaurantJson<DataRestaurant>>

    @POST("${DataLocal.RESTAURANT_PREFIX}/getNearestRestaurants")
    suspend fun getAllRestaurantsNearest(): Response<RestaurantJson<DataRestaurant>>

    @POST("${DataLocal.RESTAURANT_PREFIX}/getRestaurantByViews")
    suspend fun getAllRestaurantsByViews(): Response<RestaurantJson<DataRestaurant>>

    @POST("${DataLocal.RESTAURANT_PREFIX}/getRestaurantByOptions/city/:cityId/category/:categoryId")
    suspend fun getAllRestaurantsByOptions(): Response<RestaurantJson<DataRestaurant>>

    @POST("${DataLocal.RESTAURANT_PREFIX}/getRestaurant")
    suspend fun getRestaurantById(): Response<RestaurantJson<DataRestaurant>>
}