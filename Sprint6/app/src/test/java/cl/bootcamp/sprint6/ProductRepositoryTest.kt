package cl.bootcamp.sprint6


import android.arch.core.executor.testing.InstantTaskExecutorRule
import cl.bootcamp.sprint6.dataSource.RestDatoSource
import cl.bootcamp.sprint6.model.Product
import cl.bootcamp.sprint6.model.ProductDao
import cl.bootcamp.sprint6.repository.ProductsRepositoryImp
import cl.bootcamp.sprint6.util.Constants.Companion.ENDPOINT_PRODUCTS
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.StandardCharsets

val product1 = Product(
    id = 1,
    name = "Producto A",
    price = 100,
    image = "https://example.com/imageA.jpg",
    description = "Descripción del Producto A.",
    lastPrice = 120,
    credit = true
)

val product2 = Product(
    id = 2,
    name = "Producto B",
    price = 200,
    image = "https://example.com/imageB.jpg",
    description = null, // Sin descripción
    lastPrice = 250,
    credit = false
)

val product3 = Product(
    id = 3,
    name = "Producto C",
    price = 150,
    image = "https://example.com/imageC.jpg",
    description = "Descripción del Producto C.",
    lastPrice = null, // Último precio no disponible
    credit = true
)

class ProductRepositoryTest {
   private val mockWebServer = MockWebServer().apply {
       url("/")
       dispatcher = myDispatcher
   }

    private val restDataSource = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RestDatoSource::class.java)

    private val newsRepository = ProductsRepositoryImp(restDataSource, MockProductDao())


    @get :Rule
    val rule = InstantTaskExecutorRule()

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `verificar si obtenemos los productos de la DB`() = runBlocking {
        val products = newsRepository.getAllProductsRoom().first()
        assertEquals(3, products.size)
    }

}

class MockProductDao : ProductDao {

    private val products =
        MutableStateFlow<List<Product>>(listOf(product1, product2, product3))

    override fun insert(product: Product) {
        products.value = products.value.toMutableList().apply { add(product) }
    }

    override fun getAll(): Flow<List<Product>> = products

}

val myDispatcher: Dispatcher = object : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {
            "/$ENDPOINT_PRODUCTS" -> MockResponse().apply {
                addResponse("api_products.json")
            }
            else -> MockResponse().setResponseCode(404)
        }
    }
}

fun MockResponse.addResponse(filePath: String): MockResponse {
    val inputStream = javaClass.classLoader?.getResourceAsStream(filePath)
    val source = inputStream?.source()?.buffer()
    source?.let {
        setResponseCode(200)
        setBody(it.readString(StandardCharsets.UTF_8))
    }
    return this
}