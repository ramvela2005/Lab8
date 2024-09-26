package com.uvg.rickandmorty.presentation.login

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object LoginDestination

fun NavGraphBuilder.loginScreen(
    onLoginClick: () -> Unit
) {
    composable<LoginDestination> {
        LoginRoute(
            onLoginClick = onLoginClick,
        )
    }
}