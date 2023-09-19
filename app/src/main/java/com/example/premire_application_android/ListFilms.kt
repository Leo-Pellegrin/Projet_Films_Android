package com.example.premire_application_android

import FilmViewModel
import ImageViewModel
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieScreen(){
    val filmViewModel: FilmViewModel = viewModel()
    val imageViewModel : ImageViewModel = viewModel()
    Scaffold(
        topBar = {
            TopNavBar()
        },
        bottomBar = {
            BottomNavBar()
        }
    ){
        listFilms(filmViewModel, imageViewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavBar(){
    TopAppBar(
        title = {
            Text(text = "Films")
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Blue,
            titleContentColor = Color.White),
        modifier = Modifier.fillMaxWidth(),
        actions = {
            Button(onClick = { /* do something */ },
                    modifier = Modifier.padding(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Blue
                    )) {
                Icon(
                    painter = painterResource(id = R.drawable.search_icon),
                    contentDescription = "Icone de recherche",
                    modifier = Modifier.size(24.dp)
                )
            }
        },
    )
}

@Composable
fun BottomNavBar(){
    BottomAppBar(
        containerColor = Color.Blue,
        actions = {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly){
                IconButton(onClick = { /* do something */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.movie_white_24dp),
                        contentDescription = "Localized description",
                        tint = Color.White
                    )
                }
                IconButton(onClick = { /* do something */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.desktop_windows_white_24dp),
                        contentDescription = "Localized description",
                        tint = Color.White
                    )
                }
                IconButton(onClick = { /* do something */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.person_white_24dp),
                        contentDescription = "Localized description",
                        tint = Color.White
                    )
                }
            }

        },
    )
}


@Composable
fun listFilms(filmVM: FilmViewModel, imageVM: ImageViewModel){
    val movies by filmVM.movies.collectAsState()
    val image by imageVM.image.collectAsState()

    if(movies.results.isEmpty()){
        filmVM.getFilmInitiaux()
        Log.d("Film", "Film récupérés")
        for(movie in movies.results){
            imageVM.getImage(movie.backdrop_path)
        }
    }
    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.padding(80.dp)){
        items(movies.results){ movie ->
            ElevatedButton(onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(id = image),
                    contentDescription = "Image film ${movie.title}"
                )
                Text(text = movie.title)
            }
        }
    }
}


