package com.example.foodyapplication.data.apis

import com.example.foodyapplication.data.modelJson.User.UserJson
import retrofit2.Response
import retrofit2.http.POST

interface UserAPI {

    @POST("/user/login")
    suspend fun login(): Response<UserJson>

    @POST("/user/signUp")
    suspend fun register(): Response<UserJson>
}