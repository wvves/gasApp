package com.example.gasapp.model.stations.room

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.gasapp.model.stations.entities.StationWithOils
import kotlinx.coroutines.flow.Flow

@Dao
interface StationsDao {
    @Transaction
    @Query("SELECT * FROM stations")
    fun getStationsWithOils(): Flow<List<StationWithOils>>

    @Transaction
    @Query("SELECT * FROM stations WHERE stations.stationId = :stationId")
    fun getOilsByStationId(stationId: Long): Flow<List<StationWithOils>>
}