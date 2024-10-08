package cl.bootcamp.sprint6.dataSource

import androidx.room.Database
import androidx.room.RoomDatabase
import cl.bootcamp.sprint6.model.Product
import cl.bootcamp.sprint6.model.ProductDao


@Database(
    entities = [Product::class],
    version = 1)
abstract class DbDataSource : RoomDatabase() {
    abstract fun productDao(): ProductDao
}