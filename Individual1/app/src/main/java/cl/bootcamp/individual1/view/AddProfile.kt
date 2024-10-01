package cl.bootcamp.individual1.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import cl.bootcamp.individual1.components.InputText
import cl.bootcamp.individual1.components.Separation
import cl.bootcamp.individual1.viewModel.ContactsViewModel

@Composable
fun AddProfile(navController: NavController, viewModel: ContactsViewModel){
    Scaffold {paddingValues ->
        Column (modifier = Modifier.padding(paddingValues)){
            InputText(hint = "name", onValueChange = , value = )
            Separation()
            InputText(hint = "Phone Number", onValueChange = , value =  )
            Separation()
            InputText(hint = "Email", onValueChange = , value = )
            Separation()
            InputText(hint = "URL Profile Image", onValueChange = , value = )
            Separation()
            InputText(hint = "Birthday", onValueChange = , value = )
            Separation()
        }
    }
}

