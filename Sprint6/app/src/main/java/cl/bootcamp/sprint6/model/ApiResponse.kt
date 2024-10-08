package cl.bootcamp.sprint6.model

data class ApiResponse(
    val results : ArrayList<ProductResults>
)

data class ProductResults(
    val id: Int,
    val name: String,
    val price: Int,
    val image: String,
    val description: String?,
    val lastPrice: Int?,
    val credit: Boolean?
)

