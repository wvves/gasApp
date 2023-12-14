package com.example.gasapp.model.stations.room

import com.example.gasapp.model.stations.entities.StationWithOils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomStationsRepository(
    private val stationsDao: StationsDao
): StationsRepository {
    override fun getAllStations(): Flow<List<StationWithOils>> {
        return stationsDao.getStationsWithOils().map { entities ->
            entities.map {
                it
            }
        }
    }

    override fun getOilsById(stationId: Long): Flow<List<StationWithOils>> {
        return stationsDao.getOilsByStationId(stationId).map { entities ->
            entities.map {
                it
            }
        }
    }
}