package com.example.foodyapplication.data.services

import com.example.foodyapplication.base.network.BaseRemoteService
import com.example.foodyapplication.base.network.NetworkResult
import com.example.foodyapplication.data.apis.DistrictAPI
import com.example.foodyapplication.data.modelJson.district.DataDistrict
import com.example.foodyapplication.data.modelJson.district.DistrictJson
import javax.inject.Inject

class DistrictRemoteService @Inject constructor(
    private val districtAPI: DistrictAPI
) : BaseRemoteService() {

    suspend fun getAllDistricts(): NetworkResult<DistrictJson<DataDistrict>> {
        return callApi { districtAPI.getAllDistricts() }
    }

    suspend fun getDistrictsByCity(cityId: String): NetworkResult<DistrictJson<DataDistrict>> {
        return callApi { districtAPI.getDistrictsByCity(cityId) }
    }
}
