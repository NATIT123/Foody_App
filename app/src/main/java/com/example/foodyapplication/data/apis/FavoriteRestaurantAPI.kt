package com.example.foodyapplication.data.apis

import com.example.foodyapplication.common.DataLocal
import com.example.foodyapplication.data.modelJson.favorite.DataFavorite
import com.example.foodyapplication.data.modelJson.favorite.FavoriteJson
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface FavoriteRestaurantAPI {

        @GET("${DataLocal.FAVORITE_RESTAURANT_PREFIX}/getAllFavoriteRestaurant")
        suspend fun getAllFavoriteRestaurants(): Response<FavoriteJson<DataFavorite>>

//        @POST("${DataLocal.FAVORITE_RESTAURANT_PREFIX}/addFavoriteRestaurant")
//        suspend fun addFavoriteRestaurant(
//            @Body body: AddFavoriteRequest
//        ): Response<FavoriteJson<DataFavorite>>

        @DELETE("${DataLocal.FAVORITE_RESTAURANT_PREFIX}/deleteFavoriteRestaurant/{id}")
        suspend fun deleteFavoriteRestaurantById(
            @Path("id") id: String
        ): Response<FavoriteJson<DataFavorite>>

//        @PATCH("${DataLocal.FAVORITE_RESTAURANT_PREFIX}/updateFavoriteRestaurant/{id}")
//        suspend fun updateFavoriteRestaurantById(
//            @Path("id") id: String,
//            @Body body: UpdateFavoriteRequest
//        ): Response<FavoriteJson<DataFavorite>>


        @POST("${DataLocal.FAVORITE_RESTAURANT_PREFIX}/getFavoriteRestaurantByUserId/{userId}")
        suspend fun getFavoriteRestaurantByUserId(
            @Path("userId") userId: String
        ): Response<FavoriteJson<DataFavorite>>


    }

