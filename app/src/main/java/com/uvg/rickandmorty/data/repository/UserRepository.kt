package com.uvg.rickandmorty.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first

class UserRepository(private val dataStore: DataStore<Preferences>) {

    companion object {
        val USER_NAME_KEY = stringPreferencesKey("user_name")
    }

    // Función para guardar el nombre del usuario en DataStore
    suspend fun saveUserName(name: String) {
        dataStore.edit { preferences ->
            preferences[USER_NAME_KEY] = name
        }
    }

    // Función para obtener el nombre del usuario desde DataStore
    suspend fun getUserName(): String? {
        val preferences = dataStore.data.first()
        return preferences[USER_NAME_KEY]
    }

    // Función para eliminar el nombre del usuario (logout)
    suspend fun clearUserName() {
        dataStore.edit { preferences ->
            preferences.remove(USER_NAME_KEY)
        }
    }
}
