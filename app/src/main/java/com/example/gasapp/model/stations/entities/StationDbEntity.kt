package com.example.gasapp.model.stations.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation



@Entity(
    tableName = "stations"
)
data class StationDbEntity(
    @PrimaryKey val stationId: Long,
    val stationName: String,
    val address: String
) {
    fun toStation(): Station = Station(
        stationId = stationId,
        stationName = stationName,
        address = address
    )
}

@Entity(
    tableName = "oils"
)
data class Oil(
    @PrimaryKey val oilId: Long,
    val type: String,
    val price: Long
)

@Entity(
    tableName = "station_oil_cross_ref",
    primaryKeys = ["stationId", "oilId"]
)
data class StationOilCrossRef(
    val stationId: Long,
    val oilId: Long
)

data class StationWithOils(
    @Embedded val station: StationDbEntity,
    @Relation(
        parentColumn = "stationId",
        entityColumn = "oilId",
        associateBy = Junction(
            StationOilCrossRef::class
        )
    )
    val oils: List<Oil>
) {
    fun toStationInfo(): StationWithOils = StationWithOils(
        station = station,
        oils = oils
    )
}
