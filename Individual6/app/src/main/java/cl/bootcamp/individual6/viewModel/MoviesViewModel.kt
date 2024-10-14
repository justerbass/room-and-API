package cl.bootcamp.individual6.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.bootcamp.individual6.model.Movie
import cl.bootcamp.individual6.repository.MoviesRepository
import cl.bootcamp.individual6.state.MovieState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repository: MoviesRepository) : ViewModel() {

    private val _state = MutableStateFlow(MovieState())
    val state: StateFlow<MovieState> = _state

    init {
        loadMovies(1)
    }

    fun loadMovies(page: Int) {
        viewModelScope.launch {
            _state.value = MovieState(isLoading = true)
            Log.d("MoviesViewModel", "Loading movies for page: $page")
            try {
                val movies = repository.getMoviesFomrtApi(page)
                _state.value = MovieState(movies = movies)
            } catch (e: Exception) {
                _state.value = MovieState(error = e.message)
            }
        }
    }

    fun loadMoviesFromDb() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getMoviesFromDb().collect { movies ->
                val moviesList = movies.map { movie ->
                    Movie(
                        id = movie.id,
                        original_title = movie.original_title,
                        poster_path = movie.poster_path,
                        release_date = movie.release_date,
                        vote_average = movie.vote_average
                    )
                }
                withContext(Dispatchers.Main) {
                    _state.value = MovieState(movies = moviesList)
                }

            }
        }
    }
}