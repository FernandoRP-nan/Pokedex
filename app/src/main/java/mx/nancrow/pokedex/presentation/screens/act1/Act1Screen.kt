package mx.nancrow.pokedex.presentation.screens.act1

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import mx.nancrow.pokedex.presentation.composables.buttons.BasicButton
import mx.nancrow.pokedex.presentation.composables.screens.Screen
import mx.nancrow.pokedex.presentation.navigation.Screens
import mx.pokedex.presentation.theme.LocalSpacing
import mx.nancrow.pokedex.R
import mx.nancrow.pokedex.presentation.screens.home.PokemonTypeItem

@Composable
fun Act1Screen(
    navController: NavController,
    viewModel: Act1ViewModel = hiltViewModel(),
    pokemonData: Int
) {
    val spacing = LocalSpacing.current

    viewModel.onEvent(Act1ViewEvent.GetPokemon(pokemonData))

    Screen(
        navController = navController,
        buttonBack = true,
        currentRoute = Screens.ACT_1,
        title = "${viewModel.state.pokemon?.id} ${viewModel.state.pokemon?.name?.capitalize()}"
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary)
                .padding(top = spacing.spaceMedium)
                .padding(horizontal = spacing.spaceSmall)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(spacing.spaceMedium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(4.4f)
            ) {
                Column(
                    modifier = Modifier
                        .padding(top = spacing.spaceMedium)
                        .padding(horizontal = spacing.spaceMedium)
                        .weight(.9f),
                    verticalArrangement = Arrangement.spacedBy(spacing.spaceExtraSmall)
                ) {
                    Box(
                        modifier = Modifier
                            .border(
                                width = 6.dp,
                                color = MaterialTheme.colorScheme.primaryContainer,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .clip(RoundedCornerShape(10.dp))
                            .clip(RectangleShape)
                            .background(MaterialTheme.colorScheme.background)
                            .fillMaxWidth()
                    ) {
                        viewModel.state.pokemon?.let {
                            AsyncImage(
                                model = it.sprites.frontDefault,
                                contentDescription = null,
                                modifier = Modifier
                                    .aspectRatio(1f)
                                    .fillMaxSize()
                                    .padding(spacing.spaceMedium),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }

                    viewModel.state.pokemon?.abilities?.let {
                        Column(
                            modifier = Modifier.weight(1.1f),
                        ) {
                            it.forEach {
                                PokemonAbilityItem(
                                    modifier = Modifier
                                        .height(30.dp)
                                        .fillMaxWidth(),
                                    pokemonAbility = it.ability.name
                                )
                            }
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .weight(1.1f)
                ) {
                    viewModel.state.pokemon?.let {
                        it.stats.forEach { stats ->
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Text(
                                    text = stats.stat.name.capitalize(),
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight(600),
                                    modifier = Modifier.offset(y = (5).dp),
                                    textAlign = TextAlign.Justify,
                                    lineHeight = 15.sp,
                                )
                                Text(
                                    text = "${stats.base_stat}",
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight(600),
                                    modifier = Modifier
                                        .offset(y = (5).dp)
                                        .align(Alignment.CenterEnd),
                                    textAlign = TextAlign.Justify,
                                    lineHeight = 15.sp,
                                )
                            }
                            BarScore(
                                Modifier.padding(top = 10.dp, bottom = 5.dp),
                                stats.base_stat,
                                MaterialTheme.colorScheme.background,
                                MaterialTheme.colorScheme.secondary
                            )
                        }
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacing.spaceSmall)
                    .weight(.6f)

            ) {
                viewModel.state.pokemon?.types?.let {
                    Row(
                        modifier = Modifier.weight(.9f),
                        horizontalArrangement = Arrangement.spacedBy(spacing.spaceMedium)
                    ) {
                        it.forEach {
                            PokemonTypeItem(
                                modifier = Modifier.height(30.dp),
                                pokemonType = it.type.name
                            )
                        }
                    }
                }
                viewModel.state.pokemon?.let {
                    val height: Float = it.height.toFloat() / 10
                    val weight: Float = it.height.toFloat() / 10
                    Text(
                        text = "Altura:\n${height} m ",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight(500),
                        modifier = Modifier.offset(
                            y = (5).dp
                        ),
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.sp
                    )
                    Spacer(modifier = Modifier.height(spacing.spaceMedium))
                    Text(
                        text = "Peso:\n${weight} kg",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight(500),
                        modifier = Modifier.offset(
                            y = (5).dp//, x = (-5).dp
                        ),
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.sp
                    )
                }
            }

            Box(
                modifier = Modifier
                    .weight(5.5f)
                    .padding(horizontal = spacing.spaceSmall)
                    .border(
                        width = 10.dp,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = spacing.spaceSmall)
                        .clip(RoundedCornerShape(6.dp))
                        .background(MaterialTheme.colorScheme.background)
                        .border(
                            width = 10.dp,
                            color = MaterialTheme.colorScheme.onSurface,
                            shape = RoundedCornerShape(6.dp)
                        ), horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .weight(7f)
                            .padding(horizontal = spacing.spaceLarge)
                            .verticalScroll(rememberScrollState()),
                        contentAlignment = Alignment.Center
                    ) {
                        viewModel.state.pokemon?.let {
                            Text(
                                text = "${"Descripción:"} \n\n${it.description.capitalize()}",
                                color = MaterialTheme.colorScheme.onSecondary,
                                style = MaterialTheme.typography.labelLarge,
                                modifier = Modifier.offset(y = (5).dp),
                                textAlign = TextAlign.Justify
                            )
                        }
                    }
                    BasicButton(
                        modifier = Modifier
                            .weight(3f)
                            .padding(bottom = spacing.spaceLarge),
                        label = if (viewModel.state.exist) {
                            R.string.delete
                        } else {
                            R.string.save
                        },
                        bold = true
                    ) {
                        viewModel.state.pokemon?.let {
                            viewModel.onEvent(Act1ViewEvent.FavoritePokemon(it))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BarScore(modifier: Modifier, progress: Int, startColor: Color, endColor: Color) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(15.dp)
            .clip(RoundedCornerShape(20.dp))
            .border(2.dp, startColor, shape = RoundedCornerShape(20.dp))
    ) {
        // Barra de fondo amarilla
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(startColor)
        )
        // Barra de progreso con máscara y borde
        Box(
            modifier = Modifier
                .fillMaxWidth(progress / 150.0f) // Corrección aquí
                .height(20.dp)
                .background(if (progress >= 150) startColor else endColor)
                .clip(RoundedCornerShape(20.dp))
        )
    }
}

@Composable
fun PokemonAbilityItem(modifier: Modifier, pokemonAbility: String) {
    val spacing = LocalSpacing.current
    Box(
        modifier = modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onBackground,
            )
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = pokemonAbility.capitalize(),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .padding(horizontal = spacing.spaceExtraSmall)
                .offset(y = (1).dp)
        )
    }
}