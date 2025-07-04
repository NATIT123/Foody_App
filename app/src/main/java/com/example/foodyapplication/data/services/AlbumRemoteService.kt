package com.example.foodyapplication.data.services

import com.example.foodyapplication.base.network.BaseRemoteService
import com.example.foodyapplication.base.network.NetworkResult
import com.example.foodyapplication.data.apis.AlbumAPI
import com.example.foodyapplication.data.modelJson.album.AlbumJson
import com.example.foodyapplication.data.modelJson.album.DataAlbum
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class AlbumRemoteService @Inject constructor(
    private val albumAPI: AlbumAPI
) : BaseRemoteService() {

    suspend fun addAlbum(
        image: MultipartBody.Part,
        restaurantId: RequestBody,
        title: RequestBody,
        description: RequestBody
    ): NetworkResult<AlbumJson<DataAlbum>> {
        return callApi {
            albumAPI.addAlbum(image, restaurantId, title, description)
        }
    }

    suspend fun getAlbumsByRestaurant(restaurantId: String): NetworkResult<AlbumJson<DataAlbum>> {
        return callApi {
            albumAPI.getAlbumsByRestaurant(restaurantId)
        }
    }
}
