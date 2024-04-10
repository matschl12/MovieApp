package com.example.movieappmad24.screens
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.movieappmad24.ViewModels.MoviesViewModel
import com.example.movieappmad24.appBars.BottomAppBar
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.getMovies
import com.example.movieappmad24.appBars.TopAppBar


@Composable
fun DetailScreen(movieId: String?, navController: NavHostController, moviesViewModel: MoviesViewModel) {
    val clickedMovie = getMovies().find { it.id == movieId }
    Scaffold(
        topBar = {
            if (clickedMovie != null) {
                TopAppBar(
                    title = clickedMovie.title,
                    navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
                    onNavigationIconClick = {navController.popBackStack()}
                )
            }
        },
        bottomBar = {
            BottomAppBar(navController)
        }
    )
    { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            clickedMovie?.let { movie ->
                if (movie.images.isNotEmpty()) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()

                        ) {
                        AsyncImage(
                            model = movie.images.first(),
                            contentDescription = "movie banner"
                        )
                    }
                    Details(movie = movie)

                    //The row with movie images under the moviemetails
                    LazyRow(modifier = Modifier.padding(horizontal = 8.dp)) {
                        items(movie.images.drop(1)) { imageUrl ->
                            Card(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .size(200.dp, 150.dp)
                            ) {
                                AsyncImage(
                                    model = imageUrl,
                                    contentDescription = "images",
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Details(movie: Movie) {
    var showDetails by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = movie.title)
        Icon(
            modifier = Modifier
                .clickable {
                    showDetails = !showDetails
                },
            imageVector =
            if (showDetails) Icons.Filled.KeyboardArrowDown
            else Icons.Default.KeyboardArrowUp, contentDescription = "movie details"
        )
    }

    AnimatedVisibility(
        visible = showDetails,
        enter = fadeIn(),
        exit = fadeOut()
    ) {

        if(showDetails){ //to show the movie details
            Text( //not the most beautiful way, but works for now
                text = "Director: " + movie.director
                        + "\nReleased: " +movie.year
                        + "\nGenre: " + movie.genre
                        + "\nActors: " + movie.actors
                        + "\nRating: " + movie.rating
                        + "\nPlot: " + movie.plot
            )
        }
    }
}
