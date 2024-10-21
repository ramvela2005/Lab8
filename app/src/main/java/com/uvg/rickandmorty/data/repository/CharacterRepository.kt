package com.uvg.rickandmorty.data.repository

import com.uvg.rickandmorty.data.source.CharacterDb // Asegúrate de importar correctamente
import kotlinx.coroutines.delay

class CharacterRepository(private val characterDao: CharacterDao) {
    private val characterDb = CharacterDb() // Instancia de la base de datos simulada

    suspend fun syncCharacters() {
        delay(2000) // Simulación de sincronización
        val charactersFromDb = characterDb.getAllCharacters() // Obtener personajes desde la base de datos simulada
        characterDao.insertAll(charactersFromDb) // Insertar personajes en Room
    }

    // Obtener todos los personajes desde Room
    fun getAllCharacters() = characterDao.getAllCharacters()

    // Obtener un personaje específico por ID desde Room
    fun getCharacterById(id: Int) = characterDao.getCharacterById(id)
}
