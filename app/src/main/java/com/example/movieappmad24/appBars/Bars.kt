package com.example.movieappmad24.appBars

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import com.example.movieappmad24.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar( //comes into a seperate package later
    title: String,
    navigationIcon: ImageVector? = null,
    onNavigationIconClick: (() -> Unit)? = null
) {
    CenterAlignedTopAppBar(
        title = { Text(title) },
        navigationIcon = if (navigationIcon != null && onNavigationIconClick != null) {
            {
                IconButton(onClick = onNavigationIconClick) {
                    Icon(
                        imageVector = navigationIcon,
                        contentDescription = "Navigation Icon"
                    )
                }
            }
        } else {
            {}
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        )
    )
}

@Composable
fun BottomAppBar(navController: NavController) {

    NavigationBar {
        NavigationBarItem(selected = false, onClick = { navController.navigate(Screen.HomeScreen.route) }, icon = { Icon(
            Icons.Filled.Home, contentDescription = "home") } )
        NavigationBarItem(selected = false, onClick = { navController.navigate(Screen.WatchlistScreen.route) }, icon = { Icon(
            Icons.Filled.Star, contentDescription = "watchlist") })
    }

}
