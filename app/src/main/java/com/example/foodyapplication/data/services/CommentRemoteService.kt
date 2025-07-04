package com.example.foodyapplication.data.services

import com.example.foodyapplication.base.network.BaseRemoteService
import com.example.foodyapplication.base.network.NetworkResult
import com.example.foodyapplication.data.apis.CommentAPI
import com.example.foodyapplication.data.modelJson.comment.AddCommentRequest
import com.example.foodyapplication.data.modelJson.comment.CommentJson
import com.example.foodyapplication.data.modelJson.comment.DataComment
import com.example.foodyapplication.data.modelJson.comment.ReplyCommentRequest
import javax.inject.Inject

class CommentRemoteService @Inject constructor(
    private val commentAPI: CommentAPI
) : BaseRemoteService() {

    suspend fun addComment(
        userId: String,
        restaurantId: String,
        body: AddCommentRequest
    ): NetworkResult<CommentJson<DataComment>> {
        return callApi { commentAPI.addComment(userId, restaurantId, body) }
    }

    suspend fun getCommentsByRestaurant(restaurantId: String): NetworkResult<CommentJson<DataComment>> {
        return callApi { commentAPI.getCommentsByRestaurant(restaurantId) }
    }

    suspend fun likeComment(
        commentId: String,
        userId: String
    ): NetworkResult<CommentJson<DataComment>> {
        return callApi { commentAPI.likeComment(commentId, userId) }
    }

    suspend fun replyComment(
        commentId: String,
        body: ReplyCommentRequest
    ): NetworkResult<CommentJson<DataComment>> {
        return callApi { commentAPI.replyComment(commentId, body) }
    }

    suspend fun deleteCommentById(commentId: String): NetworkResult<CommentJson<DataComment>> {
        return callApi { commentAPI.deleteCommentById(commentId) }
    }
}
