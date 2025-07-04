package com.example.foodyapplication.ui.main.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.foodyapplication.base.viewmodel.BaseViewModel
import com.example.foodyapplication.data.modelJson.category.Category
import com.example.foodyapplication.data.modelJson.city.City
import com.example.foodyapplication.data.modelJson.district.DataDistrict
import com.example.foodyapplication.data.modelJson.district.District
import com.example.foodyapplication.data.modelJson.restaurant.DataRestaurant
import com.example.foodyapplication.data.repositories.CategoryRepository
import com.example.foodyapplication.data.repositories.CityRepository
import com.example.foodyapplication.data.repositories.DistrictRepository
import com.example.foodyapplication.data.repositories.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val cityRepository: CityRepository,
    private val categoryRepository: CategoryRepository,
    private val restaurantRepository: RestaurantRepository,
    private val districtRepository: DistrictRepository
) : BaseViewModel() {

    private val _currentCities = MutableLiveData<List<City>?>()
    val currentCities: LiveData<List<City>?> = _currentCities

    private val _currentCategories = MutableLiveData<List<Category>?>()
    val currentCategories: LiveData<List<Category>?> = _currentCategories

    private val _districtsByCity = MutableLiveData<List<District>?>()
    val districtsByCity: LiveData<List<District>?> = _districtsByCity

    private val _allDistricts = MutableLiveData<List<District>?>()
    val allDistricts: LiveData<List<District>?> = _allDistricts

    private val _restaurantsByRate = MutableLiveData<List<DataRestaurant>?>()
    val restaurantsByRate: LiveData<List<DataRestaurant>?> = _restaurantsByRate

    private val _restaurantsByCity = MutableLiveData<List<DataRestaurant>?>()
    val restaurantsByCity: LiveData<List<DataRestaurant>?> = _restaurantsByCity

    private val _restaurantsTopDeals = MutableLiveData<List<DataRestaurant>?>()
    val restaurantsTopDeals: LiveData<List<DataRestaurant>?> = _restaurantsTopDeals

    private val _restaurantsByFields = MutableLiveData<List<DataRestaurant>?>()
    val restaurantsByFields: LiveData<List<DataRestaurant>?> = _restaurantsByFields

    private val _restaurantsNearest = MutableLiveData<List<DataRestaurant>?>()
    val restaurantsNearest: LiveData<List<DataRestaurant>?> = _restaurantsNearest

    private val _restaurantsByViews = MutableLiveData<List<DataRestaurant>?>()
    val restaurantsByViews: LiveData<List<DataRestaurant>?> = _restaurantsByViews

    private val _restaurantsByOptions = MutableLiveData<List<DataRestaurant>?>()
    val restaurantsByOptions: LiveData<List<DataRestaurant>?> = _restaurantsByOptions

    private val _restaurantDetail = MutableLiveData<DataRestaurant?>()
    val restaurantDetail: LiveData<DataRestaurant?> = _restaurantDetail

    fun getAllCities() {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            try {
                val response = cityRepository.getAllCities()
                _currentCities.postValue(response.data.data)
            } catch (e: Exception) {
                Log.e("MyApp", "Error getting cities: ${e.message}", e)
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
                Log.e("MyApp", "Error getting categories: ${e.message}", e)
            }
        }
        registerJobFinish()
    }

    fun getAllRestaurantsByRates() {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            try {
                val result = restaurantRepository.getAllRestaurantsByRates()
                _restaurantsByRate.postValue(listOf(result))
            } catch (e: Exception) {
                Log.e("MyApp", "Error getting restaurants by rate: ${e.message}", e)
            }
        }
        registerJobFinish()
    }

    fun getAllRestaurantsByCity(cityId: String) {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            try {
                val result = restaurantRepository.getAllRestaurantsByCity(cityId)
                _restaurantsByCity.postValue(listOf(result))
            } catch (e: Exception) {
                Log.e("MyApp", "Error getting restaurants by city: ${e.message}", e)
            }
        }
        registerJobFinish()
    }

    fun getAllRestaurantsTopDeals() {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            try {
                val result = restaurantRepository.getAllRestaurantsTopDeals()
                _restaurantsTopDeals.postValue(listOf(result))
            } catch (e: Exception) {
                Log.e("MyApp", "Error getting top deals restaurants: ${e.message}", e)
            }
        }
        registerJobFinish()
    }

    fun getAllRestaurantsByFields() {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            try {
                val result = restaurantRepository.getAllRestaurantsByFields()
                _restaurantsByFields.postValue(listOf(result))
            } catch (e: Exception) {
                Log.e("MyApp", "Error getting restaurants by fields: ${e.message}", e)
            }
        }
        registerJobFinish()
    }

    fun getAllRestaurantsNearest() {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            try {
                val result = restaurantRepository.getAllRestaurantsNearest()
                _restaurantsNearest.postValue(listOf(result))
            } catch (e: Exception) {
                Log.e("MyApp", "Error getting nearest restaurants: ${e.message}", e)
            }
        }
        registerJobFinish()
    }

    fun getAllRestaurantsByViews() {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            try {
                val result = restaurantRepository.getAllRestaurantsByViews()
                _restaurantsByViews.postValue(listOf(result))
            } catch (e: Exception) {
                Log.e("MyApp", "Error getting restaurants by views: ${e.message}", e)
            }
        }
        registerJobFinish()
    }

    fun getAllRestaurantsByOptions(cityId: String, categoryId: String) {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            try {
                val result = restaurantRepository.getAllRestaurantsByOptions(cityId, categoryId)
                _restaurantsByOptions.postValue(listOf(result))
            } catch (e: Exception) {
                Log.e("MyApp", "Error getting restaurants by options: ${e.message}", e)
            }
        }
        registerJobFinish()
    }

    fun getRestaurantById() {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            try {
                val result = restaurantRepository.getRestaurantById()
                _restaurantDetail.postValue(result)
            } catch (e: Exception) {
                Log.e("MyApp", "Error getting restaurant detail: ${e.message}", e)
            }
        }
        registerJobFinish()
    }

    fun getAllDistricts() {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            try {
                val response = districtRepository.getAllDistricts()
                _allDistricts.postValue(response.data.data)
            } catch (e: Exception) {
                Log.e("MyApp", "Error getting all districts: ${e.message}", e)
            }
        }
        registerJobFinish()
    }

    fun getDistrictsByCity(cityId: String) {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            try {
                val response = districtRepository.getDistrictsByCity(cityId)
                _districtsByCity.postValue(response.data.data)
            } catch (e: Exception) {
                Log.e("MyApp", "Error getting districts by city: ${e.message}", e)
            }
        }
        registerJobFinish()
    }
}
