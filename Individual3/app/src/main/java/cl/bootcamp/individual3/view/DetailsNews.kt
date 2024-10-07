package cl.bootcamp.individual3.view


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

import androidx.navigation.NavController

import cl.bootcamp.individual3.component.Separate
import cl.bootcamp.individual3.component.ShowImageUrl
import cl.bootcamp.individual3.component.ShowText

import cl.bootcamp.individual3.navigation.Screnn
import cl.bootcamp.individual3.viewmodel.NewsViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsNews(navController: NavController, viewModel: NewsViewModel, id: String) {

    Log.d("Detalles", "ID recibido: $id")

    if (id is String){
        Log.d("DATO_RARO", id)
    }

    val news by viewModel.news.collectAsState()
    val newsById = news.find { it.source.id == id }


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
            .fillMaxSize()
            .background(Color(0xFF1b263b))
        ) {

                if (newsById?.source?.id != null) {

                    Column(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        ,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center) {
                        Box(modifier = Modifier.size(400.dp)){
                            ShowImageUrl(url = newsById.urlToImage ?: "")
                            Separate(value = 8)
                        }

                        // Nombre de la fuente
                        ShowText(text = newsById.source.name ?: "", title = true, maxLines = 2)
                        Separate(value = 8)

                        // Autor
                        ShowText(text = newsById.author ?: "", title = false, maxLines = 1)
                        Separate(value = 8)

                        // Título de la noticia
                        newsById.title?.let { ShowText(text = it, title = true, maxLines = 2) }
                        Separate(value = 8)

                        // Descripción de la noticia
                        ShowText(text = newsById.description ?: "", title = false, maxLines = 5)
                        Separate(value = 8)

                        // URL de la fuente
                        newsById.url?.let { ShowText(text = it, title = false, maxLines = 1) }
                    }

                }else{
                    Text(text = "No se encontraron datos")
                }



        }
    }
}


//@Composable
//fun ContentDetailView(viewModel: NewsViewModel , id: String?) {
//
////    val news = viewModel.news.collectAsState()
////    val newsById = news.value.find { it.source.id == id }
//
//    if (newsById != null && newsById.source.id != "") {
//        Column(
//            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
//                ,
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center) {
//            Box(modifier = Modifier.size(400.dp)){
//                ShowImageUrl(url = newsById.urlToImage ?: "")
//                Separate(value = 8)
//
//            }
//            // Imagen de la noticia
//
//            // Nombre de la fuente
//            ShowText(text = newsById.source.name ?: "", title = true, maxLines = 2)
//            Separate(value = 8)
//
//            // Autor
//            ShowText(text = newsById.author ?: "", title = false, maxLines = 1)
//            Separate(value = 8)
//
//            // Título de la noticia
//            newsById.title?.let { ShowText(text = it, title = true, maxLines = 2) }
//            Separate(value = 8)
//
//            // Descripción de la noticia
//            ShowText(text = newsById.description ?: "", title = false, maxLines = 5)
//            Separate(value = 8)
//
//            // URL de la fuente
//            newsById.url?.let { ShowText(text = it, title = false, maxLines = 1) }
//        }
//
//    }else{
//        Text(text = "No se encontraron datos")
//    }
//
//
//
//}