package com.uvg.rickandmorty.presentation.mainFlow.location.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.uvg.rickandmorty.data.model.Location
import com.uvg.rickandmorty.data.source.LocationDb

data class LocationListState(
    val isLoading: Boolean = false,
    val data: List<Location>? = null,
    val hasError: Boolean = false
)

class LocationListViewModel : ViewModel() {
    private val _state = MutableStateFlow(LocationListState())
    val state: StateFlow<LocationListState> get() = _state

    init {
        fetchLocations()
    }

    fun fetchLocations() {
        viewModelScope.launch {
            _state.value = LocationListState(isLoading = true)
            delay(4000) // Simulando una llamada de red con un retraso de 4 segundos
            try {
                // Obtención de datos de ubicación usando LocationDb
                val locationDb = LocationDb()
                val locations = locationDb.getAllLocations()
                _state.value = LocationListState(data = locations)
            } catch (e: Exception) {
                _state.value = LocationListState(hasError = true)
            }
        }
    }

    fun retry() {
        fetchLocations()
    }
}
