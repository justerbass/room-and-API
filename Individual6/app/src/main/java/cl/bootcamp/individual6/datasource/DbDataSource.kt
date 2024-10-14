package cl.bootcamp.individual6.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import cl.bootcamp.individual6.model.MoviesDao
import cl.bootcamp.individual6.model.MoviesEntity

@Database(
    entities = [MoviesEntity::class],
    version = 1
)

abstract class DbDataSource : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao

}