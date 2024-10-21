package com.uvg.rickandmorty.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uvg.rickandmorty.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {

    val userName = MutableStateFlow("")

    // Función para actualizar el nombre del usuario
    fun onNameChanged(newName: String) {
        userName.value = newName
    }

    // Función para guardar el nombre del usuario en DataStore
    fun login() {
        viewModelScope.launch {
            userRepository.saveUserName(userName.value)
        }
    }

    // Función para verificar si el usuario ya está logueado
    suspend fun checkIfLoggedIn(): Boolean {
        return userRepository.getUserName() != null
    }
}
