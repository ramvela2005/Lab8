package com.uvg.rickandmorty.presentation.login

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uvg.rickandmorty.data.repository.UserRepository
import com.uvg.rickandmorty.dataStore

@Composable
fun LoginRoute(
    onLoginClick: () -> Unit,
    viewModel: LoginViewModel = viewModel(factory = LoginViewModelFactory(UserRepository(LocalContext.current.dataStore)))
) {
    LoginScreen(
        viewModel = viewModel,
        onLoginSuccess = onLoginClick
    )
}
