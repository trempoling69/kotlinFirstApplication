package com.example.myapplication.screens

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.viewModel.PostViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.example.myapplication.api.model.PostResponse


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostsScreen(navController: NavHostController, viewModel: PostViewModel= viewModel()) {
    val uiState by viewModel.uiState.observeAsState(initial = PostViewModel.UiState.Loading)
    Scaffold(
        topBar = {
            androidx.compose.material3.CenterAlignedTopAppBar(
                title = {
                    Text(text = "Posts", style = MaterialTheme.typography.titleLarge)
                }
            )
        }
    ) { paddingValues ->
        when (uiState) {
            is PostViewModel.UiState.Loading -> {
                Text("Chargement...", modifier = Modifier.padding(paddingValues).padding(16.dp))
            }
            is PostViewModel.UiState.Success -> {
                val posts = (uiState as PostViewModel.UiState.Success).data
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(paddingValues)
                ) {
                    items(items = posts, itemContent = { item ->
                        Element(post = item, modifier = Modifier.fillMaxWidth().padding(16.dp).clickable { navController.navigate("posts/${item.id}") })
                    })
                }
            }
            is PostViewModel.UiState.Error -> {
                val errorMessage = (uiState as PostViewModel.UiState.Error).message
                Text(
                    text = errorMessage,
                    color = androidx.compose.ui.graphics.Color.Red,
                    modifier = Modifier.padding(paddingValues).padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun Element(post: PostResponse, modifier: Modifier) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = "User ID: ${post.userId}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = post.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = post.body,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}