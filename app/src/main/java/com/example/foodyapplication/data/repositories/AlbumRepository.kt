package com.example.foodyapplication.data.repositories

import com.example.foodyapplication.base.network.NetworkResult
import com.example.foodyapplication.data.modelJson.album.AlbumJson
import com.example.foodyapplication.data.modelJson.album.DataAlbum
import com.example.foodyapplication.data.services.AlbumRemoteService
import com.example.foodyapplication.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class AlbumRepository @Inject constructor(
    private val albumRemoteService: AlbumRemoteService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend fun addAlbum(
        image: MultipartBody.Part,
        restaurantId: RequestBody,
        title: RequestBody,
        description: RequestBody
    ): AlbumJson<DataAlbum> = withContext(dispatcher) {
        when (val result = albumRemoteService.addAlbum(image, restaurantId, title, description)) {
            is NetworkResult.Success -> result.data
            is NetworkResult.Error -> throw result.exception
        }
    }

    suspend fun getAlbumsByRestaurant(restaurantId: String): AlbumJson<DataAlbum> =
        withContext(dispatcher) {
            when (val result = albumRemoteService.getAlbumsByRestaurant(restaurantId)) {
                is NetworkResult.Success -> result.data
                is NetworkResult.Error -> throw result.exception
            }
        }
}
