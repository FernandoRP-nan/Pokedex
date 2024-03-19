package mx.nancrow.pokedex.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import mx.nancrow.pokedex.presentation.composables.screens.Screen
import mx.nancrow.pokedex.presentation.composables.textfields.TextFieldSearch
import mx.nancrow.pokedex.presentation.navigation.Screens
import mx.pokedex.presentation.theme.LocalSpacing
import mx.nancrow.pokedex.R
import mx.nancrow.pokedex.domain.model.network.response.PokemonResponse

@Composable
fun HomeScreen(
    navController: NavController, viewModel: HomeViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    var isPasswordVisible by rememberSaveable { mutableStateOf("") }

    Screen(
        navController = navController, buttonBack = false, currentRoute = Screens.HOME
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
            TextFieldSearch(
                value = isPasswordVisible,
                onValueTextChange = { isPasswordVisible = it.lowercase() },
                modifier = Modifier.height(40.dp),
                hint = stringResource(id = R.string.search_pokemon)
            )
            Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(spacing.spaceSmall),
            ) {
                viewModel.state.listPokemon?.forEach {
                    PokemonItem(modifier = Modifier.height(90.dp), pokemon = it){
                        navController.currentBackStackEntry?.savedStateHandle?.set(
                            "pokemonData",
                            it
                        )
                        navController.navigate(Screens.ACT_1)
                    }
                }
                Spacer(modifier = Modifier.height(spacing.spaceLarge))
            }
        }
    }
}

@Composable
fun PokemonItem(modifier: Modifier, pokemon: PokemonResponse, onClick: () -> Unit) {
    val spacing = LocalSpacing.current
    Row(
        modifier = modifier.clickable { onClick() }
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.background),
        horizontalArrangement = Arrangement.spacedBy(spacing.spaceMedium),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .padding(spacing.spaceExtraSmall)
                .border(
                    width = 5.dp,
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(10.dp)
                )
                .clip(RoundedCornerShape(10.dp))
                .clip(RectangleShape)
                .weight(1.5f)
                .background(MaterialTheme.colorScheme.background)

        ) {
            AsyncImage(
                model = pokemon.sprites.frontDefault,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit
            )
        }
        Text(
            text = "${pokemon.id} ${pokemon.name.capitalize()}",
            color = MaterialTheme.colorScheme.onSecondary,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .offset(y = (5).dp)
                .weight(3.5f),
        )
    }
}