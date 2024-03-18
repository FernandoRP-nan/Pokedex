package mx.pokedex.presentation.composables.buttons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration

@Composable
fun TextButton(
    modifier: Modifier,
    label: String,
    color: Color = Color(0xFFBF3C3C),
    underline: Boolean = true,
    style: TextStyle = MaterialTheme.typography.labelLarge,
    textAlign: TextAlign? = null,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .wrapContentSize(align = Alignment.Center)
            .clickable(onClick = onClick)
    ) {
        Text(
            modifier = Modifier,
            text = label,
            color = color,
            style = style,
            textDecoration = if (underline) {
                TextDecoration.Underline
            } else {
                null
            },
            fontWeight = FontWeight(800),
            textAlign = textAlign
        )
    }
}