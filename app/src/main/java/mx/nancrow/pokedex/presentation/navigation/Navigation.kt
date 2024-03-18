package mx.nancrow.pokedex.presentation.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import mx.nancrow.pokedex.presentation.navigation.Screens
import mx.nancrow.pokedex.presentation.screens.act1.Act1Screen
import mx.nancrow.pokedex.presentation.screens.act2.Act2Screen

@Composable
fun Navigation(navController: NavController) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = Screens.ACT_1,
    ) {
        composable(Screens.ACT_1) {
            Act1Screen(navController)
        }
        composable(Screens.ACT_2) {
            Act2Screen(navController)
        }
    }
}