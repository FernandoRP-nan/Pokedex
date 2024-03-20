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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color
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
import mx.nancrow.pokedex.domain.model.network.response.PokemonSearchResponse

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    var isPasswordVisible by rememberSaveable { mutableStateOf("") }

    Screen(
        navController = navController,
        buttonBack = false,
        currentRoute = Screens.HOME,
        title = stringResource(id = R.string.title_home)
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
                onValueTextChange = {
                    viewModel.onEvent(HomeViewEvent.LoadSearchData)
                    viewModel.onEvent(HomeViewEvent.UpdateFilter(it))
                    isPasswordVisible = it.lowercase()
                },
                modifier = Modifier.height(50.dp),
                hint = stringResource(id = R.string.search_pokemon)
            )
            Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))
            Column(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                if (viewModel.state.searchQuery == "") {
                    viewModel.state.listPokemon?.let {
                        LazyColumn(verticalArrangement = Arrangement.spacedBy(spacing.spaceSmall)) {
                            itemsIndexed(it) { index, pokemon ->
                                PokemonItem(modifier = Modifier.height(90.dp), pokemon = pokemon) {
                                    navController.currentBackStackEntry?.savedStateHandle?.set(
                                        "pokemonData",
                                        pokemon.id
                                    )
                                    navController.navigate(Screens.ACT_1)
                                }
                                if (index == it.lastIndex && !viewModel.state.isLoading) {
                                    viewModel.onEvent(HomeViewEvent.LoadMoreData)
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(spacing.spaceLarge))
                    }
                } else {
                    viewModel.state.listFilterPokemonSearch?.let {
                        LazyColumn(verticalArrangement = Arrangement.spacedBy(spacing.spaceSmall)) {
                            itemsIndexed(it) { index, pokemon ->
                                if (pokemon.id != 0) {
                                    PokemonFilterItem(
                                        modifier = Modifier.height(60.dp),
                                        pokemon = pokemon
                                    ) {
                                        navController.currentBackStackEntry?.savedStateHandle?.set(
                                            "pokemonData",
                                            pokemon.id
                                        )
                                        navController.navigate(Screens.ACT_1)
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(spacing.spaceLarge))
                    }
                }
            }
        }
    }
}

@Composable
fun PokemonItem(modifier: Modifier, pokemon: PokemonResponse, onClick: () -> Unit) {
    val spacing = LocalSpacing.current
    Row(
        modifier = modifier
            .clickable { onClick() }
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
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .offset(y = (5).dp)
                .weight(3.5f),
        )
    }
}

@Composable
fun PokemonFilterItem(modifier: Modifier, pokemon: PokemonSearchResponse, onClick: () -> Unit) {
    val spacing = LocalSpacing.current
    Row(
        modifier = modifier
            .clickable { onClick() }
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.background),
        horizontalArrangement = Arrangement.spacedBy(spacing.spaceSmall),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${pokemon.id} ${pokemon.name.capitalize()}",
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier
                .offset(y = (5).dp)
                .padding(start = spacing.spaceMedium)
        )
        val typeNames: List<String> = pokemon.types.map { it.type.name }

        typeNames.forEach {
            PokemonTypeItem(modifier = Modifier.height(20.dp), pokemonType = it)
        }
    }
}

@Composable
fun PokemonTypeItem(modifier: Modifier, pokemonType: String) {
    val spacing = LocalSpacing.current
    Box(
        modifier = modifier
            .clip(shape = RoundedCornerShape(30.dp))
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSecondary,
                shape = RoundedCornerShape(30.dp)
            )
            //.background(Color(0xFF969EBD))
            .background(MaterialTheme.colorScheme.onPrimary),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = pokemonType.capitalize(),
            color = MaterialTheme.colorScheme.onSecondary,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .padding(horizontal = spacing.spaceExtraSmall)
                .offset(y = (1).dp)
        )
    }
}