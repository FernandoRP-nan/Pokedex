package mx.nancrow.pokedex.presentation.composables.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import mx.nancrow.pokedex.R
import mx.pokedex.presentation.composables.images.ImageNormal
import mx.pokedex.presentation.theme.LocalSpacing

@Composable
fun CustomTopBar(
    title: String? = null,
    iconColors: Color = MaterialTheme.colorScheme.onPrimary
) {
    val spacing = LocalSpacing.current
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Row(
            modifier = Modifier.weight(5f),
            horizontalArrangement = Arrangement.spacedBy(spacing.spaceExtraSmall),
            //verticalAlignment = Alignment.CenterVertically
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
