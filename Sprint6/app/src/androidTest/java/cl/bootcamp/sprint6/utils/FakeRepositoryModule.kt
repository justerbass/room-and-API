package cl.bootcamp.sprint6.utils

import cl.bootcamp.sprint6.di.RepositoryModule
import cl.bootcamp.sprint6.model.Product
import cl.bootcamp.sprint6.model.ProductResults
import cl.bootcamp.sprint6.repository.ProductsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
class FakeRepositoryModule {


    @Provides
    @Singleton
    fun productRepository(): ProductsRepository = object : ProductsRepository{

        private val product = MutableStateFlow<List<Product>>(
            emptyList())
        override suspend fun getNewProductsApi(): ArrayList<ProductResults> {
            val productList = product.value
            val newProduct = Product(
                id = productList.size + 1,
                name = "name ${productList.size}",
                price = (100 + productList.size * 10),
                image = "https://example.com/image${productList.size}.jpg",
                description = "description ${productList.size}",
                lastPrice = (90 + productList.size * 10),
                credit = true
            )
            product.value = productList.toMutableList().apply { add(newProduct) }
            return newProduct
        }

        override suspend fun getProductById(id: Int): ProductResults {
            TODO("Not yet implemented")
        }

        override fun getAllProductsRoom(): Flow<List<Product>> {
            TODO("Not yet implemented")
        }

    }

}