package cl.bootcamp.individual1.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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
        Column (modifier = Modifier.padding(paddingValues)){
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
            saveData(
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
fun saveData(viewModel: ContactsViewModel,
             navController: NavController,
             name: String,
             phone: String,
             email: String,
             urlimg: String,
             birthday: String,
             id:Int
             ){
    Button(onClick = {
        // Crear el nuevo objeto Contacts con todos los valores
        val contact = Contacts(
            name = name,
            phoneNumber = phone,
            email = email,
            profileImage = urlimg,
            birthday = birthday
        )
        // Llamamos al ViewModel para insertar el contacto en la base de datos, aca llamamos al repo
        if (id != 0 ){
            viewModel.insertContact(contact)}
        else{
            viewModel.updateContact(contact)
        }

        navController.navigate("home")
    }) {

        Text("Guardar")

    }
}

