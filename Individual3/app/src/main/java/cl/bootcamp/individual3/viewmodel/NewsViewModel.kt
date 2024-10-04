package cl.bootcamp.individual3.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.bootcamp.individual3.model.News
import cl.bootcamp.individual3.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel(){

    private val _news = MutableStateFlow<List<News>>(emptyList())
    val news = _news.asStateFlow()

    init {
        fetchNews()
    }

    private fun fetchNews() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
               val result = newsRepository.getNews()
                _news.value = result ?: emptyList()
            }

        }
    }

     private fun getNewsById(id: String) {
         viewModelScope.launch {
             withContext(Dispatchers.IO) {
                 val result = newsRepository.getNewsById(id)
             }
         }
     }

     private fun getNewsByName(name: String) {
         viewModelScope.launch {
             withContext(Dispatchers.IO) {
                 val result = newsRepository.getNewsByName(name)
             }
         }
     }
}