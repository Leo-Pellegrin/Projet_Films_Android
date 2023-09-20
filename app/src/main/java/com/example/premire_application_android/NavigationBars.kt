package com.example.premire_application_android

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

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
fun BottomNavBar(navController: NavController){
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
                            tint = Color.White
                        )
                        Text(text = "Films", color = Color.White, fontSize = 10.sp)
                    }
                }
                IconButton(onClick = { navController.navigate("SeriesScreen") }) {
                    Column(verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,) {
                        Icon(
                            painter = painterResource(id = R.drawable.desktop_windows_white_24dp),
                            contentDescription = "Localized description",
                            tint = Color.White
                        )
                        Text(text = "Séries", color = Color.White, fontSize = 10.sp)
                    }
                }
                IconButton(onClick = { navController.navigate("PersonsScreen") }) {
                    Column(verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally){
                        Icon(
                            painter = painterResource(id = R.drawable.person_white_24dp),
                            contentDescription = "Localized description",
                            tint = Color.White
                        )
                        Text(text = "Acteurs", color = Color.White, fontSize = 10.sp)
                    }
                }
            }
        },
    )
}
