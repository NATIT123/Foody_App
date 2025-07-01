package com.example.foodyapplication.data.modelJson.album

data class AlbumJson<T>(
    val data: T,
    val message: String,
    val status: String,
    val results:Int,
)