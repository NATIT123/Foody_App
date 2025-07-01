package com.example.foodyapplication.data.apis;

import com.example.foodyapplication.common.DataLocal
import com.example.foodyapplication.data.modelJson.district.DataDistrict;
import com.example.foodyapplication.data.modelJson.district.DistrictJson;

import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

interface DistrictAPI {

    @GET("${DataLocal.DISTRICT_PREFIX}/getAllDistrict")
    suspend fun getAllDistricts(): Response<DistrictJson<DataDistrict>>

    @GET("${DataLocal.DISTRICT_PREFIX}/getDistrictsByCity/{cityId}")
    suspend fun getDistrictsByCity(
        @Path("cityId") cityId: String
    ): Response<DistrictJson<DataDistrict>>


}

