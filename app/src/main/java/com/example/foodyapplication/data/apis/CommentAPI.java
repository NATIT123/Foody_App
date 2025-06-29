package com.example.foodyapplication.data.apis;

import retrofit2.http.GET;

public interface CommentAPI {
    @GET("${DataLocal.COMMENT_PREFIX}/getAllComment")
    suspend fun

    getAllComments():Response<CommentJson<DataComment>>

    @POST("${DataLocal.COMMENT_PREFIX}/addComment/user/{userId}/restaurant/{restaurantId}")
    suspend fun

    addComment(
            @Path("userId")userId:String,
            @Path("restaurantId")restaurantId:String,
            @Body body:AddCommentRequest
    ):Response<CommentJson<DataComment>>

    @GET("${DataLocal.COMMENT_PREFIX}/getCommentsByRestaurant/{restaurantId}")
    suspend fun

    getCommentsByRestaurant(
            @Path("restaurantId")restaurantId:String
    ):Response<CommentJson<DataComment>>

    @GET("${DataLocal.COMMENT_PREFIX}/like/{commentId}/{userId}")
    suspend fun

    likeComment(
            @Header("Authorization")token:String,
            @Path("commentId")commentId:String,
            @Path("userId")userId:String
    ):Response<CommentJson<DataComment>>

    @POST("${DataLocal.COMMENT_PREFIX}/reply/{commentId}")
    suspend fun

    replyComment(
            @Header("Authorization")token:String,
            @Path("commentId")commentId:String,
            @Body body:ReplyCommentRequest
    ):Response<CommentJson<DataComment>>

    @GET("${DataLocal.COMMENT_PREFIX}/count")
    suspend fun

    countComments(
            @Header("Authorization")token:String
    ):Response<CountResponse>

    @DELETE("${DataLocal.COMMENT_PREFIX}/deleteComment/{id}")
    suspend fun

    deleteCommentById(
            @Header("Authorization")token:String,
            @Path("id")id:String
    ):Response<CommentJson<DataComment>>

    @PATCH("${DataLocal.COMMENT_PREFIX}/updateComment/{id}")
    suspend fun

    updateCommentById(
            @Header("Authorization")token:String,
            @Path("id")id:String,
            @Body body:UpdateCommentRequest
    ):Response<CommentJson<DataComment>>

    @GET("${DataLocal.COMMENT_PREFIX}/getComment/{id}")
    suspend fun

    getCommentById(
            @Header("Authorization")token:String,
            @Path("id")id:String
    ):Response<CommentJson<DataComment>>
}
