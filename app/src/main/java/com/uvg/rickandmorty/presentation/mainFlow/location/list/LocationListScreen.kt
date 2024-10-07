package com.uvg.rickandmorty.presentation.mainFlow.location.list

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uvg.rickandmorty.data.model.Location
import com.uvg.rickandmorty.presentation.mainFlow.character.list.ErrorScreen
import com.uvg.rickandmorty.presentation.mainFlow.character.list.LoadingScreen

@Composable
fun LocationListScreen(
    onLocationClick: (Int) -> Unit,
    viewModel: LocationListViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()

    when {
        state.isLoading -> {
            LoadingScreen { viewModel.showError() }
        }
        state.hasError -> {
            ErrorScreen { viewModel.retry() }
        }
        else -> {
            state.data?.let { locations ->
                LocationListContent(locations = locations, onLocationClick = onLocationClick)
            }
        }
    }
}

@Composable
fun LocationListContent(
    locations: List<Location>,
    onLocationClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(locations) { location ->
            Text(
                text = location.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onLocationClick(location.id) }
                    .padding(16.dp),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
