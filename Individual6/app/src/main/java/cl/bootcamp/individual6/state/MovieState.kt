package cl.bootcamp.individual6.state

import cl.bootcamp.individual6.model.Movie
import cl.bootcamp.individual6.model.MoviesEntity

data class MovieState (
    val movies: List<MoviesEntity> = emptyList(),
    var isLoading: Boolean = false,
    val errorMessage: String? = null,
    val selectedMovie: Movie? = null,
    val displayedMovies: List<Any> = emptyList()
)
