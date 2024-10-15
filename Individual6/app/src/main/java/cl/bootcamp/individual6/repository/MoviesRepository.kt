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

    suspend fun getOneMoviewFromApi(): MoviesEntity
    fun getMoviesFromDb(): Flow<List<MoviesEntity>>
    suspend fun deleteMovie(toDelete: MoviesEntity)
}

class MoviesRepositoryImp @Inject constructor(
    private val restDataSource: RestDataSource,
    private val moviesDao: MoviesDao
) : MoviesRepository {

    override suspend fun getOneMoviewFromApi(): MoviesEntity {

        val response = restDataSource.getMovies().results[0]
        val movie = MoviesEntity(
            id = response.id,
            original_title = response.original_title,
            poster_path = IMAGE_BASE_URL + response.poster_path,
            release_date = response.release_date,
            vote_average = response.vote_average
        )
        moviesDao.insertOneMovie(movie)
        return movie
    }

    override fun getMoviesFromDb(): Flow<List<MoviesEntity>> {
        return moviesDao.getMovies()
    }


    override suspend fun deleteMovie(toDelete: MoviesEntity) = moviesDao.deleteMovie(toDelete)

}