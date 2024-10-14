package cl.bootcamp.individual6.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MoviesEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOneMovie(movie: MoviesEntity)

    @Query("SELECT * FROM movies")
    fun getMovies(): Flow<List<MoviesEntity>>

    @Query("SELECT * FROM movies WHERE id = :id")
    suspend fun getOneMovie(id: Int): MoviesEntity

    @Delete
    fun deleteMovie(movie: MoviesEntity)


}