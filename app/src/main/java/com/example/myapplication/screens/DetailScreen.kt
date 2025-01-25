package com.example.myapplication.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myapplication.viewModel.DetailViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavHostController, postId: Int, viewModel: DetailViewModel = viewModel()) {
    val uiState by viewModel.uiState.observeAsState(initial = DetailViewModel.UiState.Loading)

    LaunchedEffect (postId) {
        viewModel.getPostById(postId)
    }

    Scaffold(
        topBar = {
            androidx.compose.material3.TopAppBar(
                title = { Text("Détail du Post $postId") },
                navigationIcon = {
                    androidx.compose.material3.IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        androidx.compose.material3.Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Retour"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        when (uiState) {
            is DetailViewModel.UiState.Loading -> {
                Text("Chargement...", modifier = Modifier.padding(paddingValues).padding(16.dp))
            }
            is DetailViewModel.UiState.Success -> {
                val post = (uiState as DetailViewModel.UiState.Success).data
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(paddingValues)
                        .padding(16.dp)
                ) {
                    Text(text = "User ID: ${post.userId}", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = post.title, style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = post.body, style = MaterialTheme.typography.bodyMedium)
                }
            }
            is DetailViewModel.UiState.Error -> {
                val errorMessage = (uiState as DetailViewModel.UiState.Error).message
                Text(
                    text = errorMessage,
                    color = androidx.compose.ui.graphics.Color.Red,
                    modifier = Modifier.padding(paddingValues).padding(16.dp)
                )
            }
        }
    }
}
