package cl.bootcamp.sprint6.dataSource

import cl.bootcamp.sprint6.model.ProductResults
import cl.bootcamp.sprint6.util.Constants.Companion.ENDPOINT_DETAIL
import cl.bootcamp.sprint6.util.Constants.Companion.ENDPOINT_PRODUCTS
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RestDatoSource {

    @GET(ENDPOINT_PRODUCTS)
    suspend fun getProduct(): List<ProductResults>

    @GET("${ENDPOINT_DETAIL}/{id}")
    suspend fun getDetailsProductsById(@Path("id") id: Int): Response<ProductResults>

}