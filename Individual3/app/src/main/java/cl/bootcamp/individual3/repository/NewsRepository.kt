package cl.bootcamp.individual3.repository

import cl.bootcamp.individual3.data.ApiNews
import cl.bootcamp.individual3.model.News
import javax.inject.Inject

class NewsRepository @Inject constructor(private val apiNews: ApiNews){

    suspend fun getNews() : List<News>? {
        val response = apiNews.getNews()
        if (response.isSuccessful){
            return response.body()?.articles
        }
        return null
    }




}