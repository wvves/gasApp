package com.example.gasapp.model.stations.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.gasapp.model.stations.entities.Order
import com.example.gasapp.model.stations.entities.OrderDbEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OrdersDao {

    @Transaction
    @Query("SELECT * from orders WHERE orders.id = :accountId")
    fun getOrdersByAccountId(accountId: Long): Flow<List<Order>>

    @Insert
    suspend fun addOrderToAccount(orderDbEntity: OrderDbEntity)
}