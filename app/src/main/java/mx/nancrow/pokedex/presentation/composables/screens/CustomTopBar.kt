package mx.nancrow.pokedex.presentation.composables.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import mx.nancrow.pokedex.R
import mx.pokedex.presentation.composables.images.ImageNormal
import mx.pokedex.presentation.theme.LocalSpacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(
    navController: NavController,
    buttonBack: Boolean = true,
    title: String? = null,
    @DrawableRes profileIcon: Int? = null,
    iconColors: Color = MaterialTheme.colorScheme.background
) {
    val spacing = LocalSpacing.current
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .padding(start = 20.dp)
                .weight(1f)
        ) {
            if (buttonBack) {
                IconButton(modifier = Modifier,
                    onClick = { navController.popBackStack() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_left),
                        contentDescription = null,
                        tint = iconColors,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
        Row(
            modifier = Modifier.weight(5f),
            horizontalArrangement = Arrangement.spacedBy(spacing.spaceSmall),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ImageNormal(
                modifier = Modifier.size(30.dp), imageName = R.drawable.poke_ball_icon
            )
            if (title != null) {
                Text(
                    text = title,
                    modifier = Modifier,
                    style = MaterialTheme.typography.bodySmall,
                    color = iconColors
                )
            }
        }
        Box(
            modifier = Modifier
                .padding(start = 30.dp)
                .weight(1f)
        ) {
            if (profileIcon != null) {
                Image(
                    modifier = Modifier.clip(RoundedCornerShape(50.dp)),
                    painter = painterResource(id = profileIcon),
                    contentDescription = null,
                )
            }
        }
    }
}
