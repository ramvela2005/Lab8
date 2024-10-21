package com.uvg.rickandmorty.presentation.mainFlow.profile

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import com.uvg.rickandmorty.data.repository.UserRepository
import com.uvg.rickandmorty.dataStore // Asegúrate de que este import está definido correctamente

@Serializable
data object ProfileDestination

fun NavGraphBuilder.profileScreen(
    onLogOutClick: () -> Unit
) {
    composable<ProfileDestination> {
        val context = LocalContext.current

        // Crear UserRepository manualmente
        val userRepository = UserRepository(context.dataStore)

        // Crear ProfileViewModel usando ProfileViewModelFactory
        val viewModelFactory = ProfileViewModelFactory(userRepository)
        val viewModel: ProfileViewModel = viewModel(factory = viewModelFactory)

        // Llamar a ProfileRoute pasando el viewModel
        ProfileRoute(
            onLogOutClick = onLogOutClick,
            viewModel = viewModel
        )
    }
}
