package com.uvg.rickandmorty.presentation.mainFlow.location.profile

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data class LocationProfileDestination(
    val locationId: Int
)

fun NavController.navigateToLocationProfileScreen(
    locationId: Int,
    navOptions: NavOptions? = null
) {
    navigate("location_profile/$locationId", navOptions)
}

fun NavGraphBuilder.locationProfileScreen(
    onNavigateBack: () -> Unit
) {
    composable("location_profile/{locationId}") { backStackEntry ->
        val locationId = backStackEntry.arguments?.getString("locationId")?.toIntOrNull() ?: return@composable
        LocationProfileRoute(
            locationId = locationId,
            onNavigateBack = onNavigateBack
        )
    }
}

@Composable
fun LocationProfileRoute(
    locationId: Int,
    onNavigateBack: () -> Unit
) {
    // Instantiate the ViewModel using viewModel() function
    val viewModel: LocationProfileViewModel = viewModel()

    // Pass the locationId, onNavigateBack, and viewModel to the screen
    LocationProfileScreen(
        locationId = locationId,
        onNavigateBack = onNavigateBack,
        viewModel = viewModel
    )
}
