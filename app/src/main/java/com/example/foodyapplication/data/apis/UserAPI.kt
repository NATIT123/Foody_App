package com.example.foodyapplication.data.apis

import com.example.foodyapplication.data.modelJson.user.DataLogin
import com.example.foodyapplication.data.modelJson.user.DataPhoto
import com.example.foodyapplication.data.modelJson.user.DataUser
import com.example.foodyapplication.data.modelJson.user.UserJson
import com.example.foodyapplication.data.models.User
import com.example.foodyapplication.data.models.UserPassword
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part


interface UserAPI {

    @POST("login")
    suspend fun login(@Body user: User): Response<UserJson<DataLogin>>

    @POST("signUp")
    suspend fun register(): Response<UserJson<DataLogin>>

    @PATCH("updatePassword")
    suspend fun updatePassword(@Body user: UserPassword): Response<UserJson<DataLogin>>

    @GET("me")
    suspend fun getMe(): Response<UserJson<DataUser>>

    @POST("logout")
    suspend fun logOut(): Response<UserJson<Unit>>

    @POST("uploadPhoto")
    @Multipart
    suspend fun uploadPhoto(@Part image: MultipartBody.Part): Response<UserJson<DataPhoto>>

}