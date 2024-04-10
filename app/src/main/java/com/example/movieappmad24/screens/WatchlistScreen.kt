package com.example.movieappmad24.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.movieappmad24.ViewModels.MoviesViewModel
import com.example.movieappmad24.appBars.BottomAppBar
import com.example.movieappmad24.models.getMovies

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WatchlistScreen(navController: NavController, moviesViewModel: MoviesViewModel)
{
    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("MAD Watchlist")
                }
            )
        },
        bottomBar = {
            BottomAppBar(navController)
        }
    ){
            innerPadding -> Modifier.padding(innerPadding)
        MovieList(movies = getMovies().subList(1,2),  navController = navController) //hardcoded Watchlist with only one movie


    }
}
