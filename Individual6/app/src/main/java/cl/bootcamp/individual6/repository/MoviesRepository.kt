package cl.bootcamp.individual6.repository

import android.util.Log
import cl.bootcamp.individual6.datasource.RestDataSource
import cl.bootcamp.individual6.model.Movie
import cl.bootcamp.individual6.model.MoviesDao
import cl.bootcamp.individual6.model.MoviesEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface MoviesRepository {
    suspend fun getMoviesFomrtApi(page:Int) : List<Movie>
    suspend fun getMoviesFromDb() : Flow<List<MoviesEntity>>

    suspend fun getPOneMovie(id: Int) : MoviesEntity
    suspend fun deleteMovie(toDelete: MoviesEntity)
}

class MoviesRepositoryImp @Inject constructor(
    private val restDataSource: RestDataSource,
    private val moviesDao: MoviesDao
) : MoviesRepository{
    override suspend fun getMoviesFomrtApi(page: Int): List<Movie> {
        val response = restDataSource.getMovies(page = page)
        if (response.isSuccessful) {

            Log.d("MoviesRepository", "Response successful: ${response.body()}")
            val moviesResponse = response.body()?.results ?: emptyList()

            moviesResponse.forEach { movie ->
                val movieEntity = MoviesEntity(
                    id = movie.id,
                    original_title = movie.original_title,
                    poster_path = movie.poster_path,
                    release_date = movie.release_date,
                    vote_average = movie.vote_average

                )
                moviesDao.insertMovies(listOf(movieEntity))
            }

            return moviesResponse
        } else {
            Log.e("MoviesRepository", "Error fetching movies: ${response.code()} ${response.message()}") // Log de error
            throw Exception("Error al recuperar la informacion desde la api")
        }
    }

    override suspend fun getMoviesFromDb(): Flow<List<MoviesEntity>> {
        return moviesDao.getMovies()
    }

    override suspend fun getPOneMovie(id: Int): MoviesEntity {
        delay(2000)
        val movieEntiti = moviesDao.getOneMovie(id)

        val movie = MoviesEntity(
            id = movieEntiti.id,
            original_title = movieEntiti.original_title,
            poster_path = movieEntiti.poster_path,
            release_date = movieEntiti.release_date,
            vote_average = movieEntiti.vote_average
        )
        moviesDao.insertOneMovie(movie)
        return movie
    }

    override suspend fun deleteMovie(toDelete: MoviesEntity) = moviesDao.deleteMovie(toDelete)

}