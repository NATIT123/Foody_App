package com.example.foodyapplication.data.repositories

import com.example.foodyapplication.base.network.NetworkResult
import com.example.foodyapplication.data.modelJson.payment.DataPayment
import com.example.foodyapplication.data.modelJson.payment.PaymentJson
import com.example.foodyapplication.data.services.OrderRemoteService
import com.example.foodyapplication.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OrderRepository @Inject constructor(
    private val orderRemoteService: OrderRemoteService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend fun getPaymentUrl(): PaymentJson<DataPayment> = withContext(dispatcher) {
        when (val result = orderRemoteService.getPaymentUrl()) {
            is NetworkResult.Success -> result.data
            is NetworkResult.Error -> throw result.exception
        }
    }

    suspend fun handleVnpayReturn(query: Map<String, String>): PaymentJson<DataPayment> =
        withContext(dispatcher) {
            when (val result = orderRemoteService.handleVnpayReturn(query)) {
                is NetworkResult.Success -> result.data
                is NetworkResult.Error -> throw result.exception
            }
        }

    suspend fun handleVnpayIpn(query: Map<String, String>): PaymentJson<DataPayment> =
        withContext(dispatcher) {
            when (val result = orderRemoteService.handleVnpayIpn(query)) {
                is NetworkResult.Success -> result.data
                is NetworkResult.Error -> throw result.exception
            }
        }

    suspend fun getOrderHistory(userId: String): PaymentJson<DataPayment> =
        withContext(dispatcher) {
            when (val result = orderRemoteService.getOrderHistory(userId)) {
                is NetworkResult.Success -> result.data
                is NetworkResult.Error -> throw result.exception
            }
        }

    suspend fun placeOrder(): PaymentJson<DataPayment> = withContext(dispatcher) {
        when (val result = orderRemoteService.placeOrder()) {
            is NetworkResult.Success -> result.data
            is NetworkResult.Error -> throw result.exception
        }
    }

    suspend fun getPendingOrders(page: Int = 1, limit: Int = 10): PaymentJson<DataPayment> =
        withContext(dispatcher) {
            when (val result = orderRemoteService.getPendingOrders(page, limit)) {
                is NetworkResult.Success -> result.data
                is NetworkResult.Error -> throw result.exception
            }
        }

    suspend fun searchPendingOrders(searchQuery: String, page: Int = 1): PaymentJson<DataPayment> =
        withContext(dispatcher) {
            when (val result = orderRemoteService.searchPendingOrders(searchQuery, page)) {
                is NetworkResult.Success -> result.data
                is NetworkResult.Error -> throw result.exception
            }
        }

    suspend fun updateOrderStatus(orderId: String): PaymentJson<DataPayment> =
        withContext(dispatcher) {
            when (val result = orderRemoteService.updateOrderStatus(orderId)) {
                is NetworkResult.Success -> result.data
                is NetworkResult.Error -> throw result.exception
            }
        }
}
