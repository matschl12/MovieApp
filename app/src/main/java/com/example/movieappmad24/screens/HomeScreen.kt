package com.example.movieappmad24.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.movieappmad24.appBars.BottomAppBar
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.getMovies
import com.example.movieappmad24.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("MAD Movie App")
                }
            )
        },
        bottomBar = {
            BottomAppBar(navController)
        }
    ){
            innerPadding -> Modifier.padding(innerPadding)
        MovieList(movies = getMovies(),  navController = navController)


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieRow(movie: Movie, onItemClick: (String) -> Unit = {}){
    var showDetails by remember{
        mutableStateOf(false)
    }

    val rotationState by animateFloatAsState( //to rotate the arrow icon
        targetValue = if(showDetails) 180f else 0f
    )

    Column {
        Card (
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize()
                .clickable {
                    onItemClick(movie.id)
                },


            ){
            Box {//box with the movie banner
                AsyncImage(model = movie.images.first(), //takes the first url from the available pictures for each movie
                    contentDescription = "Movie Banner")
                Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = "Like" , Modifier.align(
                    Alignment.TopEnd) )
            }
        }
        //column with movie title and icon button (arrow) and if arrow is pressed with movie description
        Column(verticalArrangement = Arrangement.SpaceBetween, horizontalAlignment = Alignment.End) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = movie.title
                )
                IconButton(
                    modifier = Modifier
                        .clickable {
                            showDetails = !showDetails
                        }
                        .rotate(rotationState),

                    onClick = {
                        showDetails = !showDetails
                    }) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Drop-Down Arrow"
                    )
                }
            }
            //when the arrow button is pressed, show the movie description
            if(showDetails){
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
}

@Composable
fun MovieList(movies: List<Movie> = getMovies(), navController: NavController){
    LazyColumn(){
        items(movies) { movie ->
            MovieRow(movie = movie) {movieId ->
                navController.navigate(Screen.DetailScreen.createRoute(movieId))
            }
        }
    }
}