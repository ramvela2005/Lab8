package com.uvg.rickandmorty.presentation.mainFlow.character.profile

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uvg.rickandmorty.data.model.Character
import com.uvg.rickandmorty.presentation.mainFlow.character.list.ErrorScreen
import com.uvg.rickandmorty.presentation.mainFlow.character.list.LoadingScreen
import com.uvg.rickandmorty.presentation.ui.theme.RickAndMortyTheme

@Composable
fun CharacterProfileScreen(
    characterId: Int,
    onNavigateBack: () -> Unit,
    viewModel: CharacterProfileViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchCharacter(characterId)
    }

    when {
        state.isLoading -> {
            LoadingScreen { viewModel.showError() }
        }
        state.hasError -> {
            ErrorScreen { viewModel.retry(characterId) }
        }
        else -> {
            state.character?.let { character ->
                CharacterProfileContent(character = character, onNavigateBack = onNavigateBack)
            }
        }
    }
}

@Composable
fun CharacterProfileContent(
    character: Character,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        IconButton(onClick = { onNavigateBack() }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
        }
        Text(
            text = character.name,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Species: ${character.species}")
        Text(text = "Status: ${character.status}")
        Text(text = "Gender: ${character.gender}")
    }
}
