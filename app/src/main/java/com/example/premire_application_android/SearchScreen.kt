package com.example.premire_application_android

import MainViewModel
import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavController, search: String, windowclass: WindowSizeClass) {
    when(windowclass.widthSizeClass){
        WindowWidthSizeClass.Compact -> {
            Scaffold(
                topBar = {
                    TopNavBar(navController)
                }
            ){
                val modifier = Modifier.padding(top = 60.dp)
                SearchComposable(navController, search, modifier)
            }
        }
        else -> {
            val modifier = Modifier.padding(top = 15.dp)
            SearchComposable(navController, search, modifier)

            var searchActive  by remember { mutableStateOf(false) } // État pour indiquer si la recherche est active;
            var searchText by remember { mutableStateOf("") } // État pour stocker le texte de recherche
            val imeAction = rememberUpdatedState(ImeAction.Done)
            val keyboardController = LocalSoftwareKeyboardController.current
            if(searchActive) {
                Column(
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier.fillMaxWidth().padding(15.dp)){
                    Card(
                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        border = BorderStroke(2.dp, Color.DarkGray),
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            IconButton(
                                onClick = {
                                    searchActive = false
                                    searchText = "" // Effacer le texte de recherche
                                },
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.cancel_white_24dp), // Icône de fermeture
                                    contentDescription = "Fermer la recherche",
                                    modifier = Modifier.size(35.dp).padding(top = 7.dp),
                                    tint = Color.Black
                                )
                            }
                            TextField(
                                value = searchText,
                                onValueChange = { newText ->
                                    searchText = newText
                                },
                                keyboardOptions = KeyboardOptions(
                                    imeAction = imeAction.value
                                ),
                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        // Action de validation (Done) gérée ici
                                        keyboardController?.hide()
                                        navController.navigate("SearchScreen/${searchText}")
                                    }
                                ),
                                modifier = Modifier.padding(16.dp).weight(1f),
                                textStyle = TextStyle(color = Color.Black, fontSize = 20.sp),
                                colors = TextFieldDefaults.colors(
                                    focusedContainerColor = Color.White,
                                    unfocusedContainerColor = Color.White,
                                    disabledContainerColor = Color.White,
                                    cursorColor = Color.White,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                ),
                            )
                            IconButton(
                                enabled = searchText != "",
                                onClick = {
                                    searchActive = false
                                    navController.navigate("SearchScreen/${searchText}")
                                },
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.search_icon),
                                    contentDescription = "Icone de recherche",
                                    modifier = Modifier.size(35.dp).padding(top = 7.dp),
                                    tint = Color.Black
                                )
                            }
                        }
                    }
                }
            }
            else{
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.BottomEnd
                ){
                    FloatingActionButton(onClick = { searchActive = !searchActive },
                        shape = CircleShape,
                        containerColor = Color(0xFF00DAC5)) {
                        Icon(
                            painter = painterResource(id = R.drawable.search_icon),
                            contentDescription = "Icone de recherche",
                            modifier = Modifier.size(24.dp),
                            tint = Color.Black
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchComposable(navController: NavController, search: String, modifier: Modifier) {
    // ViewModel
    val SearchMV: MainViewModel = viewModel()
    // Déclaration des variables pour les chips
    var selectedAll by remember { mutableStateOf(true) }
    var selectedMovie by remember { mutableStateOf(false) }
    var selectedSerie by remember { mutableStateOf(false) }
    var selectedPerson by remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text(
            text = "Résultats pour : $search",
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            modifier = Modifier
                .padding(top = 10.dp, bottom = 10.dp)
                .align(CenterHorizontally)
        )
        // FilterChip avec : Tout, Films, Série, Personnes pour trier la recherche
        LazyRow(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.padding(start = 10.dp)
        ) {
            item {
                FilterChip(
                    modifier = Modifier.padding(end = 10.dp),
                    onClick = {
                        selectedAll = !selectedAll
                        if (selectedAll) {
                            selectedMovie = false
                            selectedSerie = false
                            selectedPerson = false
                        }
                    },
                    label = {
                        Text("Tout")
                    },
                    selected = selectedAll,
                    leadingIcon = if (selectedAll) {
                        {
                            Icon(
                                imageVector = Icons.Filled.Done,
                                contentDescription = "Done icon",
                                modifier = Modifier.size(FilterChipDefaults.IconSize)
                            )
                        }
                    } else {
                        null
                    },
                )
            }
            item {
                FilterChip(
                    modifier = Modifier.padding(end = 10.dp),
                    onClick = { selectedMovie = !selectedMovie
                              if(selectedMovie)selectedAll = false},
                    label = {
                        Text("Films")
                    },
                    selected = selectedMovie,
                    leadingIcon = if (selectedMovie) {
                        {
                            Icon(
                                imageVector = Icons.Filled.Done,
                                contentDescription = "Done icon",
                                modifier = Modifier.size(FilterChipDefaults.IconSize)
                            )
                        }
                    } else {
                        null
                    },
                )
            }
            item {
                FilterChip(
                    modifier = Modifier.padding(end = 10.dp),
                    onClick = { selectedSerie = !selectedSerie
                              if(selectedSerie)selectedAll = false},
                    label = {
                        Text("Series")
                    },
                    selected = selectedSerie,
                    leadingIcon = if (selectedSerie) {
                        {
                            Icon(
                                imageVector = Icons.Filled.Done,
                                contentDescription = "Done icon",
                                modifier = Modifier.size(FilterChipDefaults.IconSize)
                            )
                        }
                    } else {
                        null
                    },
                )
            }
            item {
                FilterChip(
                    modifier = Modifier.padding(end = 10.dp),
                    onClick = { selectedPerson = !selectedPerson
                              if(selectedPerson)selectedAll = false},
                    label = {
                        Text("Personnes")
                    },
                    selected = selectedPerson,
                    leadingIcon = if (selectedPerson) {
                        {
                            Icon(
                                imageVector = Icons.Filled.Done,
                                contentDescription = "Done icon",
                                modifier = Modifier.size(FilterChipDefaults.IconSize)
                            )
                        }
                    } else {
                        null
                    },
                )
            }
        }
        val SearchMV: MainViewModel = viewModel()
        val movies by SearchMV.movies.collectAsState()
        val series by SearchMV.series.collectAsState()
        val persons by SearchMV.persons.collectAsState()

        LazyColumn(
            modifier = Modifier.padding(top = 12.dp, bottom = 20.dp),
            content = {
                item {
                    if (selectedMovie || selectedAll) {
                        SearchMV.getMovieSearch(search)
                        Text(
                            text = "Films",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            modifier = Modifier
                                .padding(top = 10.dp, bottom = 10.dp, start = 10.dp)
                        )
                        if (movies.results.isNotEmpty()) {
                            LazyRow() {
                                items(movies.results.take(10)) { movie ->
                                    FloatingActionButton(
                                        onClick = { navController.navigate("DetailMovie/${movie.id}") },
                                        modifier = Modifier.padding(20.dp),
                                        containerColor = Color.White
                                    ) {
                                        Column(
                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = CenterHorizontally,
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
                item {
                    if (selectedSerie || selectedAll) {
                        SearchMV.getSerieSearch(search)
                        Text(
                            text = "Séries",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            modifier = Modifier
                                .padding(top = 10.dp, bottom = 10.dp, start = 10.dp)
                        )
                        if (series.results.isNotEmpty()) {
                            LazyRow() {
                                items(series.results.take(10)) { serie ->
                                    FloatingActionButton(
                                        onClick = { if(serie.id > 0.toString())navController.navigate("DetailSerie/${serie.id}") },
                                        modifier = Modifier.padding(20.dp),
                                        containerColor = Color.White
                                    ) {
                                        Column(
                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = CenterHorizontally,
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
                item{
                    if (selectedPerson || selectedAll) {
                        SearchMV.getPersonSearch(search)
                        Text(
                            text = "Personnes",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            modifier = Modifier
                                .padding(top = 10.dp, bottom = 10.dp, start = 10.dp)
                        )
                        if (persons.results.isNotEmpty()) {
                            LazyRow() {
                                items(persons.results.take(10)) { person ->
                                    FloatingActionButton(
                                        onClick = { navController.navigate("DetailPerson/${person.id}") },
                                        modifier = Modifier.padding(20.dp),
                                        containerColor = Color.White
                                    ) {
                                        Column(
                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = CenterHorizontally,
                                            modifier = Modifier.fillMaxSize()
                                        ) {
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
                                            Text(
                                                text = person.name,
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
            }
        )
    }
}