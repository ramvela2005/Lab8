package com.uvg.rickandmorty.presentation.login

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import com.uvg.rickandmorty.data.repository.UserRepository
import com.uvg.rickandmorty.dataStore // Asegúrate de tener este import correctamente definido

@Serializable
data object LoginDestination

fun NavGraphBuilder.loginScreen(
    onLoginClick: () -> Unit
) {
    composable<LoginDestination> {
        val context = LocalContext.current

        // Crear UserRepository manualmente
        val userRepository = UserRepository(context.dataStore)

        // Crear LoginViewModel usando la LoginViewModelFactory
        val viewModelFactory = LoginViewModelFactory(userRepository)
        val viewModel: LoginViewModel = viewModel(factory = viewModelFactory)

        // Llamar a LoginScreen pasando el viewModel
        LoginScreen(
            viewModel = viewModel,
            onLoginSuccess = onLoginClick
        )
    }
}
