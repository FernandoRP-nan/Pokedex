package mx.nancrow.pokedex.presentation.screens.act1

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import mx.nancrow.pokedex.presentation.composables.buttons.BasicButton
import mx.nancrow.pokedex.presentation.composables.screens.Screen
import mx.nancrow.pokedex.presentation.navigation.Screens
import mx.pokedex.presentation.theme.LocalSpacing
import mx.nancrow.pokedex.R

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
        title = " ${viewModel.state.pokemon?.id} ${viewModel.state.pokemon?.name?.capitalize()}"
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
            Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(4f)
            ) {
                Box(
                    modifier = Modifier
                        .border(
                            width = 10.dp,
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .clip(RoundedCornerShape(10.dp))
                        .clip(RectangleShape)
                        .weight(1f)
                        .background(MaterialTheme.colorScheme.background)

                ) {
                    viewModel.state.pokemon?.let {
                        AsyncImage(
                            model = it.sprites.frontDefault,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(spacing.spaceMedium),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                ) {

                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .border(
                            width = 10.dp,
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .clip(RoundedCornerShape(10.dp))
                        .clip(RectangleShape)
                        .weight(1f)
                        .background(MaterialTheme.colorScheme.background)

                ) {

                }
                Box(
                    modifier = Modifier
                        .weight(.5f)
                ) {

                }
            }
            Column(
                modifier = Modifier
                    .padding(horizontal = spacing.spaceSmall)
                    .fillMaxWidth()
                    .weight(5.5f)
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colorScheme.background)
                    .border(
                        width = 10.dp,
                        color = MaterialTheme.colorScheme.onSurface,
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
                            color = MaterialTheme.colorScheme.onBackground,
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
                    label = R.string.save
                ) {
                    viewModel.onEvent(Act1ViewEvent.GetPokemon(pokemonData))
                }
            }
        }
    }
}
