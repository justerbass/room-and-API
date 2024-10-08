package cl.bootcamp.individual3.data


import cl.bootcamp.individual3.model.News
import cl.bootcamp.individual3.model.NewsResponse
import cl.bootcamp.individual3.util.Constant.Companion.API_KEY
import cl.bootcamp.individual3.util.Constant.Companion.ENDPOINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiNews {

    @GET(ENDPOINT + API_KEY)
    suspend fun getNews(): Response<NewsResponse>

    @GET("${ENDPOINT}/{id}${API_KEY}")
    suspend fun getNewsById(@Path("id") id: String): Response<News>

}