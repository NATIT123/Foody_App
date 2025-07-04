package com.example.foodyapplication.data.services

import com.example.foodyapplication.base.network.BaseRemoteService
import com.example.foodyapplication.base.network.NetworkResult
import com.example.foodyapplication.data.apis.PaymentAPI
import com.example.foodyapplication.data.modelJson.payment.DataPayment
import com.example.foodyapplication.data.modelJson.payment.PaymentJson
import javax.inject.Inject

class OrderRemoteService @Inject constructor(
    private val paymentAPI: PaymentAPI
) : BaseRemoteService() {

    suspend fun getPaymentUrl(): NetworkResult<PaymentJson<DataPayment>> {
        return callApi { paymentAPI.getPaymentUrl() }
    }

    suspend fun handleVnpayReturn(query: Map<String, String>): NetworkResult<PaymentJson<DataPayment>> {
        return callApi { paymentAPI.handleVnpayReturn(query) }
    }

    suspend fun handleVnpayIpn(query: Map<String, String>): NetworkResult<PaymentJson<DataPayment>> {
        return callApi { paymentAPI.handleVnpayIpn(query) }
    }

    suspend fun getOrderHistory(userId: String): NetworkResult<PaymentJson<DataPayment>> {
        return callApi { paymentAPI.getOrderHistory(userId) }
    }

    suspend fun placeOrder(): NetworkResult<PaymentJson<DataPayment>> {
        return callApi { paymentAPI.placeOrder() }
    }

    suspend fun getPendingOrders(
        page: Int = 1,
        limit: Int = 10
    ): NetworkResult<PaymentJson<DataPayment>> {
        return callApi { paymentAPI.getPendingOrders(page, limit) }
    }

    suspend fun searchPendingOrders(
        searchQuery: String,
        page: Int = 1
    ): NetworkResult<PaymentJson<DataPayment>> {
        return callApi { paymentAPI.searchPendingOrders(searchQuery, page) }
    }

    suspend fun updateOrderStatus(orderId: String): NetworkResult<PaymentJson<DataPayment>> {
        return callApi { paymentAPI.updateOrderStatus(orderId) }
    }
}
