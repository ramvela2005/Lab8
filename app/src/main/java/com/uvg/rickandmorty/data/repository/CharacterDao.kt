package com.uvg.rickandmorty.data.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.uvg.rickandmorty.data.model.Character

@Dao
interface CharacterDao {

    // Insertar múltiples personajes con reemplazo en caso de conflicto
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<Character>)

    // Obtener todos los personajes
    @Query("SELECT * FROM character")
    fun getAllCharacters(): List<Character>

    // Obtener un personaje por su ID
    @Query("SELECT * FROM character WHERE id = :id")
    fun getCharacterById(id: Int): Character
}
