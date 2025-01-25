package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.myapplication.screens.DetailScreen
import com.example.myapplication.screens.LoginScreen
import com.example.myapplication.screens.PostsScreen

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(navController)
        }
        composable("posts") {
            PostsScreen(navController)
        }
        composable("posts/{postId}") { backStackEntry ->
            val postId = backStackEntry.arguments?.getString("postId")?.toIntOrNull()
            if (postId != null) {
                DetailScreen(postId = postId, navController = navController)
            }
        }
    }
}
