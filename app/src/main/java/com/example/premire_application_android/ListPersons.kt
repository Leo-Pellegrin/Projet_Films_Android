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
fun PersonsScreen(navController: NavController){
    val mainViewModel: MainViewModel = viewModel()
    Scaffold(
        topBar = {
            TopNavBar(navController)
        },
        bottomBar = {
            BottomNavBar(navController)
        }
    ){
        ListPersons(mainViewModel, navController)
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ListPersons(PersonsVM: MainViewModel, navController: NavController) {
    val persons by PersonsVM.persons.collectAsState()

    if (persons.results.isEmpty()) {
        PersonsVM.getPersonsInitiaux()
    }
    if (persons.results.isNotEmpty()) {
        LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.padding(top = 60.dp, bottom = 60.dp)) {
            items(persons.results) { person ->
                FloatingActionButton(onClick = {navController.navigate("DetailPerson/${person.id}") }, modifier = Modifier.padding(20.dp), containerColor = Color.White) {
                    Column( verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()){
                        Image(
                            painter = rememberImagePainter(
                                data = "https://image.tmdb.org/t/p/w780" + person.profile_path,
                                builder = {
                                    crossfade(true)
                                    size(
                                        250,
                                        200
                                    )
                                }),
                            contentDescription = "Image film ${person.name}"
                        )
                        Text(text = person.name,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 5.dp))
                    }
                }
            }
        }
    }
}


