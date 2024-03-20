package mx.nancrow.pokedex.presentation.composables.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import mx.nancrow.pokedex.presentation.composables.BottomNavigation

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Screen(
    navController: NavController,
    title: String? = null,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    colorStatusBar: Color = MaterialTheme.colorScheme.primary,
    darkIconsStatusBar: Boolean = false,
    paddingTop: Boolean = false,
    currentRoute: String,
    iconColors: Color = MaterialTheme.colorScheme.onPrimary,
    content: @Composable BoxScope. () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = colorStatusBar,
            darkIcons = darkIconsStatusBar
        )
    }
    Scaffold(
        topBar = {
            CustomTopBar(
                title = title,
                iconColors = iconColors
            )
        },
        bottomBar = {
            BottomNavigation(
                navController = navController,
                currentRoute = currentRoute,
            )
        },
        containerColor = containerColor
    ) {
        Box(
            modifier = Modifier
                .padding(
                    if (paddingTop) {
                        it

                    } else {
                        PaddingValues(top = 25.dp, bottom = it.calculateBottomPadding())
                    }
                )
                .fillMaxSize()
        ) {
            content()
        }
    }
}