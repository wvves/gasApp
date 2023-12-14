package com.example.gasapp.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gasapp.model.accounts.room.AccountDbEntity
import com.example.gasapp.model.accounts.room.AccountsDao
import com.example.gasapp.model.stations.entities.Oil
import com.example.gasapp.model.stations.entities.OrderDbEntity
import com.example.gasapp.model.stations.entities.StationDbEntity
import com.example.gasapp.model.stations.entities.StationOilCrossRef
import com.example.gasapp.model.stations.room.OrdersDao
import com.example.gasapp.model.stations.room.StationsDao

@Database(
    version = 1,
    entities = [
        AccountDbEntity::class,
        Oil::class,
        StationDbEntity::class,
        StationOilCrossRef::class,
        OrderDbEntity::class
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getAccountsDao(): AccountsDao

    abstract fun getStationsDao(): StationsDao

    abstract fun getOrdersDao(): OrdersDao
}