package com.uvg.rickandmorty.presentation.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigateToLogin: () -> Unit,
    onNavigateToCharacterList: () -> Unit,
    isUserLoggedIn: Boolean
) {
    LaunchedEffect(Unit) {
        delay(2000) // Simulación de tiempo de espera de 2 segundos
        if (isUserLoggedIn) {
            onNavigateToCharacterList() // Navegar a la lista de personajes si está logueado
        } else {
            onNavigateToLogin() // Navegar al login si no está logueado
        }
    }
}

@Preview
@Composable
fun PreviewSplashScreen() {
    SplashScreen(
        onNavigateToLogin = {},
        onNavigateToCharacterList = {},
        isUserLoggedIn = false
    )
}
