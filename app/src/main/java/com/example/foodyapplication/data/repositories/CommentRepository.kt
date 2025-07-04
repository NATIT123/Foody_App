package com.example.foodyapplication.data.repositories

import com.example.foodyapplication.base.network.NetworkResult
import com.example.foodyapplication.data.modelJson.comment.AddCommentRequest
import com.example.foodyapplication.data.modelJson.comment.ReplyCommentRequest
import com.example.foodyapplication.data.services.CommentRemoteService
import com.example.foodyapplication.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CommentRepository @Inject constructor(
    private val commentRemoteService: CommentRemoteService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend fun addComment(
        userId: String,
        restaurantId: String,
        request: AddCommentRequest
    ) = withContext(dispatcher) {
        when (val result = commentRemoteService.addComment(userId, restaurantId, request)) {
            is NetworkResult.Success -> result.data
            is NetworkResult.Error -> throw result.exception
        }
    }

    suspend fun getCommentsByRestaurant(restaurantId: String) = withContext(dispatcher) {
        when (val result = commentRemoteService.getCommentsByRestaurant(restaurantId)) {
            is NetworkResult.Success -> result.data
            is NetworkResult.Error -> throw result.exception
        }
    }

    suspend fun likeComment(commentId: String, userId: String) = withContext(dispatcher) {
        when (val result = commentRemoteService.likeComment(commentId, userId)) {
            is NetworkResult.Success -> result.data
            is NetworkResult.Error -> throw result.exception
        }
    }

    suspend fun replyComment(
        commentId: String,
        request: ReplyCommentRequest
    ) = withContext(dispatcher) {
        when (val result = commentRemoteService.replyComment(commentId, request)) {
            is NetworkResult.Success -> result.data
            is NetworkResult.Error -> throw result.exception
        }
    }

    suspend fun deleteCommentById(commentId: String) = withContext(dispatcher) {
        when (val result = commentRemoteService.deleteCommentById(commentId)) {
            is NetworkResult.Success -> result.data
            is NetworkResult.Error -> throw result.exception
        }
    }
}
