package com.example.foodyapplication.data.apis;

import com.example.foodyapplication.common.DataLocal
import com.example.foodyapplication.data.modelJson.comment.AddCommentRequest
import com.example.foodyapplication.data.modelJson.comment.CommentJson
import com.example.foodyapplication.data.modelJson.comment.DataComment
import com.example.foodyapplication.data.modelJson.comment.ReplyCommentRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET;
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface CommentAPI {

    @POST("${DataLocal.COMMENT_PREFIX}/addComment/user/{userId}/restaurant/{restaurantId}")
    suspend fun

            addComment(
        @Path("userId") userId: String,
        @Path("restaurantId") restaurantId: String,
        @Body body: AddCommentRequest
    ): Response<CommentJson<DataComment>>

    @GET("${DataLocal.COMMENT_PREFIX}/getCommentsByRestaurant/{restaurantId}")
    suspend fun

            getCommentsByRestaurant(
        @Path("restaurantId") restaurantId: String
    ): Response<CommentJson<DataComment>>

    @GET("${DataLocal.COMMENT_PREFIX}/like/{commentId}/{userId}")
    suspend fun

            likeComment(
        @Path("commentId") commentId: String,
        @Path("userId") userId: String
    ): Response<CommentJson<DataComment>>

    @POST("${DataLocal.COMMENT_PREFIX}/reply/{commentId}")
    suspend fun

            replyComment(
        @Path("commentId") commentId: String,
        @Body body: ReplyCommentRequest
    ): Response<CommentJson<DataComment>>

    @DELETE("${DataLocal.COMMENT_PREFIX}/deleteComment/{id}")
    suspend fun

            deleteCommentById(
        @Path("id") id: String
    ): Response<CommentJson<DataComment>>


}
