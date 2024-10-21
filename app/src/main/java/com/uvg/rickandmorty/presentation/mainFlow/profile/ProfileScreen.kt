package com.uvg.rickandmorty.presentation.mainFlow.profile

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uvg.rickandmorty.presentation.ui.theme.RickAndMortyTheme

@Composable
fun ProfileRoute(
    onLogOutClick: () -> Unit,
    viewModel: ProfileViewModel
) {
    val userName = viewModel.userName.collectAsState().value
    ProfileScreen(
        userName = userName,
        onLogOutClick = {
            viewModel.logOut()
            onLogOutClick()
        },
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun ProfileScreen(
    userName: String,
    onLogOutClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(64.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .background(MaterialTheme.colorScheme.secondaryContainer, shape = CircleShape)
        ) {
            Icon(
                Icons.Outlined.Person,
                contentDescription = "Person",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                tint = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Nombre:")
            Text(text = userName) // Mostramos el nombre del usuario
        }
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedButton(onClick = onLogOutClick) {
            Text("Cerrar sesión")
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewProfileScreen() {
    RickAndMortyTheme {
        ProfileScreen(
            userName = "Rick Sanchez",
            onLogOutClick = { /*TODO*/ },
            modifier = Modifier.fillMaxSize()
        )
    }
}
