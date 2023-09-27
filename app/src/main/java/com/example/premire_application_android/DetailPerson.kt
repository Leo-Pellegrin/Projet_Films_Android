package com.example.premire_application_android

import MainViewModel
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalCoilApi::class)
@Composable
fun DetailPerson(navController: NavController, movieId: String) {
    val PersonDetailMV: MainViewModel = viewModel()
    val person by PersonDetailMV.person.collectAsState()

    if (person.name == "") {
        PersonDetailMV.getPersonDetail(movieId)
    }
    if (person.name != "") {
        //Column Globale
        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.verticalScroll(rememberScrollState())) {
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
                        .align(CenterHorizontally)
                )
            }

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
                if(person.place_of_birth != "" && person.birthday != ""){
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
        // Synopsis
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
    }
}
