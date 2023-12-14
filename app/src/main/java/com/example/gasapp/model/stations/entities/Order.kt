package com.example.gasapp.model.stations.entities

import androidx.room.Junction
import androidx.room.Relation
import com.example.gasapp.model.accounts.room.AccountDbEntity

data class Order(
    val orderId: Long,
    @Relation(
        parentColumn = "orderId",
        entityColumn = "id",
        associateBy = Junction(OrderDbEntity::class)
    )
    val account: AccountDbEntity,
    @Relation(
        parentColumn = "orderId",
        entityColumn = "stationId",
        associateBy = Junction(OrderDbEntity::class)
    )
    val station: StationDbEntity,
    @Relation(
        parentColumn = "orderId",
        entityColumn = "oilId",
        associateBy = Junction(OrderDbEntity::class)
    )
    val oil: Oil,
    val createdAt: Long
)