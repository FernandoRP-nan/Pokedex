package mx.nancrow.pokedex.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import mx.nancrow.pokedex.R

val fontNormal = FontFamily(
    Font(
        R.font.pokemon_gb_raeo)
)

// Set of Material typography styles to start with
val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = fontNormal,
        fontWeight = FontWeight.Normal,
        fontSize = 55.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = fontNormal,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ) ,

    bodyMedium = TextStyle(
        fontFamily = fontNormal,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.5.sp
    ),
    bodySmall = TextStyle(
        fontFamily = fontNormal,
        fontWeight = FontWeight.Normal,
        fontSize = 19.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    labelLarge = TextStyle(
        fontFamily = fontNormal,
        fontWeight = FontWeight(300),
        fontSize = 15.sp,
        lineHeight = 12.sp,
        letterSpacing = 0.5.sp
    ),
    labelMedium = TextStyle(
        fontFamily = fontNormal,
        fontWeight = FontWeight(700),
        fontSize = 13.sp,
        lineHeight = 11.sp,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontFamily = fontNormal,
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp,
        lineHeight = 8.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)