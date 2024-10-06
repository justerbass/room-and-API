package cl.bootcamp.individual3.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import cl.bootcamp.individual3.component.Separate
import cl.bootcamp.individual3.component.ShowImageUrl
import cl.bootcamp.individual3.component.ShowText
import cl.bootcamp.individual3.model.Article
import cl.bootcamp.individual3.viewmodel.NewsViewModel
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsNews(navController: NavController, viewModel: NewsViewModel, id: String?, name: String?) {

    LaunchedEffect(Unit) {
        if (id == null) {
            name?.let {
                viewModel.getNewsById(it.replace(" ", "-"))
            }
        } else {
            viewModel.getNewsById(id)
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            viewModel.clean()
        }
    }

    Scaffold (topBar ={
        CenterAlignedTopAppBar(
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = "Regresar",
                        tint = Color.White
                    )
                }
            },
            title = { Text(text = "newsapi.org",
                textAlign = TextAlign.Center,
                color = Color.White,
                fontWeight = FontWeight.Bold)
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF56c1ff))

        )
    }
    ){
        paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()) {

            ContentDetailView(viewModel)

        }
    }
}


@Composable
fun ContentDetailView(viewModel: NewsViewModel) {
    val state = viewModel.state

    // Imagen de la noticia
    ShowImageUrl(url = state.urlToImage ?: "")
    Separate(value = 8)

    // Nombre de la fuente
    ShowText(text = state.name ?: "", title = true, maxLines = 2)
    Separate(value = 8)

    // Autor
    ShowText(text = state.author ?: "", title = false, maxLines = 1)
    Separate(value = 8)

    // Título de la noticia
    ShowText(text = state.title, title = true, maxLines = 2)
    Separate(value = 8)

    // Descripción de la noticia
    ShowText(text = state.description ?: "", title = false, maxLines = 5)
    Separate(value = 8)

    // URL de la fuente
    ShowText(text = state.url, title = false, maxLines = 1)

}