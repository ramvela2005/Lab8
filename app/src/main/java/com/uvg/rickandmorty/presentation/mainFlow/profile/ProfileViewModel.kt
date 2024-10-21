package com.uvg.rickandmorty.presentation.mainFlow.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uvg.rickandmorty.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _userName = MutableStateFlow("")
    val userName: StateFlow<String> get() = _userName

    init {
        viewModelScope.launch {
            _userName.value = userRepository.getUserName() ?: "Usuario Desconocido"
        }
    }

    fun logOut() {
        viewModelScope.launch {
            userRepository.clearUserName()
        }
    }
}
