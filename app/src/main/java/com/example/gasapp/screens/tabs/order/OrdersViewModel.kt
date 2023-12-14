package com.example.gasapp.screens.tabs.order

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gasapp.model.stations.entities.Order
import com.example.gasapp.model.stations.room.OrdersRepository
import com.example.gasapp.utils.share
import kotlinx.coroutines.launch

class OrdersViewModel(
    private val ordersRepository: OrdersRepository
    ): ViewModel() {

    private val _orders = MutableLiveData<List<Order>>()
    val orders = _orders.share()

    init {
        viewModelScope.launch {
            ordersRepository.getOrders(1).collect {
                _orders.value = it
            }
        }
    }

}