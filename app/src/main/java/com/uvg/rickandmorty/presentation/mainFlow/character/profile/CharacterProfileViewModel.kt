package com.uvg.rickandmorty.presentation.mainFlow.character.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uvg.rickandmorty.data.model.Character
import com.uvg.rickandmorty.data.source.CharacterDb
import com.uvg.rickandmorty.presentation.mainFlow.character.list.CharacterListState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class CharacterProfileState(
    val isLoading: Boolean = false,
    val character: Character? = null,
    val hasError: Boolean = false
)

class CharacterProfileViewModel : ViewModel() {
    private val _state = MutableStateFlow(CharacterProfileState())
    val state: StateFlow<CharacterProfileState> get() = _state

    fun fetchCharacter(characterId: Int) {
        viewModelScope.launch {
            _state.value = CharacterProfileState(isLoading = true)
            delay(2000) // Simulando una llamada de red con un retraso de 2 segundos
            try {
                // Obtención del personaje por ID utilizando CharacterDb
                val characterDb = CharacterDb()
                val character = characterDb.getCharacterById(characterId)
                _state.value = CharacterProfileState(character = character)
            } catch (e: Exception) {
                _state.value = CharacterProfileState(hasError = true)
            }
        }
    }

    fun showError() {
        _state.value = CharacterProfileState(hasError = true)
    }

    fun retry(characterId: Int) {
        fetchCharacter(characterId)
    }
}


