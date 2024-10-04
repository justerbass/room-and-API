package cl.bootcamp.individual3.repository

import cl.bootcamp.individual3.data.ApiNews
import cl.bootcamp.individual3.model.News
import cl.bootcamp.individual3.model.NewsResponse
import javax.inject.Inject

class NewsRepository @Inject constructor(private val apiNews: ApiNews){

    suspend fun getNews() : List<News>? {
        val response = apiNews.getNews()
        if (response.isSuccessful){
            return response.body()?.articles
        }
        return null
    }

    suspend fun getNewsById(id: String) : NewsResponse? {
        val response = apiNews.getNewsById(id)
        if (response.isSuccessful){
            return response.body()
        }
        return null
    }

    suspend fun getNewsByName(name: String) : NewsResponse? {
        val response = apiNews.getNewsByName(name)
        if (response.isSuccessful) {
            return response.body()
        }
        return null
    }

}