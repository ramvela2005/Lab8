package com.uvg.rickandmorty.presentation.mainFlow.location.profile

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
import com.uvg.rickandmorty.data.model.Location
import com.uvg.rickandmorty.presentation.mainFlow.character.profile.ErrorScreen
import com.uvg.rickandmorty.presentation.mainFlow.character.profile.LoadingScreen
import com.uvg.rickandmorty.presentation.ui.theme.RickAndMortyTheme

@Composable
fun LocationProfileScreen(
    locationId: Int,
    onNavigateBack: () -> Unit,
    viewModel: LocationProfileViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchLocation(locationId)
    }

    when {
        state.isLoading -> {
            LoadingScreen { viewModel.retry(locationId) }
        }
        state.hasError -> {
            ErrorScreen { viewModel.retry(locationId) }
        }
        else -> {
            state.location?.let { location ->
                LocationProfileContent(location = location, onNavigateBack = onNavigateBack)
            }
        }
    }
}

@Composable
fun LocationProfileContent(
    location: Location,
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
            text = location.name,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Tipo: ${location.type}")
        Text(text = "Dimensión: ${location.dimension}")
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewLocationProfileScreen() {
    RickAndMortyTheme {
        Surface {
            LocationProfileScreen(
                locationId = 1234,
                onNavigateBack = { }
            )
        }
    }
}
