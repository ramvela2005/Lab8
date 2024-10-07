package com.uvg.rickandmorty.presentation.mainFlow.character.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.uvg.rickandmorty.data.model.Character
import com.uvg.rickandmorty.data.source.CharacterDb

data class CharacterListState(
    val isLoading: Boolean = false,
    val data: List<Character>? = null,
    val hasError: Boolean = false
)

class CharacterListViewModel : ViewModel() {
    private val _state = MutableStateFlow(CharacterListState())
    val state: StateFlow<CharacterListState> get() = _state

    init {
        fetchCharacters()
    }

    fun fetchCharacters() {
        viewModelScope.launch {
            _state.value = CharacterListState(isLoading = true)
            delay(4000) // Simulando una llamada de red con un retraso de 4 segundos
            try {
                // Obtención de datos usando CharacterDb
                val characterDb = CharacterDb()
                val characters = characterDb.getAllCharacters()
                _state.value = CharacterListState(data = characters)
            } catch (e: Exception) {
                _state.value = CharacterListState(hasError = true)
            }
        }
    }

    fun retry() {
        fetchCharacters()
    }
}
