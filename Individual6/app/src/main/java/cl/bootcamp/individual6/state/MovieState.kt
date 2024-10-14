package cl.bootcamp.individual6.state

import cl.bootcamp.individual6.model.Movie
import cl.bootcamp.individual6.model.MoviesEntity

data class MovieState (
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val selectedMovie: MoviesEntity? = null
)
