package cl.bootcamp.individual1.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import cl.bootcamp.individual1.components.ProfileCard
import cl.bootcamp.individual1.components.Separation
import cl.bootcamp.individual1.viewModel.ContactsViewModel

@Composable
fun HomeView(navController: NavController, viewModel: ContactsViewModel){
    Scaffold {paddingValues ->
        Column (modifier = Modifier.padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            AddProfile(navController = navController)
            Separation()
            ShowProfile(navController = navController, viewModel = viewModel)
        }
    }
}

@Composable
fun AddProfile(navController: NavController){
    Button(onClick = {
        navController.navigate("add")
    }) {
        Text(text = "Agregar contacto")
    }
}

@Composable
fun ShowProfile(navController: NavController, viewModel: ContactsViewModel){
    val contacts by viewModel.getAllContacts().collectAsState(initial = emptyList())
    LazyColumn {
        items(contacts) {contact ->
            ProfileCard(
                name = contact.name,
                phoneNumber = contact.phoneNumber,
                email = contact.email,
                profileImage = contact.profileImage,
                birthday = contact.birthday,
                onClick = {navController.navigate("add")
                          viewModel.listenID.value = contact.id},
                deleteClick = {viewModel.deleteContact(contact)}
            )
        }
    }
}


