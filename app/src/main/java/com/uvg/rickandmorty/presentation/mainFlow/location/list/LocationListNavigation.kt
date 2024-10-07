package com.uvg.rickandmorty.presentation.mainFlow.location.list

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object LocationListDestination

fun NavGraphBuilder.locationListScreen(
    onLocationClick: (Int) -> Unit
) {
    composable<LocationListDestination> {
        LocationListRoute(onLocationClick = onLocationClick)
    }
}

@Composable
fun LocationListRoute(
    onLocationClick: (Int) -> Unit
) {
    // Instantiate the ViewModel using viewModel() function
    val viewModel: LocationListViewModel = viewModel()

    // Pass the viewModel and the onLocationClick function to the screen
    LocationListScreen(
        onLocationClick = onLocationClick,
        viewModel = viewModel
    )
}
