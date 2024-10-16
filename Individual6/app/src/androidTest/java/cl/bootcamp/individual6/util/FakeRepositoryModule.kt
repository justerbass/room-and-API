package cl.bootcamp.individual6.util


import cl.bootcamp.individual6.di.RepositoryModule
import cl.bootcamp.individual6.model.MoviesEntity
import cl.bootcamp.individual6.repository.MoviesRepository
import dagger.Module
import dagger.Provides

import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
class FakeRepositoryModule {

    @Provides
    @Singleton
    fun moviesRepository(): MoviesRepository = object : MoviesRepository {
        private val moviesList = mutableListOf<MoviesEntity>()
        private val moviesFlow = MutableStateFlow<List<MoviesEntity>>(moviesList)

        override suspend fun getOneMovieFromApi(index: Int): MoviesEntity {
            val fakeMovies = listOf(
                MoviesEntity(
                    id = 1L,
                    original_title = "The Great Adventure",
                    poster_path = "https://example.com/images/great_adventure.jpg",
                    release_date = "2023-07-21",
                    vote_average = 8.2f
                )
            )

            if (index in fakeMovies.indices) {
                val selectedMovie = fakeMovies[index]
                moviesList.add(selectedMovie)
                updateMoviesFlow()
                return selectedMovie
            } else {
                throw Exception("Índice fuera de rango o no hay películas disponibles.")
            }
        }

        override fun getMoviesFromDb(): Flow<List<MoviesEntity>> {
            return moviesFlow.asStateFlow()
        }

        override suspend fun deleteMovie(toDelete: MoviesEntity) {
            moviesList.remove(toDelete)
            updateMoviesFlow()
        }

        private fun updateMoviesFlow() {
            moviesFlow.value = moviesList.toList()
        }

    }


}