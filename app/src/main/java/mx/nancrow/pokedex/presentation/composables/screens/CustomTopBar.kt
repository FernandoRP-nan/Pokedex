package mx.nancrow.pokedex.presentation.composables.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
    iconColors: Color = MaterialTheme.colorScheme.onPrimary
) {
    val spacing = LocalSpacing.current
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Row(
            modifier = Modifier.weight(5f),
            horizontalArrangement = Arrangement.spacedBy(spacing.spaceMedium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(spacing.spaceLarge))
            if (title != null) {
                ImageNormal(
                    modifier = Modifier.size(35.dp), imageName = R.drawable.poke_ball_icon
                )
                Text(
                    text = title,
                    modifier = Modifier.padding(top = spacing.spaceSmall),
                    style = MaterialTheme.typography.bodySmall,
                    color = iconColors
                )
            } else {
                ImageNormal(
                    modifier = Modifier.size(50.dp), imageName = R.drawable.poke_ball_icon
                )
            }
        }
    }
}
