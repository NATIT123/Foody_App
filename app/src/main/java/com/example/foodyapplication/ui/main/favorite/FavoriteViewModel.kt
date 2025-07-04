package com.example.foodyapplication.ui.main.favorite

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.foodyapplication.base.viewmodel.BaseViewModel
import com.example.foodyapplication.common.Event
import com.example.foodyapplication.data.modelJson.favorite.DataFavorite
import com.example.foodyapplication.data.repositories.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : BaseViewModel() {

    private val _favoriteRestaurants = MutableLiveData<List<DataFavorite>?>()
    val favoriteRestaurants: LiveData<List<DataFavorite>?> = _favoriteRestaurants

    private val _actionSuccess = MutableLiveData<Event<Boolean>>()
    val actionSuccess: LiveData<Event<Boolean>> = _actionSuccess

    fun getFavoriteRestaurantsByUser(userId: String) {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            try {
                val response = favoriteRepository.getFavoriteRestaurantByUserId(userId)
                _favoriteRestaurants.postValue(listOf(response.data))
            } catch (e: Exception) {
                Log.e("FavoriteVM", "Error getting favorite restaurants: ${e.message}", e)
            }
        }
        registerJobFinish()
    }

    fun deleteFavoriteRestaurant(id: String, userId: String) {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            try {
                favoriteRepository.deleteFavoriteRestaurantById(id)
                _actionSuccess.postValue(Event(true))
                getFavoriteRestaurantsByUser(userId)
            } catch (e: Exception) {
                Log.e("FavoriteVM", "Error deleting favorite restaurant: ${e.message}", e)
                _actionSuccess.postValue(Event(false))
            }
        }
        registerJobFinish()
    }
}
