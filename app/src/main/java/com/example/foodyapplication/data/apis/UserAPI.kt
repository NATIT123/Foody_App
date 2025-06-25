package com.example.foodyapplication.data.apis

import com.example.foodyapplication.data.modelJson.user.DataLogin
import com.example.foodyapplication.data.modelJson.user.DataUser
import com.example.foodyapplication.data.modelJson.user.UserJson
import com.example.foodyapplication.data.models.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface UserAPI {

    @POST("login")
    suspend fun login(@Body user: User): Response<UserJson<DataLogin>>

    @POST("signUp")
    suspend fun register(): Response<UserJson<DataLogin>>

    @GET("me")
    suspend fun getMe(): Response<UserJson<DataUser>>

    @POST("logout")
    suspend fun logOut(): Response<UserJson<Unit>>

}