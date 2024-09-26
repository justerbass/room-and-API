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

    @Query("SELECT * FROM contacts WHERE name = :name")
    fun getContactByName(name: String): Flow<Contacts>

    @Query("SELECT * FROM contacts WHERE phone_number = :phoneNumber")
    fun getContactByPhoneNumber(phoneNumber: String): Flow<Contacts>

    @Query("SELECT * FROM contacts WHERE email_address = :email")
    fun getContactByEmail(email: String): Flow<Contacts>

    @Query("SELECT * FROM contacts WHERE profile_image = :profileImage")
    fun getContactByProfileImage(profileImage: Image): Flow<Contacts>

    @Query("SELECT * FROM contacts WHERE birthday = :birthday")
    fun getContactByBirthday(birthday: String): Flow<Contacts>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(contact: Contacts)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateContact(contact: Contacts)

    @Delete
    suspend fun deleteContact(contact: Contacts)
}