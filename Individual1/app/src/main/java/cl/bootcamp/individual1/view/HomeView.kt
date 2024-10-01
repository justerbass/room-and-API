package cl.bootcamp.individual1.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import cl.bootcamp.individual1.components.Separation

@Composable
fun HomeView(navController: NavController){
    Scaffold {paddingValues ->
        Column (modifier = Modifier.padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            addProfile(navController = navController)
            Separation()
        }

    }
}

@Composable
fun addProfile(navController: NavController){
    Button(onClick = {
        navController.navigate("addProfile")
    }) {
        Text(text = "Agregar contacto")
    }
}