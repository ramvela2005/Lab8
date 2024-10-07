package com.uvg.rickandmorty.presentation.mainFlow.location.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uvg.rickandmorty.data.model.Location
import com.uvg.rickandmorty.data.source.LocationDb
import com.uvg.rickandmorty.presentation.mainFlow.character.list.CharacterListState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class LocationProfileState(
    val isLoading: Boolean = false,
    val location: Location? = null,
    val hasError: Boolean = false
)

class LocationProfileViewModel : ViewModel() {
    private val _state = MutableStateFlow(LocationProfileState())
    val state: StateFlow<LocationProfileState> get() = _state

    fun fetchLocation(locationId: Int) {
        viewModelScope.launch {
            _state.value = LocationProfileState(isLoading = true)
            delay(2000) // Simulando una llamada de red con un retraso de 2 segundos
            try {
                // Obtención de datos de la ubicación usando LocationDb
                val locationDb = LocationDb()
                val location = locationDb.getLocationById(locationId)
                _state.value = LocationProfileState(location = location)
            } catch (e: Exception) {
                _state.value = LocationProfileState(hasError = true)
            }
        }
    }

    fun showError() {
        _state.value = LocationProfileState(hasError = true)
    }

    fun retry(locationId: Int) {
        fetchLocation(locationId)
    }
}
