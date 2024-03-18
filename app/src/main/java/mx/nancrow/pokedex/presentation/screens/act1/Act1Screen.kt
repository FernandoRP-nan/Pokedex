package mx.nancrow.pokedex.presentation.screens.act1

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import mx.nancrow.pokedex.presentation.composables.buttons.BasicButton
import mx.pokedex.presentation.composables.screens.Screen
import mx.nancrow.pokedex.presentation.composables.textfields.TextFieldSearch
import mx.nancrow.pokedex.presentation.navigation.Screens
import mx.pokedex.presentation.theme.LocalSpacing
import mx.nancrow.pokedex.R

@Composable
fun Act1Screen(
    navController: NavController,
    viewModel: Act1ViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    var isPasswordVisible by rememberSaveable { mutableStateOf("") }

    Screen(
        navController = navController,
        buttonBack = false,
        currentRoute = Screens.ACT_1
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary)
                .padding(top = spacing.spaceMedium)
                .padding(horizontal = spacing.spaceLarge)
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            TextFieldSearch(
                value = isPasswordVisible,
                onValueTextChange = { isPasswordVisible = it.lowercase() },
                modifier = Modifier
                    .height(30.dp),
                hint = "Buscar pokemon"
            )
            viewModel.state.pokemon?.let {
                Text(text = it.name)
                Text(
                    text = it.name,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier,
                )
            }

        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .fillMaxHeight(.3f)
                .clip(RoundedCornerShape(10.dp, 10.dp, 0.dp, 0.dp))
                .background(MaterialTheme.colorScheme.background)
        ) {
            BasicButton(label = R.string.obtener) {
                viewModel.onEvent(Act1ViewEvent.GetRandomPokemon)
            }
        }
    }
}
