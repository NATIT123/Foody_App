package com.example.foodyapplication.base.network

public open class NetworkErrorException (val responseMessage: String? = null): Exception() {
}