package cl.bootcamp.individual1.room

import android.media.Image
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import cl.bootcamp.individual1.model.Contacts
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactsDao {

    @Query("SELECT * FROM contacts")
    fun getAllContacts(): Flow<List<Contacts>>

    @Query("SELECT * FROM contacts WHERE id = :id")
    fun getContactById(id: Int): Flow<Contacts>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(contact: Contacts)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateContact(contact: Contacts)

    @Delete
    suspend fun deleteContact(contact: Contacts)
}