package cl.bootcamp.individual1.room

import androidx.room.Database
import androidx.room.RoomDatabase
import cl.bootcamp.individual1.model.Contacts

@Database(entities = [Contacts::class], version = 1, exportSchema = false)
abstract class ContatcsDataBase : RoomDatabase() {
    abstract fun contactsDao(): ContactsDao
}