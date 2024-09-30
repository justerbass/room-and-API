package cl.bootcamp.individual1.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.bootcamp.individual1.model.Contacts
import cl.bootcamp.individual1.repository.ContatcsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(private val repository: ContatcsRepository) : ViewModel() {

    fun getAllContacts(): Flow<List<Contacts>> {
        return repository.getAllContacts()
    }

    fun getContactById(id: Int): Flow<Contacts> {
        return repository.getContactById(id)
    }

    fun insertContact(contact: Contacts) = viewModelScope.launch (Dispatchers.IO) {
        repository.insertContact(contact)
    }

    fun updateContact(contact: Contacts) = viewModelScope.launch (Dispatchers.IO) {
        repository.updateContact(contact)
    }

    fun deleteContact(contact: Contacts) = viewModelScope.launch (Dispatchers.IO) {
        repository.deleteContact(contact)
    }

}

