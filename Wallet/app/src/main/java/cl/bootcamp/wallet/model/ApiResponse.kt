package cl.bootcamp.wallet.model

data class ApiResponse(
    val results: ArrayList<UserWallet>,
)

data class UserWallet(
    val id : Int,
    val nombre : String,
    val pais : String,
    val imagenLink : String,
    val cuenta: String,
    val saldo: Double,
    val depositos: Boolean
)