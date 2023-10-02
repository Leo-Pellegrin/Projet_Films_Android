package com.example.premire_application_android

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun TopNavBar(navController: NavController) {
    var searchActive by remember { mutableStateOf(false) } // État pour indiquer si la recherche est active
    var searchText by remember { mutableStateOf("") } // État pour stocker le texte de recherche
    val imeAction = rememberUpdatedState(ImeAction.Done)
    val keyboardController = LocalSoftwareKeyboardController.current

    TopAppBar(
        title = {
            Text(text = if (searchActive) "" else "TMDB") // Masquer le texte quand la recherche est active
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Blue,
            titleContentColor = Color.White
        ),
        modifier = Modifier.fillMaxWidth(),
        actions = {
            if (searchActive) {
                // Bouton de fermeture de la recherche
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
                        modifier = Modifier.size(24.dp),
                        tint = Color.White
                    )
                }
                // Champ de texte de recherche quand la recherche est active
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
                    modifier = Modifier.padding(16.dp),
                    textStyle = TextStyle(color = Color.White),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Blue,
                        cursorColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                )
            } else {
                // Bouton d'icône de recherche quand la recherche n'est pas active
                IconButton(
                    onClick = {
                        searchActive = true
                    },
                    modifier = Modifier.padding(16.dp),
                    colors = IconButtonDefaults.iconButtonColors(contentColor = Color.White)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.search_icon),
                        contentDescription = "Icone de recherche",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        },
    )
}


@Composable
fun BottomNavBar(navController: NavController, filmsBool: Boolean = false, seriesBool: Boolean = false, personsBool: Boolean = false){
    val tintMovie = if (filmsBool) {
        Color.White
    } else {
        Color(0xFFb3b1b1) // Vous pouvez définir une autre couleur par défaut si nécessaire
    }

    val tintSerie = if(seriesBool){
        Color.White
    } else {
        Color(0xFFb3b1b1)
    }

    val tintPerson = if(personsBool){
        Color.White
    } else {
        Color(0xFFb3b1b1)
    }
    BottomAppBar(
        containerColor = Color.Blue,
        actions = {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly){
                IconButton(onClick = { navController.navigate("MovieScreen") }) {
                    Column(verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,) {
                        Icon(
                            painter = painterResource(id = R.drawable.movie_white_24dp),
                            contentDescription = "Localized description",
                            tint = tintMovie
                        )
                        Text(text = "Films", color = tintMovie, fontSize = 10.sp)
                    }
                }
                IconButton(onClick = { navController.navigate("SeriesScreen") }) {
                    Column(verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,) {
                        Icon(
                            painter = painterResource(id = R.drawable.desktop_windows_white_24dp),
                            contentDescription = "Localized description",
                            tint = tintSerie
                        )
                        Text(text = "Séries", color = tintSerie, fontSize = 10.sp)
                    }
                }
                IconButton(onClick = { navController.navigate("PersonsScreen") }) {
                    Column(verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally){
                        Icon(
                            painter = painterResource(id = R.drawable.person_white_24dp),
                            contentDescription = "Localized description",
                            tint = tintPerson
                        )
                        Text(text = "Acteurs", color = tintPerson, fontSize = 10.sp)
                    }
                }
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun LeftNavBar(navController: NavController, filmsBool: Boolean = false, seriesBool: Boolean = false, personsBool: Boolean = false){
    val tintMovie = if (filmsBool) {
        Color.Black
    } else {
        Color.LightGray // Vous pouvez définir une autre couleur par défaut si nécessaire
    }

    val tintSerie = if(seriesBool){
        Color.Black
    } else {
        Color.LightGray
    }

    val tintPerson = if(personsBool){
        Color.Black
    } else {
        Color.LightGray
    }
    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .width(56.dp), // Width of the vertical AppBar
        color = Color.White,
        shadowElevation = 4.dp
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly
        ){
            IconButton(onClick = { navController.navigate("MovieScreen") }) {
                Column(verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,) {
                    Icon(
                        painter = painterResource(id = R.drawable.movie_white_24dp),
                        contentDescription = "Localized description",
                        tint = tintMovie
                    )
                    Text(text = "Films", color = tintMovie, fontSize = 10.sp)
                }
            }
            IconButton(onClick = { navController.navigate("SeriesScreen") }) {
                Column(verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,) {
                    Icon(
                        painter = painterResource(id = R.drawable.desktop_windows_white_24dp),
                        contentDescription = "Localized description",
                        tint = tintSerie
                    )
                    Text(text = "Séries", color = tintSerie, fontSize = 10.sp)
                }
            }
            IconButton(onClick = { navController.navigate("PersonsScreen") }) {
                Column(verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally){
                    Icon(
                        painter = painterResource(id = R.drawable.person_white_24dp),
                        contentDescription = "Localized description",
                        tint = tintPerson
                    )
                    Text(text = "Acteurs", color = tintPerson, fontSize = 10.sp)
                }
            }
        }
    }

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
                containerColor = Color(0xFF3bd7e3)) {
                Icon(
                    painter = painterResource(id = R.drawable.search_icon),
                    contentDescription = "Icone de recherche",
                    modifier = Modifier.size(24.dp),
                    tint = Color.White
                )
            }
        }
    }
}

