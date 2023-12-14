package com.example.gasapp.model.stations.room

import com.example.gasapp.model.stations.entities.StationWithOils
import kotlinx.coroutines.flow.Flow

interface StationsRepository {

    fun getAllStations(): Flow<List<StationWithOils>>

    fun getOilsById(stationId: Long): Flow<List<StationWithOils>>
}