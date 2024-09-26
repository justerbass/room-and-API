package cl.bootcamp.individual1.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.bootcamp.individual1.model.Contacts
import cl.bootcamp.individual1.repository.ContatcsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(private val repository: ContatcsRepository) : ViewModel() {

    fun insertContact(contact: Contacts) = viewModelScope.launch {
        repository.insertContact(contact)
    }

    fun updateContact(contact: Contacts) = viewModelScope.launch {
        repository.updateContact(contact)
    }

    fun deleteContact(contact: Contacts) = viewModelScope.launch {
        repository.deleteContact(contact)
    }

}