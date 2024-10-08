package cl.bootcamp.wallet.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun CardUser(
    nombre: String,
    pais: String,
    imagenLink: String,
    cuenta: String,
    onClick: () -> Unit
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable { onClick() }
            .shadow(5.dp),
        shape = RoundedCornerShape(5.dp),
        elevation = CardDefaults.cardElevation(11.dp)
    ){
        Row (horizontalArrangement = Arrangement.SpaceAround){
            ImageUser(imagen = imagenLink)
            Column(modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceEvenly) {
                Text(text = nombre)
                Text(text = pais)
                Text(text = cuenta)
            }
        }
    }
}

@Composable
fun ImageUser(imagen: String){
    val image = rememberAsyncImagePainter(model = imagen)

    Image(painter = image, contentDescription = null)
}