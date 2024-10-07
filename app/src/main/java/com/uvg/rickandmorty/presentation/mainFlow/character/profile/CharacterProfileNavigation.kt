package com.uvg.rickandmorty.presentation.mainFlow.character.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data class CharacterProfileDestination(
    val characterId: Int
)

fun NavController.navigateToCharacterProfileScreen(
    characterId: Int,
    navOptions: NavOptions? = null
) {
    navigate("character_profile/$characterId", navOptions)
}

fun NavGraphBuilder.characterProfileScreen(
    onNavigateBack: () -> Unit
) {
    composable("character_profile/{characterId}") { backStackEntry ->
        val characterId = backStackEntry.arguments?.getString("characterId")?.toIntOrNull() ?: return@composable
        CharacterProfileScreen(
            characterId = characterId,
            onNavigateBack = onNavigateBack
        )
    }
}
