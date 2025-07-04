package com.example.foodyapplication.ui.main.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.foodyapplication.base.viewmodel.BaseViewModel
import com.example.foodyapplication.common.Event
import com.example.foodyapplication.data.modelJson.comment.AddCommentRequest
import com.example.foodyapplication.data.modelJson.comment.DataComment
import com.example.foodyapplication.data.modelJson.comment.ReplyCommentRequest
import com.example.foodyapplication.data.repositories.CommentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor(
    private val commentRepository: CommentRepository
) : BaseViewModel() {

    private val _comments = MutableLiveData<List<DataComment>?>()
    val comments: LiveData<List<DataComment>?> = _comments

    private val _commentActionResult = MutableLiveData<Event<Boolean>>()
    val commentActionResult: LiveData<Event<Boolean>> = _commentActionResult

    fun getCommentsByRestaurant(restaurantId: String) {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            try {
                val result = commentRepository.getCommentsByRestaurant(restaurantId)
                _comments.postValue(listOf(result.data))
            } catch (e: Exception) {
                Log.e("CommentVM", "Error getting comments: ${e.message}", e)
            }
        }
        registerJobFinish()
    }

    fun addComment(userId: String, restaurantId: String, request: AddCommentRequest) {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            try {
                commentRepository.addComment(userId, restaurantId, request)
                _commentActionResult.postValue(Event(true))
                getCommentsByRestaurant(restaurantId) // Refresh
            } catch (e: Exception) {
                Log.e("CommentVM", "Error adding comment: ${e.message}", e)
                _commentActionResult.postValue(Event(false))
            }
        }
        registerJobFinish()
    }

    fun replyComment(commentId: String, request: ReplyCommentRequest) {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            try {
                commentRepository.replyComment(commentId, request)
                _commentActionResult.postValue(Event(true))
            } catch (e: Exception) {
                Log.e("CommentVM", "Error replying comment: ${e.message}", e)
                _commentActionResult.postValue(Event(false))
            }
        }
        registerJobFinish()
    }

    fun likeComment(commentId: String, userId: String) {
        parentJob = viewModelScope.launch(handler) {
            try {
                commentRepository.likeComment(commentId, userId)
                _commentActionResult.postValue(Event(true))
            } catch (e: Exception) {
                Log.e("CommentVM", "Error liking comment: ${e.message}", e)
                _commentActionResult.postValue(Event(false))
            }
        }
        registerJobFinish()
    }

    fun deleteComment(commentId: String, restaurantId: String) {
        parentJob = viewModelScope.launch(handler) {
            try {
                commentRepository.deleteCommentById(commentId)
                _commentActionResult.postValue(Event(true))
                getCommentsByRestaurant(restaurantId)
            } catch (e: Exception) {
                Log.e("CommentVM", "Error deleting comment: ${e.message}", e)
                _commentActionResult.postValue(Event(false))
            }
        }
        registerJobFinish()
    }
}
