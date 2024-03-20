package mx.nancrow.pokedex.presentation.screens.act3

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import mx.nancrow.pokedex.R
import mx.nancrow.pokedex.presentation.composables.screens.Screen
import mx.nancrow.pokedex.presentation.navigation.Screens
import mx.pokedex.presentation.theme.LocalSpacing


@Composable
fun Act3Screen(
    navController: NavController,
    viewModel: Act3ViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current

    Screen(
        navController = navController,
        buttonBack = false,
        currentRoute = Screens.ACT_3,
        title = stringResource(id = R.string.title_config)
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary)
                .padding(top = spacing.spaceMedium)
                .padding(horizontal = spacing.spaceLarge)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(spacing.spaceSmall),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                viewModel.state.itemsSettings.forEach {
                    ItemConfiguration(
                        modifier = Modifier.height(60.dp),
                        label = stringResource(id = it.label),
                        icon = it.icon,
                        colorTheme = MaterialTheme.colorScheme.onPrimary

                    ) {
                        viewModel.onEvent(Act3ViewEvent.OnClickSettings(it.label))
                        if (it.label == R.string.change_mode)
                            Toast.makeText(
                                context,
                                "Tema aplicado, reinicia la aplicación para que surfa efecto",
                                Toast.LENGTH_LONG
                            ).show()
                    }
                    HorizontalDivider(thickness = 2.dp, color = MaterialTheme.colorScheme.onPrimary)
                }
                Spacer(modifier = Modifier.height(spacing.spaceSmall))
                Text(
                    text = stringResource(id = R.string.made_by),
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .offset(y = (5).dp)
                        .padding(start = spacing.spaceMedium)
                )
                Spacer(modifier = Modifier.height(spacing.spaceMedium))
                Text(
                    text = stringResource(id = R.string.my_name),
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.bodyMedium,
                    lineHeight = 20.sp,
                    modifier = Modifier
                        .offset(y = (5).dp)
                        .padding(start = spacing.spaceMedium)
                )
            }
        }
    }
}

@Composable
fun ItemConfiguration(
    modifier: Modifier,
    label: String,
    icon: Int,
    colorTheme: Color,
    onClick: () -> Unit
) {
    val spacing = LocalSpacing.current
    Row(
        modifier = modifier
            .clickable { onClick() }
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp)),
        horizontalArrangement = Arrangement.spacedBy(spacing.spaceSmall),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .fillMaxSize()
                .weight(2.5f),
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = colorTheme
        )
        Text(
            text = label,
            color = colorTheme,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .offset(y = (5).dp)
                .padding(start = spacing.spaceMedium)
                .weight(7.5f)
        )
    }
}
