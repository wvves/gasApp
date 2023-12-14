package com.example.gasapp.model.stations.entities

import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.gasapp.model.accounts.entites.Account
import com.example.gasapp.model.accounts.room.AccountDbEntity


@Entity(
    tableName = "orders",
)
data class OrderDbEntity(
    @PrimaryKey(autoGenerate = true) val orderId: Long,
    val id: Long,
    val stationId: Long,
    val oilId: Long,
    val createdAt: Long
)