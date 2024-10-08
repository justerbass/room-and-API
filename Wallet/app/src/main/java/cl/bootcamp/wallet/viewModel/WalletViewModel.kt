package cl.bootcamp.wallet.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.bootcamp.wallet.model.User
import cl.bootcamp.wallet.repository.WalletRepository
import cl.bootcamp.wallet.state.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WalletViewModel @Inject constructor(private val repository: WalletRepository) : ViewModel() {

    var state by mutableStateOf(UserState())
        private set

    val user: Flow<List<User>> by lazy {
        repository.getAllUserRoom()

    }

    fun getALLAPI() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.getAllUserApi()
            }
        }
    }

    fun getUserById(id: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = repository.getUserById(id)
                state = state.copy(
                    nombre = result.nombre,
                    pais = result.pais,
                    imagenLink = result.imagenLink,
                    cuenta = result.cuenta,
                    saldo = result.saldo,
                    depositos = result.depositos
                )

            }
        }

    }

    fun clean(){
        state = state.copy(
            nombre = "",
            pais = "",
            imagenLink = "",
            cuenta = "",
            saldo = 0.0,
            depositos = false
        )
    }
}