package com.uvg.rickandmorty.data.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.uvg.rickandmorty.data.model.Location

@Dao
interface LocationDao {

    // Insertar múltiples ubicaciones con reemplazo en caso de conflicto
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(locations: List<Location>)

    // Obtener todas las ubicaciones
    @Query("SELECT * FROM location")
    fun getAllLocations(): List<Location>

    // Obtener una ubicación por su ID
    @Query("SELECT * FROM location WHERE id = :id")
    fun getLocationById(id: Int): Location
}
