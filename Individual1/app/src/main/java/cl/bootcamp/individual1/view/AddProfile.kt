package cl.bootcamp.individual1.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cl.bootcamp.individual1.components.InputText
import cl.bootcamp.individual1.components.Separation
import cl.bootcamp.individual1.model.Contacts
import cl.bootcamp.individual1.viewModel.ContactsViewModel

@Composable
fun AddProfile(navController: NavController, viewModel: ContactsViewModel){

    val id = viewModel.listenID.value

    val name = remember { mutableStateOf("") }
    val phone = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val urlimg = remember { mutableStateOf("")}
    val birthday = remember { mutableStateOf("")}
    if (id != 0){

       val contact = viewModel.getContactById(id).collectAsState(initial = null)

        name.value = contact.value?.name ?: ""
        phone.value = contact.value?.phoneNumber ?: ""
        email.value = contact.value?.email ?: ""
        urlimg.value = contact.value?.profileImage ?: ""
        birthday.value = contact.value?.birthday ?: ""
    }


    Scaffold {paddingValues ->
        Column (modifier = Modifier.padding(paddingValues).padding(15.dp).fillMaxSize()
            .background(Color.LightGray),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally){
            InputText(hint = "name", onValueChange = {name.value = it}, value = name.value)
            Separation()
            InputText(hint = "Phone Number", onValueChange = {phone.value = it}, value = phone.value)
            Separation()
            InputText(hint = "Email", onValueChange = {email.value = it}, value = email.value)
            Separation()
            InputText(hint = "URL Profile Image", onValueChange = {urlimg.value = it}, value = urlimg.value)
            Separation()
            InputText(hint = "Birthday", onValueChange = {birthday.value = it}, value = birthday.value)
            Separation()
            SaveData(
                viewModel = viewModel,
                navController = navController,
                name = name.value,
                phone = phone.value,
                email = email.value,
                urlimg = urlimg.value,
                birthday = birthday.value,
                id = id
            )

        }
    }
}

@Composable
fun SaveData(viewModel: ContactsViewModel,
             navController: NavController,
             name: String,
             phone: String,
             email: String,
             urlimg: String,
             birthday: String,
             id:Int
             ){
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
        // Crear el nuevo objeto Contacts con todos los valores

        if (id != 0 ){

            val contact = Contacts(
                id = id,
                name = name,
                phoneNumber = phone,
                email = email,
                profileImage = urlimg,
                birthday = birthday
            )

            viewModel.updateContact(contact)
            }
        else{

            val newContact = Contacts(
                name = name,
                phoneNumber = phone,
                email = email,
                profileImage = urlimg,
                birthday = birthday
            )

            viewModel.insertContact(newContact)
        }

        navController.navigate("home")
    }) {

        Text("Guardar",
            modifier = Modifier.padding(10.dp),
            fontSize = 20.sp)

    }
}

