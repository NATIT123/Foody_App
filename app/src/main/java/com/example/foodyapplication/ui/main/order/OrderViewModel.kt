package com.example.foodyapplication.ui.main.order

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.foodyapplication.base.viewmodel.BaseViewModel
import com.example.foodyapplication.data.modelJson.payment.DataPayment
import com.example.foodyapplication.data.repositories.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val orderRepository: OrderRepository
) : BaseViewModel() {

    private val _paymentUrl = MutableLiveData<DataPayment?>()
    val paymentUrl: LiveData<DataPayment?> = _paymentUrl

    private val _orderHistory = MutableLiveData<List<DataPayment>?>()
    val orderHistory: LiveData<List<DataPayment>?> = _orderHistory

    private val _pendingOrders = MutableLiveData<List<DataPayment>?>()
    val pendingOrders: LiveData<List<DataPayment>?> = _pendingOrders

    private val _orderResult = MutableLiveData<DataPayment?>()
    val orderResult: LiveData<DataPayment?> = _orderResult

    fun getPaymentUrl() {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            try {
                val result = orderRepository.getPaymentUrl()
                _paymentUrl.postValue(result.data)
            } catch (e: Exception) {
                Log.e("OrderViewModel", "getPaymentUrl error: ${e.message}", e)
            }
        }
        registerJobFinish()
    }

    fun placeOrder() {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            try {
                val result = orderRepository.placeOrder()
                _orderResult.postValue(result.data)
            } catch (e: Exception) {
                Log.e("OrderViewModel", "placeOrder error: ${e.message}", e)
            }
        }
        registerJobFinish()
    }

    fun getOrderHistory(userId: String) {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            try {
                val result = orderRepository.getOrderHistory(userId)
                _orderHistory.postValue(listOf(result.data))
            } catch (e: Exception) {
                Log.e("OrderViewModel", "getOrderHistory error: ${e.message}", e)
            }
        }
        registerJobFinish()
    }

    fun getPendingOrders(page: Int = 1, limit: Int = 10) {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            try {
                val result = orderRepository.getPendingOrders(page, limit)
                _pendingOrders.postValue(listOf(result.data))
            } catch (e: Exception) {
                Log.e("OrderViewModel", "getPendingOrders error: ${e.message}", e)
            }
        }
        registerJobFinish()
    }

    fun searchPendingOrders(searchQuery: String, page: Int = 1) {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            try {
                val result = orderRepository.searchPendingOrders(searchQuery, page)
                _pendingOrders.postValue(listOf(result.data))
            } catch (e: Exception) {
                Log.e("OrderViewModel", "searchPendingOrders error: ${e.message}", e)
            }
        }
        registerJobFinish()
    }

    fun updateOrderStatus(orderId: String) {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            try {
                orderRepository.updateOrderStatus(orderId)
            } catch (e: Exception) {
                Log.e("OrderViewModel", "updateOrderStatus error: ${e.message}", e)
            }
        }
        registerJobFinish()
    }
}
