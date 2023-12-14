package com.example.gasapp.model.stations.room


import com.example.gasapp.model.stations.entities.Order
import com.example.gasapp.model.stations.entities.OrderDbEntity
import kotlinx.coroutines.flow.Flow


interface OrdersRepository {

    fun getOrders(accountId: Long): Flow<List<Order>>

    suspend fun addOrder(orderDbEntity: OrderDbEntity)
}