package mx.nancrow.pokedex.presentation.screens.act2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import mx.nancrow.pokedex.R
import mx.nancrow.pokedex.domain.model.network.response.PokemonResponse
import mx.nancrow.pokedex.domain.model.network.response.Sprites
import mx.nancrow.pokedex.presentation.navigation.Screens
import mx.nancrow.pokedex.presentation.composables.screens.Screen
import mx.nancrow.pokedex.presentation.screens.act3.Act3ViewModel
import mx.nancrow.pokedex.presentation.screens.home.PokemonItem
import mx.pokedex.presentation.theme.LocalSpacing

@Composable
fun Act2Screen(
    navController: NavController,
    viewModel: Act2ViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    Screen(
        navController = navController,
        buttonBack = false,
        currentRoute = Screens.ACT_2,
        title = stringResource(id = R.string.title_favorites)
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary)
                .padding(top = spacing.spaceMedium)
                .padding(horizontal = spacing.spaceSmall)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(spacing.spaceSmall),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))
            Column(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                viewModel.state.listPokemon.let {
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(spacing.spaceSmall)) {
                        itemsIndexed(it) { index, pokemon ->
                            PokemonItem(
                                modifier = Modifier.height(90.dp),
                                pokemon = PokemonResponse(
                                    id = pokemon.id,
                                    name = pokemon.name,
                                    sprites = Sprites(pokemon.sprites)
                                )
                            ) {
                                navController.currentBackStackEntry?.savedStateHandle?.set(
                                    "pokemonData",
                                    pokemon.id
                                )
                                navController.navigate(Screens.ACT_1)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(spacing.spaceLarge))
                }
            }
        }
    }
}
