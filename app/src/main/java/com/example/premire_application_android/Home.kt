package com.example.premire_application_android

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun HomeScreen(windowclass : WindowSizeClass, navController: NavController){
    when(windowclass.widthSizeClass){
        WindowWidthSizeClass.Compact -> {
            Column(
                Modifier.fillMaxSize().clickable { navController.navigate("spacer") },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PersonalPicture();
                TitreNom();
                TitreDescription();
                DescriptionEcole();
                EspaceBlanc();
                TexteMail();
                TexteLinkedin();
                EspaceBlanc();
                EspaceBlanc();
                BoutonDemarrer(navController)
            }
        }
        else -> {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    PersonalPicture();
                    TitreNom();
                    TitreDescription();
                    DescriptionEcole();
                }
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    TexteMail();
                    TexteLinkedin();
                    EspaceBlanc();
                    BoutonDemarrer(navController)
                }
            }
        }
    }
}

@Composable
fun PersonalPicture(){
        Image(
            painterResource(id = R.drawable.photo),
            modifier = Modifier.clip(CircleShape),
            contentDescription = "PersonalPicture",
        )
}

@Composable
fun TitreNom(){
        Text(
            text = "Léo Pellegrin",
            fontSize = 30.sp,
            modifier = Modifier.padding(10.dp)
        )
}

@Composable
fun TitreDescription(){
    Text(
        text = "Etudiant en informatique et santé",
        fontSize = 10.sp
    )
}

@Composable
fun DescriptionEcole(){
    Text(
        text = "École d'ingénieur ISIS",
        fontSize = 10.sp,
        fontStyle = FontStyle.Italic
    )
}

@Composable
fun EspaceBlanc(){
    Spacer(Modifier.height(50.dp))
}

@Composable
fun TexteMail(){
    Row(){
        Image(
            painterResource(id = R.drawable.maillogo),
            contentDescription = "Mail Logo",
            modifier = Modifier.size(30.dp)
        )
        Text(
            text = "leo.pellegrin87@gmail.com",
            modifier = Modifier.padding(5.dp)
        )
    }
}

@Composable
fun TexteLinkedin(){
    Row(){
        Image(
            painterResource(id = R.drawable.linkedin_logo),
            contentDescription = "Linkedin logo",
            modifier = Modifier.size(30.dp)
        )
        Text(
            text = "linkedin.com/in/pellegrin-leo",
            modifier = Modifier.padding(5.dp)
        )
    }
}



@Composable
fun BoutonDemarrer(navController: NavController){

    Button(onClick = { navController.navigate("MovieScreen")}) {
        Text(text = "Démarrer")
    }
}

