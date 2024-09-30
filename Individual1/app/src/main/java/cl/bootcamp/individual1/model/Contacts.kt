package cl.bootcamp.individual1.model

import android.media.Image
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class Contacts(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "phone_number")
    val phoneNumber: String,

    @ColumnInfo(name = "email_address")
    val email: String,

    @ColumnInfo(name = "profile_image")
    val profileImage: String,
    
    @ColumnInfo(name = "birthday")
    val birthday: String
)
