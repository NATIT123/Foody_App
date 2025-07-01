package com.example.foodyapplication.data.apis;

import com.example.foodyapplication.common.DataLocal
import com.example.foodyapplication.data.modelJson.album.AlbumJson
import com.example.foodyapplication.data.modelJson.album.DataAlbum

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path

interface AlbumAPI {


    @Multipart
    @POST("${DataLocal.ALBUM_PREFIX}/addAlbum")
    suspend fun

    addAlbum(
            @Part image:MultipartBody.Part,
            @Part("restaurantId")restaurantId:RequestBody,
            @Part("title")title:RequestBody,
            @Part("description")description:RequestBody
    ):Response<AlbumJson<DataAlbum>>


    @GET("${DataLocal.ALBUM_PREFIX}/getAlbumsByRestaurant/{restaurantId}")
    suspend fun

    getAlbumsByRestaurant(
            @Path("restaurantId")restaurantId:String
    ):Response<AlbumJson<DataAlbum>>


}

