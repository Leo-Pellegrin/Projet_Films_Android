package com.example.premire_application_android

import MainViewModel
import android.annotation.SuppressLint
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
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeriesScreen(navController: NavController, windowclass : WindowSizeClass){
    val mainViewModel: MainViewModel = viewModel()
    when(windowclass.widthSizeClass){
        WindowWidthSizeClass.Compact -> {
            Scaffold(
                topBar = {
                    TopNavBar(navController)
                },
                bottomBar = {
                    BottomNavBar(navController, seriesBool = true)
                }
            ){
                val modifier = Modifier.padding(top = 60.dp, bottom = 60.dp)
                ListSeries(mainViewModel, navController, nbColumns = 2, modifier = modifier)
            }
        }
        else -> {
            Scaffold(
                bottomBar = {
                    LeftNavBar(navController, seriesBool = true)
                }
            ){
                val modifier = Modifier.padding(start = 45.dp)
                ListSeries(mainViewModel, navController, nbColumns = 3, modifier = modifier)
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ListSeries(SeriesVM: MainViewModel, navController: NavController, nbColumns: Int = 2, modifier: Modifier) {
    val series by SeriesVM.series.collectAsState()

    if (series.results.isEmpty()) {
        SeriesVM.getSeriesInitiaux()
    }
    if (series.results.isNotEmpty()) {
        LazyVerticalGrid(columns = GridCells.Fixed(nbColumns), modifier = modifier) {
            items(series.results) { serie ->
                FloatingActionButton(onClick = { navController.navigate("DetailSerie/${serie.id}")}, modifier = Modifier.padding(20.dp), containerColor = Color.White) {
                    Column( verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()){
                        Image(
                            painter = rememberImagePainter(
                                data = "https://image.tmdb.org/t/p/w780" + serie.poster_path,
                                builder = {
                                    crossfade(true)
                                    size(
                                        250,
                                        200
                                    )
                                }),
                            contentDescription = "Image Series ${serie.name}"
                        )
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxSize()
                        ){
                            Text(
                                text = serie.name,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(top = 5.dp, start = 10.dp))
                        }
                    }
                }
            }
        }
    }
}


