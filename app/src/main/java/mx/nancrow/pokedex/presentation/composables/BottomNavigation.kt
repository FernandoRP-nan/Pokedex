package mx.nancrow.pokedex.presentation.composables

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import mx.nancrow.pokedex.R
import mx.nancrow.pokedex.presentation.navigation.Screens

data class NavigationItem(
    val title: String,
    val icon: Int,
    val route: String? = null
)

private val items = listOf(
    NavigationItem(
        title = "Pokemon",
        icon = R.drawable.home,
        route = Screens.HOME
    ),
    NavigationItem(
        title = "Favoritos",
        icon = R.drawable.favorite,
        route = Screens.ACT_2
    ),
    NavigationItem(
        title = "Perfil",
        icon = R.drawable.settings,
        route = Screens.ACT_3
    )
)

@Composable
fun BottomNavigation(
    currentRoute: String,
    navController: NavController
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.background,
    ) {
        items.forEachIndexed { index, navigationItem ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painterResource(navigationItem.icon),
                        contentDescription = navigationItem.title,
                        modifier = Modifier.size(30.dp)
                    )
                },
                //label = { Text(navigationItem.title, fontSize = 6.sp) },
                selected = currentRoute == navigationItem.route,
                onClick = {
                    navigationItem.route?.let {
                        if (currentRoute != it)
                            navController.navigate(it)
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedIconColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedTextColor = MaterialTheme.colorScheme.primary,
                    indicatorColor = MaterialTheme.colorScheme.onPrimary,
                )
            )
        }
    }
}