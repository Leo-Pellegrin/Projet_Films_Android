package com.example.premire_application_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.premire_application_android.ui.theme.Première_Application_AndroidTheme

class MainActivity : ComponentActivity() {
    @ExperimentalMaterial3WindowSizeClassApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Première_Application_AndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val WindowSizeClass = calculateWindowSizeClass(this)
                    Screen(WindowSizeClass)
                }
            }
        }
    }
}

@Composable
fun Screen(windowclass : WindowSizeClass){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "HomeScreen") {
        composable("HomeScreen") {
            HomeScreen(windowclass, navController)
        }
        composable("MovieScreen"){
            MovieScreen(navController)
        }
        composable("SeriesScreen"){
            SeriesScreen(navController)
        }
        composable("PersonsScreen"){
            PersonsScreen(navController)
        }
        composable("DetailMovie/{movieid}"){ backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieid") ?: ""
            DetailMovie(navController, movieId)
        }
        composable("DetailSerie/{serieid}"){ backStackEntry ->
            val serieId = backStackEntry.arguments?.getString("serieid") ?: ""
            DetailSerie(navController, serieId)
        }
        composable("DetailPerson/{personid}"){ backStackEntry ->
            val personId = backStackEntry.arguments?.getString("personid") ?: ""
            DetailPerson(navController, personId)
        }
        composable("SearchScreen/{search}"){ backStackEntry ->
            val search = backStackEntry.arguments?.getString("search") ?: ""
            SearchScreen(navController, search)
        }
    }
}
