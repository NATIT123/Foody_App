package com.example.foodyapplication.data.modelJson.comment

data class CommentJson<T>(
    val data: T,
    val message: String,
    val status: String,
    val results:Int,
)