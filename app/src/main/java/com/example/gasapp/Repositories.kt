package com.example.gasapp

import android.content.Context
import androidx.room.Room
import com.example.gasapp.model.AppDatabase
import com.example.gasapp.model.accounts.room.AccountsRepository
import com.example.gasapp.model.accounts.room.RoomAccountsRepository
import com.example.gasapp.model.settings.AppSettings
import com.example.gasapp.model.settings.SharedPreferencesAppSettings
import com.example.gasapp.model.stations.room.OrdersRepository
import com.example.gasapp.model.stations.room.RoomOrdersRepository
import com.example.gasapp.model.stations.room.RoomStationsRepository
import com.example.gasapp.model.stations.room.StationsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

object Repositories {
    private lateinit var applicationContext: Context

    private val database: AppDatabase by lazy<AppDatabase> {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database.db")
            .createFromAsset("initial_database.db")
            .build()
    }

    private val appSettings: AppSettings by lazy {
        SharedPreferencesAppSettings(applicationContext)
    }

    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    val accountsRepository: AccountsRepository by lazy {
        RoomAccountsRepository(database.getAccountsDao(), appSettings, ioDispatcher)
    }

    val stationsRepository: StationsRepository by lazy {
        RoomStationsRepository(database.getStationsDao())
    }

    val ordersRepository: OrdersRepository by lazy {
        RoomOrdersRepository(database.getOrdersDao())
    }

    fun init(context: Context) {
        applicationContext = context
    }
}