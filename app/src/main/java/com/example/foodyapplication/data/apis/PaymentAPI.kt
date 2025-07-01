package com.example.foodyapplication.data.apis

import com.example.foodyapplication.common.DataLocal
import com.example.foodyapplication.data.modelJson.payment.DataPayment
import com.example.foodyapplication.data.modelJson.payment.PaymentJson
import retrofit2.Response
import retrofit2.http.*

interface PaymentAPI {

    @POST("${DataLocal.PAYMENT_PREFIX}/payment-url")
    suspend fun
            getPaymentUrl(): Response<PaymentJson<DataPayment>>

    @GET("${DataLocal.PAYMENT_PREFIX}/vnpay-return")
    suspend fun
            handleVnpayReturn(
        @QueryMap query: Map<String, String>
    ): Response<PaymentJson<DataPayment>>

    @GET("${DataLocal.PAYMENT_PREFIX}/vnpay-ipn")
    suspend fun
            handleVnpayIpn(
        @QueryMap query: Map<String, String>
    ): Response<PaymentJson<DataPayment>>

    @GET("${DataLocal.PAYMENT_PREFIX}/history")
    suspend fun
            getOrderHistory(
        @Query("id") userId: String
    ): Response<PaymentJson<DataPayment>>

    @POST("${DataLocal.PAYMENT_PREFIX}/place-order")
    suspend fun
            placeOrder(): Response<PaymentJson<DataPayment>>

    @GET("${DataLocal.PAYMENT_PREFIX}/pending-orders")
    suspend fun
            getPendingOrders(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10
    ): Response<PaymentJson<DataPayment>>

    @GET("${DataLocal.PAYMENT_PREFIX}/search-pending-orders")
    suspend fun
            searchPendingOrders(
        @Query("searchQuery") searchQuery: String,
        @Query("page") page: Int = 1
    ): Response<PaymentJson<DataPayment>>

    @PATCH("${DataLocal.PAYMENT_PREFIX}/update-order-status/{id}")
    suspend fun
            updateOrderStatus(
        @Path("id") orderId: String
    ): Response<PaymentJson<DataPayment>>
}
