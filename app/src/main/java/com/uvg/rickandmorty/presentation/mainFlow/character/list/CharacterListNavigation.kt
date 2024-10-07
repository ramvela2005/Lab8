package com.uvg.rickandmorty.presentation.mainFlow.character.list

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object CharacterListDestination

fun NavGraphBuilder.characterListScreen(
    onCharacterClick: (Int) -> Unit
) {
    composable<CharacterListDestination> {
        CharacterListRoute(onCharacterClick = onCharacterClick)
    }
}

@Composable
fun CharacterListRoute(
    onCharacterClick: (Int) -> Unit
) {
    // Instantiate the ViewModel using viewModel() function
    val viewModel: CharacterListViewModel = viewModel()

    // Pass the viewModel and the onCharacterClick function to the screen
    CharacterListScreen(
        onCharacterClick = onCharacterClick,
        viewModel = viewModel
    )
}
