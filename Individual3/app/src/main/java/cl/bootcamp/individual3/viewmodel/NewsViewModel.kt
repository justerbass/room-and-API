package cl.bootcamp.individual3.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.bootcamp.individual3.model.Article
import cl.bootcamp.individual3.repository.NewsRepository
import cl.bootcamp.individual3.state.ArticleState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel(){

    private val _news = MutableStateFlow<List<Article>>(emptyList())
    val news = _news.asStateFlow()

    var state by mutableStateOf(ArticleState())
        private set


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

    fun getNewsById(id: String) {
         viewModelScope.launch {
             withContext(Dispatchers.IO) {
                 val result = newsRepository.getNewsById(id)
                 state = state.copy(
                     name = result?.articles?.get(0)?.source?.name ?: state.name,
                     author = result?.articles?.get(0)?.author ?: state.author,
                     title = result?.articles?.get(0)?.title ?: state.title,
                     description = result?.articles?.get(0)?.description ?: state.description,
                     url = result?.articles?.get(0)?.url ?: state.url,
                     urlToImage = result?.articles?.get(0)?.urlToImage ?: state.urlToImage
                 )
             }
         }
     }


     fun clean() {
         _news.value = emptyList()
     }

}