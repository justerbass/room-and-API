package cl.bootcamp.individual6.repository

import android.util.Log
import cl.bootcamp.individual6.datasource.RestDataSource
import cl.bootcamp.individual6.model.Movie
import cl.bootcamp.individual6.model.MoviesDao
import cl.bootcamp.individual6.model.MoviesEntity
import cl.bootcamp.individual6.util.Constant.Companion.IMAGE_BASE_URL
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface MoviesRepository {

    suspend fun getOneMoviewFromApi(index :Int): MoviesEntity
    fun getMoviesFromDb(): Flow<List<MoviesEntity>>
    suspend fun deleteMovie(toDelete: MoviesEntity)
}

class MoviesRepositoryImp @Inject constructor(
    private val restDataSource: RestDataSource,
    private val moviesDao: MoviesDao
) : MoviesRepository {

    override suspend fun getOneMoviewFromApi(index: Int): MoviesEntity {

        val response = restDataSource.getMovies().results
        if (response.isNotEmpty() && index in response.indices) {
            val movieResponse = response[index] // Usa el índice proporcionado

            val movie = MoviesEntity(
                id = movieResponse.id,
                original_title = movieResponse.original_title,
                poster_path = IMAGE_BASE_URL + movieResponse.poster_path,
                release_date = movieResponse.release_date,
                vote_average = movieResponse.vote_average
            )
            moviesDao.insertOneMovie(movie)
            return movie
        } else {
            throw Exception("Índice fuera de rango o no hay películas disponibles.")
        }
    }

    override fun getMoviesFromDb(): Flow<List<MoviesEntity>> {
        return moviesDao.getMovies()
    }


    override suspend fun deleteMovie(toDelete: MoviesEntity) = moviesDao.deleteMovie(toDelete)

}