package com.uvg.rickandmorty.data.repository

import com.uvg.rickandmorty.data.source.LocationDb // Asegúrate de importar correctamente
import kotlinx.coroutines.delay

class LocationRepository(private val locationDao: LocationDao) {
    private val locationDb = LocationDb() // Instancia de la base de datos simulada

    suspend fun syncLocations() {
        delay(2000) // Simulación de sincronización
        val locationsFromDb = locationDb.getAllLocations() // Obtener ubicaciones desde la base de datos simulada
        locationDao.insertAll(locationsFromDb) // Insertar ubicaciones en Room
    }

    // Obtener todas las ubicaciones desde Room
    fun getAllLocations() = locationDao.getAllLocations()

    // Obtener una ubicación específica por ID desde Room
    fun getLocationById(id: Int) = locationDao.getLocationById(id)
}
