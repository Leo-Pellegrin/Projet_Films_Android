package com.example.premire_application_android

import MainViewModel
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import java.util.Locale
import java.util.regex.Pattern

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalCoilApi::class)
@Composable
fun DetailPerson(personId: String, navController: NavController) {
    val PersonDetailMV: MainViewModel = viewModel()
    val person by PersonDetailMV.person.collectAsState()
    val movies by PersonDetailMV.personmovie.collectAsState()
    val series by PersonDetailMV.personserie.collectAsState()

    if (person.name == "") {
        PersonDetailMV.getPersonDetail(personId)
    }
    if (person.name != "") {
        //Column Globale
        Column(){
            LazyColumn(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    item {
                        // Nom de la personne
                        Column(
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                text = person.name,
                                fontWeight = FontWeight.Bold,
                                fontSize = 30.sp,
                                modifier = Modifier
                                    .padding(top = 10.dp, bottom = 10.dp)
                                    .align(Alignment.CenterHorizontally)
                            )
                        }
                    }
                    // Image
                    item {
                        Image(
                            painter = rememberImagePainter(
                                data = "https://image.tmdb.org/t/p/h632" + person.profile_path,
                                builder = {
                                    crossfade(true)
                                    size(500, 500)
                                }),
                            contentDescription = "Image film ${person.name}",
                            Modifier
                                .padding(start = 15.dp, end = 15.dp)
                        )
                    }
                    item {
                        // Affiche + Date de sortie + Genre
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            if (person.gender == 1) {
                                Text(
                                    text = "Sexe : Femme",
                                    modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                                )
                            } else {
                                Text(
                                    text = "Sexe : Homme",
                                    modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                                )
                            }
                            if(person.known_for_department != ""){
                                Text(
                                    text = "Métier : " + person.known_for_department,
                                    modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                                )
                            }
                            var checkBirthday = false
                            if(person.birthday != null){
                                checkBirthday = Pattern.matches("^\\d{4}-\\d{2}-\\d{2}\$\n", person.birthday)
                            }
                            if(person.place_of_birth != "" && checkBirthday){
                                Text(
                                    text = "Lieu de naissance : " + person.place_of_birth,
                                    fontStyle = FontStyle.Italic,
                                    modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                                )
                                Text(
                                    text = "Anniversaire : " + formatDate(
                                        person.birthday,
                                        "yyyy-dd-MM",
                                        "dd MMM yyyy",
                                        Locale.FRANCE
                                    ),
                                    modifier = Modifier.padding(top = 15.dp),
                                    fontSize = 15.sp
                                )
                            }
                        }
                    }
                    // Synopsis
                    item{
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(start = 10.dp)
                        ) {
                            if(person.biography != ""){
                                Text(
                                    text = "Biographie",
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 25.sp,
                                    modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                                )
                                Text(
                                    text = person.biography,
                                    modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                                )
                            }
                        }
                    }
                    if(series.cast.isNotEmpty() || movies.cast.isNotEmpty()){
                        item {
                            Text(
                                text = "Apparait dans :",
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontSize = 25.sp,
                                modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                            )
                        }
                    }
                    item{
                        PersonDetailMV.getPersonDetailMovies(personId)
                        if(movies.cast.isNotEmpty()){
                            Column(){
                                Text( text = "Films :",
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 25.sp,
                                    modifier = Modifier.padding(top = 15.dp, end = 15.dp))
                                LazyRow() {
                                    items(movies.cast.take(10)) { movie ->
                                        FloatingActionButton(
                                            onClick = { navController.navigate("DetailMovie/${movie.id}") },
                                            modifier = Modifier.padding(20.dp),
                                            containerColor = Color.White
                                        ) {
                                            Column(
                                                verticalArrangement = Arrangement.Center,
                                                horizontalAlignment = Alignment.CenterHorizontally,
                                                modifier = Modifier.fillMaxSize()
                                            ) {
                                                Image(
                                                    painter = rememberImagePainter(
                                                        data = "https://image.tmdb.org/t/p/w780" + movie.poster_path,
                                                        builder = {
                                                            crossfade(true)
                                                            size(
                                                                250,
                                                                200
                                                            )
                                                        }),
                                                    contentDescription = "Image film ${movie.title}"
                                                )
                                                Text(
                                                    text = movie.title,
                                                    fontWeight = FontWeight.Bold,
                                                    modifier = Modifier.padding(5.dp)
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    item{
                        PersonDetailMV.getPersonDetailSeries(personId)
                        if(series.cast.isNotEmpty()){
                            Column() {
                                Text( text = "Séries",
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 25.sp,
                                    modifier = Modifier.padding(top = 15.dp, end = 15.dp))
                                LazyRow() {
                                    items(series.cast.take(10)) { serie ->
                                        FloatingActionButton(
                                            onClick = { navController.navigate("DetailSerie/${serie.id}") },
                                            modifier = Modifier.padding(20.dp),
                                            containerColor = Color.White
                                        ) {
                                            Column(
                                                verticalArrangement = Arrangement.Center,
                                                horizontalAlignment = Alignment.CenterHorizontally,
                                                modifier = Modifier.fillMaxSize()
                                            ) {
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
                                                    contentDescription = "Image film ${serie.name}"
                                                )
                                                Text(
                                                    text = serie.name,
                                                    fontWeight = FontWeight.Bold,
                                                    modifier = Modifier.padding(5.dp)
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                })
        }
    }
}
