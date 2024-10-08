package cl.bootcamp.sprint6.view

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cl.bootcamp.sprint6.R
import cl.bootcamp.sprint6.component.TopBarComponent
import cl.bootcamp.sprint6.viewModel.ProductViewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun DetailsView(viewModel: ProductViewModel, navController: NavController, id: Int) {

    LaunchedEffect (Unit) {
        viewModel.getProductById(id)
    }

    DisposableEffect (Unit){
        onDispose {
            viewModel.clean()
        }
    }

    Scaffold (modifier = Modifier.fillMaxSize()
        , topBar = {
            TopBarComponent(
            titulo = "Home",
            mostrarBotton = true,
            onClick = { navController.popBackStack() }
        )
        })

    {
        ContentDetailView(it, viewModel = viewModel)
    }
}

@Composable
fun ContentDetailView(
    paddingValues: PaddingValues,
    viewModel: ProductViewModel
) {
    val state = viewModel.state
    val context = LocalContext.current
    val image = rememberAsyncImagePainter(model = state.image)
    var nuevoNombre = state.name.replace(" ", "_")
    var email = "${nuevoNombre}@hotmail.com"
    var asunto = "Formulario de Contacto - ${state.name}"
    var mensaje = """Hola
        
        Somos parte del equipo de contacto de Wallet, Te animas a que podamos
        contactarte, para poder recibir información importante.
        
        Número de Contacto: _________
        
        Gracias.
    """.trimIndent()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Image(
            painter = image,
            "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
        )
        Column(
            modifier = Modifier
                .padding(20.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "nombre: " + " ${state.name}",
                    fontWeight = FontWeight.Bold
                )
                HorizontalDivider()
                Text(text = "Precio: ${state.price}", fontWeight = FontWeight.Bold)
                HorizontalDivider()
                Text(text = "descripcion: ${state.description}", fontWeight = FontWeight.Bold)
                HorizontalDivider()
                Text(text = "ultimo Precio: ${state.lastPrice}", fontWeight = FontWeight.Bold)
                HorizontalDivider()
                Text(text = "Credito: ${state.credit}", fontWeight = FontWeight.Bold)
                HorizontalDivider()
            }
            Button(
                onClick = {
                    val intent = Intent(Intent.ACTION_SEND)
                    val emailAddress = arrayOf(email)
                    intent.putExtra(Intent.EXTRA_EMAIL, emailAddress)
                    intent.putExtra(Intent.EXTRA_SUBJECT, asunto)
                    intent.putExtra(Intent.EXTRA_TEXT, mensaje)
                    intent.type = "message/rfc822"
                    context.startActivity(Intent.createChooser(intent, "Email del cliente"))
                }
            ) { Text(text = "Enviar Correo") }
        }
    }
}