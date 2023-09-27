package com.example.premire_application_android

import MainViewModel
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import java.text.SimpleDateFormat
import java.util.Locale

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieScreen(navController: NavController){
    val mainViewModel: MainViewModel = viewModel()
    Scaffold(
        topBar = {
            TopNavBar(navController)
        },
        bottomBar = {
            BottomNavBar(navController)
        }
    ){
        ListFilms(mainViewModel, navController)
    }
}

@OptIn(ExperimentalCoilApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ListFilms(filmVM: MainViewModel, navController: NavController) {
    val movies by filmVM.movies.collectAsState()

    if (movies.results.isEmpty()) {
        filmVM.getFilmInitiaux()
    }
    if (movies.results.isNotEmpty()) {
        LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.padding(top = 60.dp, bottom = 60.dp)) {
            items(movies.results) { movie ->
                FloatingActionButton(
                    onClick = {navController.navigate("DetailMovie/${movie.id}")},
                    modifier = Modifier.padding(20.dp),
                    containerColor = Color.White,
                    ) {
                    Column( verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()) {
                        Column( verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxSize()) {
                            Image(
                                painter = rememberImagePainter(
                                    data = "https://image.tmdb.org/t/p/w780" + movie.poster_path,
                                    builder = {
                                        crossfade(true)
                                        size(
                                            350,
                                            400
                                        )
                                    }),
                                contentDescription = "Image film ${movie.title}",
                                Modifier.padding(start = 5.dp, end = 5.dp)
                            )
                        }
                        Column(horizontalAlignment = Alignment.Start, modifier = Modifier.padding(start = 10.dp)){
                            Text(text = movie.title,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                modifier = Modifier.padding(top = 5.dp))
                            Text(text = formatDate(movie.release_date, "yyyy-MM-dd", "dd MMM yyyy", Locale.FRANCE),
                                color = Color.Black,
                                modifier = Modifier.padding(top = 15.dp))
                        }
                    }
                }
            }
        }
    }
}
fun formatDate(inputDate: String, inputDateFormat: String, outputDateFormat: String, locale: Locale): String {
    val inputFormat = SimpleDateFormat(inputDateFormat, locale)
    val outputFormat = SimpleDateFormat(outputDateFormat, locale)
    Log.d("date", inputDate)
    Log.d("date", inputFormat.toString())
    val date = inputFormat.parse(inputDate)
    return outputFormat.format(date)
}

