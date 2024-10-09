package cl.bootcamp.sprint6.view

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import cl.bootcamp.sprint6.ui.theme.Color2
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
                titulo = stringResource(id = R.string.details_title, viewModel.state.name),
                mostrarBotton = true
            ) { navController.popBackStack() }

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
    var email = "info@novaera.cl"
    var mensaje = stringResource(id = R.string.email_message)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(Color2)
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
                Text(text = stringResource(id = R.string.nombre_label) + " ${state.name}", fontWeight = FontWeight.Bold)
                HorizontalDivider()
                Text(text = stringResource(id = R.string.precio_label) + ": ${state.price}", fontWeight = FontWeight.Bold)
                HorizontalDivider()
                Text(text = stringResource(id = R.string.descripcion_label) + ": ${state.description}", fontWeight = FontWeight.Bold)
                HorizontalDivider()
                Text(text = stringResource(id = R.string.ultimo_precio_label) + ": ${state.lastPrice}", fontWeight = FontWeight.Bold)
                HorizontalDivider()
                Text(text = if (state.credit == true) stringResource(id = R.string.credito_label) else stringResource(id = R.string.efectivo_label), fontWeight = FontWeight.Bold)
                HorizontalDivider()
            }
            Button(
                onClick = {
                    val intent = Intent(Intent.ACTION_SEND)
                    val emailAddress = arrayOf(email)
                    intent.putExtra(Intent.EXTRA_EMAIL, emailAddress)
                    intent.putExtra(Intent.EXTRA_TEXT, mensaje)
                    intent.type = "message/rfc822"
                    context.startActivity(Intent.createChooser(intent, "Email del cliente"))
                }
            ) { Text(text = stringResource(id = R.string.send_email)) }
        }
    }
}