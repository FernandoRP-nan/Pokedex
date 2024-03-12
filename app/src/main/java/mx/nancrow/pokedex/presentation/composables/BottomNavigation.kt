package mx.nancrow.pokedex.presentation.composables

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import mx.nancrow.pokedex.R
import mx.pokedex.presentation.navigation.Screens
import mx.pokedex.presentation.theme.Primary

data class NavigationItem(
    val title: String,
    val icon: Int,
    val route: String? = null
)

private val items = listOf(
    NavigationItem(
        title = "Actividad actual",
        icon = R.drawable.home,
        route = Screens.ACT_1
    ),
    NavigationItem(
        title = "Todas",
        icon = R.drawable.menu,
        route = Screens.ACT_1
    ),
    NavigationItem(
        title = "Perfil",
        icon = R.drawable.person,
        route = Screens.ACT_1
    )
)

@Composable
fun BottomNavigation(
    currentRoute: String,
    navController: NavController
) {
    NavigationBar(
        containerColor = Color.White,
        contentColor = Primary,
    ) {
        items.forEachIndexed { index, navigationItem ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painterResource(navigationItem.icon),
                        contentDescription = navigationItem.title,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = { Text(navigationItem.title, fontSize = 6.sp) },
                selected = currentRoute == navigationItem.route,
                onClick = {
                    navigationItem.route?.let {
                        if (currentRoute != it)
                            navController.navigate(it)
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.secondary,
                    selectedTextColor = MaterialTheme.colorScheme.secondary,
                    unselectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedTextColor = MaterialTheme.colorScheme.primary,
                    indicatorColor = MaterialTheme.colorScheme.secondary
                )
            )
        }
    }
}