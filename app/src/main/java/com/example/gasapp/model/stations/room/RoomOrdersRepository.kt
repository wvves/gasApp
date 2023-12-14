package com.example.gasapp.model.stations.room

import com.example.gasapp.model.accounts.room.AccountsRepository
import com.example.gasapp.model.stations.entities.Order
import com.example.gasapp.model.stations.entities.OrderDbEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest

class RoomOrdersRepository(
    private val ordersDao: OrdersDao
): OrdersRepository {
    override fun getOrders(accountId: Long): Flow<List<Order>> {
        return ordersDao.getOrdersByAccountId(accountId).mapLatest { entities ->
            entities.map {
                it
            }
        }
    }

    override suspend fun addOrder(orderDbEntity: OrderDbEntity) {
//        ordersDao.addOrderToAccount(OrderDbEntity(
//            orderId = 0,
//            id = 1,
//            stationId = 2,
//            oilId = 3,
//            createdAt = System.currentTimeMillis())
//        )
//        ordersDao.addOrderToAccount(orderDbEntity)
    }

}