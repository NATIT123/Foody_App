package com.example.foodyapplication.data.repositories

import com.example.foodyapplication.base.network.NetworkResult
import com.example.foodyapplication.data.services.DistrictRemoteService
import com.example.foodyapplication.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DistrictRepository @Inject constructor(
    private val districtRemoteService: DistrictRemoteService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend fun getAllDistricts() = withContext(dispatcher) {
        when (val result = districtRemoteService.getAllDistricts()) {
            is NetworkResult.Success -> result.data
            is NetworkResult.Error -> throw result.exception
        }
    }

    suspend fun getDistrictsByCity(cityId: String) = withContext(dispatcher) {
        when (val result = districtRemoteService.getDistrictsByCity(cityId)) {
            is NetworkResult.Success -> result.data
            is NetworkResult.Error -> throw result.exception
        }
    }
}
