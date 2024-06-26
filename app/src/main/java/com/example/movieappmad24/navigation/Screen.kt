package com.example.movieappmad24.navigation

sealed class Screen(val route: String) {
    object HomeScreen : Screen("homescreen")
    object DetailScreen : Screen("detailscreen/{movieId}")
    {
        fun createRoute(movieId: String) = "detailscreen/$movieId"
        //test for branch
    }
    object WatchlistScreen : Screen("watchlistscreen")
}
