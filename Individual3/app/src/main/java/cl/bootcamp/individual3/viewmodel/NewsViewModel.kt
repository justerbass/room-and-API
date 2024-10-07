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

    var state by mutableStateOf(ArticleState(source = null))
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
        clean()
         viewModelScope.launch {
             withContext(Dispatchers.IO) {
                 val result = newsRepository.getNewsById(id)
                 result?.let { news ->
                     state = state.copy(
                         source = news.source,
                         author = news.author ?: "",
                         title = news.title ?: "",
                         description = news.description ?: "",
                         url = news.url ?: "",
                         urlToImage = news.urlToImage ?: ""
                     )
                 }
             }
         }
     }


     fun clean() {
        state = state.copy(
            source = null,
            author = "",
            title = "",
            description = "",
            url = "",
            urlToImage = ""
        )
     }

}