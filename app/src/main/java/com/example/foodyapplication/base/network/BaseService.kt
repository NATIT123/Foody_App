package com.example.foodyapplication.base.network

import android.util.Log


abstract class BaseService {

    protected fun parseError(
        responseMessage: String?,
        responseCode: Int,
        errorBody: String?
    ): BaseNetworkException {

        val baseNetworkException =  BaseNetworkException(responseMessage,responseCode)
        errorBody?.let{
            baseNetworkException.parseFromString(it)
        }

        return baseNetworkException
    }

    protected fun parseNetworkErrorException(throwable: Throwable): NetworkErrorException {
        Log.d("MyApp",throwable.message.toString())
        return NetworkErrorException(throwable.message)
    }




}