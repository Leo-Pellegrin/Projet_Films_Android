package com.example.premire_application_android

import Genre
import MainViewModel
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailMovie(navController: NavController, movieId: String){
    val MovieDetailVM: MainViewModel = viewModel()
    val movie by MovieDetailVM.movie.collectAsState()

    MovieDetailVM.getMovieDetail(movieId)

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){

        Text(
            text = movie.title,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp))
        Image(
            painter = rememberImagePainter(
                data = "https://image.tmdb.org/t/p/w1280" + movie.backdrop_path,
                builder = {
                    crossfade(true)
                }),
            contentDescription = "Image film ${movie.title}",
            Modifier.padding(start = 5.dp, end = 5.dp).fillMaxWidth()
        )
        Row(modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Top){
            Image(
                painter = rememberImagePainter(
                    data = "https://image.tmdb.org/t/p/w1280" + movie.poster_path,
                    builder = {
                        crossfade(true);
                        size(400, 400)
                    }),
                contentDescription = "Image film ${movie.title}",
                Modifier.padding(start = 5.dp, end = 5.dp).fillMaxWidth()
            )
            Text(text = movie.release_date,
                color = Color.Black,
                modifier = Modifier.padding(top = 15.dp))
            Text(text = getGenres(movie.genres))
        }
    }
}

fun getGenres(genres: List<Genre>): String {
    var genresString = ""
    for (genre in genres) {
        genresString += genre.name + "& "
    }
    return genresString.dropLast(2)
}