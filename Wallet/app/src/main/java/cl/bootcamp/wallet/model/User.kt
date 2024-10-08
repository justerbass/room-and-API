package cl.bootcamp.wallet.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User (
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    @ColumnInfo(name = "nombre")
    val nombre : String,
    @ColumnInfo(name = "pais")
    val pais : String,
    @ColumnInfo(name = "imagenLink")
    val imagenLink : String,
    @ColumnInfo(name = "cuenta")
    val cuenta: String,
    @ColumnInfo(name = "saldo")
    val saldo: Double,
    @ColumnInfo(name = "depositos")
    val depositos: Boolean
)