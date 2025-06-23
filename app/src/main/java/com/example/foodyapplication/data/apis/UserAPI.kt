package com.example.foodyapplication.data.apis

import com.example.foodyapplication.data.modelJson.User.UserJson
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST


interface UserAPI {

    @POST("/login")
    suspend fun login(): Response<UserJson>

    @POST("/signUp")
    suspend fun register(): Response<UserJson>

    @GET("/me")
    suspend fun getMe(): Response<UserJson>

}