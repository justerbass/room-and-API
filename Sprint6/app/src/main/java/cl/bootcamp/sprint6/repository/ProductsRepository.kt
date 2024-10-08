package cl.bootcamp.sprint6.repository

import cl.bootcamp.sprint6.dataSource.RestDatoSource
import cl.bootcamp.sprint6.model.Product
import cl.bootcamp.sprint6.model.ProductDao
import cl.bootcamp.sprint6.model.ProductResults
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ProductsRepository {
    suspend fun getNewProductsApi(): ArrayList<ProductResults>
    suspend fun getProductById(id: Int): ProductResults
    fun getAllProductsRoom(): Flow<List<Product>>
}

class ProductsRepositoryImp @Inject constructor(
    private val dataSource: RestDatoSource,
    private val prodcutDao: ProductDao
) : ProductsRepository {
    override suspend fun getNewProductsApi(): ArrayList<ProductResults> {
        val data = dataSource.getProduct()
        data.forEach {
            val product = Product(
                id = it.id,
                name = it.name,
                price = it.price,
                image = it.image,
                description = it.description,
                lastPrice = it.lastPrice,
                credit = it.credit
            )
            prodcutDao.insert(product)
        }
        return ArrayList(data)
    }

    override suspend fun getProductById(id: Int): ProductResults {
        val data = dataSource.getDetailsProductsById(id).body()!!
        val detailsProduct = ProductResults(
            id = data.id,
            name = data.name,
            price = data.price,
            image = data.image,
            description = data.description,
            lastPrice = data.lastPrice,
            credit = data.credit
        )
        return detailsProduct
    }

    override fun getAllProductsRoom(): Flow<List<Product>> = prodcutDao.getAll()

}