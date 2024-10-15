package cl.bootcamp.individual6.datasource


import cl.bootcamp.individual6.model.MovieResponse
import cl.bootcamp.individual6.util.Constant.Companion.API_KEY
import cl.bootcamp.individual6.util.Constant.Companion.ENDPOINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RestDataSource {

    @GET(ENDPOINT)
    suspend fun getMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: Int = 1,
        @Query("language") language: String = "en-US",
    ): MovieResponse


}