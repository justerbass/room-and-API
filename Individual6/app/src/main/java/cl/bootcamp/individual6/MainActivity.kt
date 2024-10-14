package cl.bootcamp.individual6

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cl.bootcamp.individual6.model.Movie
import cl.bootcamp.individual6.ui.theme.Individual6Theme
import cl.bootcamp.individual6.viewModel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel : MoviesViewModel by viewModels()
        enableEdgeToEdge()
        setContent {
            Individual6Theme {
                MoviesScreen(viewModel)
            }
        }
    }
}


@Composable
fun MoviesScreen(viewModel: MoviesViewModel) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        Log.d("MoviesScreen", "MoviesScreen LaunchedEffect called")
        viewModel.loadMoviesFromDb() // Cargar películas cuando se inicia la pantalla
    }

    // Revisar aquí
    when {
        state.error != null -> {
            Log.e("MoviesScreen", "Error in MoviesState: ${state.error}")
            Text("Error: ${state.error}")
        }
        state.movies.isEmpty() -> {
            Log.d("MoviesScreen", "Movies are loading...")
            Text("Cargando películas...")
        }
        else -> {
            Log.d("MoviesScreen", "Displaying movies: ${state.movies.size} movies")
            LazyColumn {
                items(state.movies) { movie ->
                    MovieCard(movie)
                }
            }
        }
    }
}





@Composable
fun MovieCard(movie: Movie) {
    // Renderiza la tarjeta de película aquí
    Card {
        Column {
            Text(text = movie.original_title)
            // Agrega más detalles de la película aquí
        }
    }
}