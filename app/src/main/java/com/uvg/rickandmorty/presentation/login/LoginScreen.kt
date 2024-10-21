package com.uvg.rickandmorty.presentation.login

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uvg.rickandmorty.R
import com.uvg.rickandmorty.data.repository.UserRepository
import com.uvg.rickandmorty.presentation.ui.theme.RickAndMortyTheme

@Composable
fun LoginScreen(viewModel: LoginViewModel, onLoginSuccess: () -> Unit) {
    val name = viewModel.userName.collectAsState().value

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        // Imagen de Rick and Morty (debe estar en la carpeta de recursos 'drawable')
        Image(
            painter = painterResource(id = R.drawable.rickmorty_logo),
            contentDescription = "Rick and Morty Logo",
            modifier = Modifier.size(200.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Campo de texto para el nombre
        TextField(
            value = name,
            onValueChange = { viewModel.onNameChanged(it) },
            label = { Text("Nombre") },
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón de Iniciar sesión
        Button(
            onClick = {
                viewModel.login()
                onLoginSuccess()
            },
            enabled = name.isNotEmpty(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Iniciar sesión")
        }
    }
}

