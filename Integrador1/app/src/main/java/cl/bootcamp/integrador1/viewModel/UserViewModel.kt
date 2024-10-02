package cl.bootcamp.integrador1.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.bootcamp.integrador1.model.User
import cl.bootcamp.integrador1.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepositoryImp: UserRepository
) :ViewModel() {

    private val _isLoading : MutableStateFlow<Boolean> by lazy {
        MutableStateFlow(false)
    }

    val isLoading : Flow<Boolean> get() = _isLoading

    val  users : Flow<List<User>> by lazy {
    userRepositoryImp.getAllUsers()
    }

    fun addNewUser() {
        if (_isLoading.value) {
            viewModelScope.launch(Dispatchers.IO) {
                _isLoading.value = true
                userRepositoryImp.getNewUser()
                _isLoading.value = false
            }
        }
    }

    fun deleteUser(toDelete: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepositoryImp.deleteUser(toDelete)
        }

    }
}