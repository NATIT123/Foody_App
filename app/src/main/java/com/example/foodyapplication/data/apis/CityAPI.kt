package com.example.foodyapplication.data.apis

import com.example.foodyapplication.common.DataLocal
import com.example.foodyapplication.data.modelJson.city.CityJson
import com.example.foodyapplication.data.modelJson.city.DataCity
import retrofit2.Response
import retrofit2.http.GET

interface CityAPI {
    @GET("${DataLocal.CITY_PREFIX}/getAllCity")
    suspend fun getAllCities(): Response<CityJson<DataCity>>
}