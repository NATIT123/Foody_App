package com.example.foodyapplication.data.apis;

interface AlbumAPI {

    @GET("${DataLocal.ALBUM_PREFIX}/getAllAlbums")
    suspend fun

    getAllAlbums():Response<AlbumJson<DataAlbum>>

    @Multipart
    @POST("${DataLocal.ALBUM_PREFIX}/addAlbum")
    suspend fun

    addAlbum(
            @Part image:MultipartBody.Part,
            @Part("restaurantId")restaurantId:RequestBody,
            @Part("title")title:RequestBody,
            @Part("description")description:RequestBody
    ):Response<AlbumJson<DataAlbum>>

    @GET("${DataLocal.ALBUM_PREFIX}/getAlbum/{id}")
    suspend fun

    getAlbumById(
            @Path("id")id:String
    ):Response<AlbumJson<DataAlbum>>

    @GET("${DataLocal.ALBUM_PREFIX}/getAlbumsByRestaurant/{restaurantId}")
    suspend fun

    getAlbumsByRestaurant(
            @Path("restaurantId")restaurantId:String
    ):Response<AlbumJson<DataAlbum>>

    @GET("${DataLocal.ALBUM_PREFIX}/count")
    suspend fun

    countAlbums(
            @Header("Authorization")token:String
    ):Response<CountResponse>

    @DELETE("${DataLocal.ALBUM_PREFIX}/deleteAlbum/{id}")
    suspend fun

    deleteAlbumById(
            @Header("Authorization")token:String,
            @Path("id")id:String
    ):Response<AlbumJson<DataAlbum>>

    @PATCH("${DataLocal.ALBUM_PREFIX}/updateAlbum/{id}")
    suspend fun

    updateAlbumById(
            @Header("Authorization")token:String,
            @Path("id")id:String,
            @Body body:UpdateAlbumRequest
    ):Response<AlbumJson<DataAlbum>>
}

