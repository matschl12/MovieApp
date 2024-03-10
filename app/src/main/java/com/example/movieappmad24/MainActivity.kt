package com.example.movieappmad24

import android.os.Bundle
import androidx.compose.ui.Alignment
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.movieappmad24.models.Movie
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.*
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.movieappmad24.models.getMovies
import com.example.movieappmad24.ui.theme.MovieAppMAD24Theme
import org.w3c.dom.Text


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppMAD24Theme {
                TopAppBar()
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TopAppBar() {
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
                BottomAppBar()
            }
        ){
                innerPadding -> Modifier.padding(innerPadding)
            MovieList(movies = getMovies())

        }
    }
    @Composable
    fun BottomAppBar() {

        NavigationBar {
            NavigationBarItem(selected = false, onClick = { /*TODO*/ }, icon = {Icon(Icons.Filled.Home, contentDescription = "home")} )
            NavigationBarItem(selected = false, onClick = { /*TODO*/ }, icon = {Icon(Icons.Filled.Star, contentDescription = "watchlist")})
        }

    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MovieRow(movie: Movie){
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
                    .animateContentSize(),


            ){
                Box {//box with the movie banner
                    AsyncImage(model = movie.images.first(), //takes the first url from the available pictures for each movie
                        contentDescription = "Movie Banner")
                    Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = "Like" , Modifier.align(Alignment.TopEnd) )
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
    fun MovieList(movies: List<Movie> = getMovies()){
        LazyColumn(){
            items(movies) { movie ->
                MovieRow(movie)
            }
        }
    }


    @Composable
    @Preview
    fun ExpandableCardPreview() //for preview without having to load the emulator
    {
       TopAppBar()
    }

}




