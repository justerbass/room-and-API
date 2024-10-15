package cl.bootcamp.individual6.viewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.bootcamp.individual6.model.MoviesEntity
import cl.bootcamp.individual6.repository.MoviesRepository
import cl.bootcamp.individual6.state.MovieState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repository: MoviesRepository) : ViewModel() {

    val movies : Flow<List<MoviesEntity>> by lazy {
        repository.getMoviesFromDb()
    }

    private var _state = MutableStateFlow(MovieState())
    var state: StateFlow<MovieState> = _state

    private val _isLoading: MutableStateFlow<Boolean> by lazy {
        MutableStateFlow<Boolean>(false)
    }

    val isLoading: Flow<Boolean> get() = _isLoading

    fun addMovie(){
        if(!_isLoading.value){
            viewModelScope.launch(Dispatchers.IO) {
                _isLoading.value = true
                repository.getOneMoviewFromApi()
                _isLoading.value = false

            }
        }
    }

    fun deleteMovie(toDelete: MoviesEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteMovie(toDelete)
        }

    }

}