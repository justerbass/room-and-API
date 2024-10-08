package cl.bootcamp.sprint6.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "nombre")
    var name: String = "",
    @ColumnInfo(name = "precio")
    var price: Int = 0,
    @ColumnInfo(name = "imagen")
    var image: String = "",
    @ColumnInfo(name = "descripcion")
    var description: String? = "",
    @ColumnInfo(name = "ultimoPrecio")
    var lastPrice: Int? = 0,
    @ColumnInfo(name = "credito")
    var credit: Boolean? = false

)
