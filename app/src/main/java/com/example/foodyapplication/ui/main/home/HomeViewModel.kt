package com.example.foodyapplication.ui.main.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.foodyapplication.base.viewmodel.BaseViewModel
import com.example.foodyapplication.data.modelJson.category.Category
import com.example.foodyapplication.data.modelJson.city.City
import com.example.foodyapplication.data.repositories.CategoryRepository
import com.example.foodyapplication.data.repositories.CityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val cityRepository: CityRepository,
    private val categoryRepository: CategoryRepository
) :
    BaseViewModel() {

    private var _currentCities = MutableLiveData<List<City>?>()
    val currentCities: LiveData<List<City>?>
        get() = _currentCities

    private var _currentCategories = MutableLiveData<List<Category>?>()
    val currentCategories: LiveData<List<Category>?>
        get() = _currentCategories

    fun getAllCities() {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            try {
                val response = cityRepository.getAllCities()
                _currentCities.postValue(response.data.data)
            } catch (e: Exception) {
                Log.e("MyApp", "Error getting user: ${e.message}", e)
            }
        }
        registerJobFinish()
    }

    fun getAllCategories() {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            try {
                val response = categoryRepository.getAllCategories()
                _currentCategories.postValue(response.data.data)
            } catch (e: Exception) {
                Log.e("MyApp", "Error getting category: ${e.message}", e)
            }
        }
        registerJobFinish()
    }
}