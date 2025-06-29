package com.example.foodyapplication.data.apis

import com.example.foodyapplication.common.DataLocal
import com.example.foodyapplication.data.modelJson.category.CategoryJson
import com.example.foodyapplication.data.modelJson.category.DataCategory
import retrofit2.Response
import retrofit2.http.GET

interface SubCategoryAPI {
    @GET("${DataLocal.SUBCATEGORY_PREFIX}/getAllCategory")
    suspend fun getAllCategories(): Response<CategoryJson<DataCategory>>
}