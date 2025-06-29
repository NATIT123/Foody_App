package com.example.foodyapplication.data.services

import com.example.foodyapplication.base.network.BaseRemoteService
import com.example.foodyapplication.base.network.NetworkResult
import com.example.foodyapplication.data.apis.CityAPI
import com.example.foodyapplication.data.apis.UserAPI
import com.example.foodyapplication.data.modelJson.city.CityJson
import com.example.foodyapplication.data.modelJson.city.DataCity
import com.example.foodyapplication.data.modelJson.user.DataLogin
import com.example.foodyapplication.data.modelJson.user.DataPhoto
import com.example.foodyapplication.data.modelJson.user.DataUpdate
import com.example.foodyapplication.data.modelJson.user.DataUser
import com.example.foodyapplication.data.modelJson.user.UserJson
import com.example.foodyapplication.data.models.User
import com.example.foodyapplication.data.models.UserPassword
import okhttp3.MultipartBody
import javax.inject.Inject

class CityRemoteService @Inject constructor(private val cityAPI: CityAPI) : BaseRemoteService() {

    suspend fun getAllCities(): NetworkResult<CityJson<DataCity>> {
        return callApi { cityAPI.getAllCities() }
    }
}