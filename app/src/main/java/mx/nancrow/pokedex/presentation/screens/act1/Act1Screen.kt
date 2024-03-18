package mx.nancrow.pokedex.presentation.screens.act1

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import mx.nancrow.pokedex.presentation.composables.buttons.BasicButton
import mx.pokedex.presentation.composables.screens.Screen
import mx.nancrow.pokedex.presentation.composables.textfields.TextFieldSearch
import mx.nancrow.pokedex.presentation.navigation.Screens
import mx.pokedex.presentation.theme.LocalSpacing
import mx.nancrow.pokedex.R
import mx.pokedex.presentation.composables.images.ImageNormal

@Composable
fun Act1Screen(
    navController: NavController, viewModel: Act1ViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    var isPasswordVisible by rememberSaveable { mutableStateOf("") }

    Screen(
        navController = navController, buttonBack = false, currentRoute = Screens.ACT_1
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
            TextFieldSearch(
                value = isPasswordVisible,
                onValueTextChange = { isPasswordVisible = it.lowercase() },
                modifier = Modifier.height(40.dp),
                hint = stringResource(id = R.string.search_pokemon)
            )
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(spacing.spaceSmall),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ImageNormal(
                    modifier = Modifier.size(30.dp), imageName = R.drawable.poke_ball_icon
                )
                viewModel.state.pokemon?.let {
                    Text(
                        text = "${it.id} ${it.name.capitalize()}",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.offset(y = (5).dp),
                    )
                }
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .weight(4f)) {
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
                        .fillMaxHeight(.4f)
                ) {

                }
            }
            Column(
                modifier = Modifier
                    .padding(horizontal = spacing.spaceSmall)
                    .fillMaxWidth()
                    .weight(4f)
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
                        .padding(horizontal = spacing.spaceMedium)
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
                    label = R.string.obtener
                ) {
                    viewModel.onEvent(Act1ViewEvent.GetRandomPokemon)
                }
            }
        }
    }
}
