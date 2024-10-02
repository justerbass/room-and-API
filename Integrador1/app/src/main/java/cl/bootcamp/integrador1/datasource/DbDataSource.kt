package cl.bootcamp.integrador1.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import cl.bootcamp.integrador1.model.User
import cl.bootcamp.integrador1.model.UserDao

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class DbDataSource : RoomDatabase() {
    abstract fun userDao(): UserDao
}