package cl.bootcamp.wallet.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import cl.bootcamp.wallet.model.User
import cl.bootcamp.wallet.model.UserDao

@Database(entities = [User::class], version = 1)
abstract class BdDataSource :RoomDatabase(){
    abstract fun userDao(): UserDao
}