package com.uvg.rickandmorty.presentation.mainFlow.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.uvg.rickandmorty.data.repository.UserRepository

class ProfileViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
