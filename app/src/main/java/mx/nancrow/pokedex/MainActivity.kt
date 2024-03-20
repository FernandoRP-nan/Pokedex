package mx.nancrow.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import mx.nancrow.pokedex.domain.preferences.Preferences
import mx.nancrow.pokedex.presentation.navigation.Navigation
import mx.nancrow.pokedex.presentation.theme.PokedexTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    companion object {
        val _networkAvailable = MutableStateFlow(true)
        val networkAvailable = _networkAvailable.asStateFlow()
    }

    @Inject
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexTheme (darkTheme = preferences.loadDarkTheme()) {
                Navigation(navController = rememberNavController())
                val checkNetworkAvailable by networkAvailable
                    .collectAsStateWithLifecycle()
            }
        }
    }
}