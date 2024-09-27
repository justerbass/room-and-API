package cl.bootcamp.individual1.repository

import cl.bootcamp.individual1.model.Contacts
import cl.bootcamp.individual1.room.ContactsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ContatcsRepository @Inject constructor(private val contactsDao: ContactsDao) {

    suspend fun insertContact(contact: Contacts) = contactsDao.insertContact(contact)
    suspend fun updateContact(contact: Contacts) = contactsDao.updateContact(contact)
    suspend fun deleteContact(contact: Contacts) = contactsDao.deleteContact(contact)
    fun getAllContacts() : Flow<List<Contacts>> = contactsDao.getAllContacts().flowOn(Dispatchers.IO).conflate()
    fun getContactById(id: Int) : Flow<Contacts> = contactsDao.getContactById(id).flowOn(Dispatchers.IO).conflate()

}
