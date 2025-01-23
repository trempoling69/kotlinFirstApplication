import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavHost() {
    // Contrôleur de navigation Compose
    val navController = rememberNavController()

    // Scaffold principal de l’application
    Scaffold(
        topBar = {
            // Ici, vous pouvez changer le titre selon l’écran actuel
            val currentRoute = currentRoute(navController)
            AppToolbar(title = currentRoute ?: "Mon Application") {
                // Action sur l’icône de navigation si besoin
                // Ex: navController.popBackStack() pour retour
            }
        }
    ) { innerPadding ->
        // Espace réservé pour le contenu en dessous de la Toolbar
        // (Utilisez innerPadding pour éviter que votre contenu ne soit caché par la top bar)
        NavHost(
            navController = navController,
            startDestination = "list",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("list") {
                // On passe le ViewModel au Composable
                ListScreen(
                    onNavigateToDetail = {
                        navController.navigate("detail")
                    }
                )
            }
            composable("detail") {
            }
            // Ajoutez ici d'autres écrans si nécessaire
        }
    }
}

@Composable
fun NavHost(navController: NavHostController, startDestination: String, modifier: Any, content: () -> Unit) {

}

@Composable
fun AppToolbar(title: String, content: () -> Unit) {

}

fun currentRoute(navController: NavController): String? {
    val destination = navController.currentBackStackEntry?.destination
    return destination?.hierarchy?.joinToString("-") { it.route ?: "" }
        ?.split("-")
        ?.lastOrNull()
}