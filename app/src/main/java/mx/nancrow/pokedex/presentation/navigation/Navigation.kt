package mx.nancrow.pokedex.presentation.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import mx.nancrow.pokedex.presentation.screens.act1.Act1Screen
import mx.nancrow.pokedex.presentation.screens.act2.Act2Screen
import mx.nancrow.pokedex.presentation.screens.act3.Act3Screen
import mx.nancrow.pokedex.presentation.screens.home.HomeScreen

@Composable
fun Navigation(navController: NavController) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = Screens.HOME,
    ) {
        composable(Screens.HOME) {
            HomeScreen(navController)
        }
        composable(Screens.ACT_1) {
            val pokemonData =
                navController.previousBackStackEntry?.savedStateHandle?.get<Int>("pokemonData")
                    ?: 0
            Act1Screen(navController, pokemonData = pokemonData)
        }
        composable(Screens.ACT_2) {
            Act2Screen(navController)
        }
        composable(Screens.ACT_3) {
            Act3Screen(navController)
        }
    }
}