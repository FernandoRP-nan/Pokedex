package mx.nancrow.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import mx.pokedex.presentation.navigation.Navigation
import mx.pokedex.presentation.theme.PmaTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PmaTheme {
                Navigation(navController = rememberNavController())
            }
        }
    }
}