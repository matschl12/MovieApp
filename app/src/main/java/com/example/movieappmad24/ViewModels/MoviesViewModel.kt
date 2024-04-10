package com.example.movieappmad24.ViewModels

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.getMovies

class MoviesViewModel: ViewModel() {
    private val _movieList = getMovies().toMutableStateList()
    val movieList: List<Movie>
        get() = _movieList

    val favoriteMovies: List<Movie>
        get() = _movieList.filter { movie -> movie.isFavorite }

    fun toggleFavoriteMovie(movieID: String) = _movieList.find { it.id == movieID }?.let { movie ->
        movie.isFavorite= !movie.isFavorite
    }
}