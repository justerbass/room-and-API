package cl.bootcamp.wallet.state

data class UserState(
    val nombre: String = "",
    val pais: String = "",
    val imagenLink: String = "",
    val cuenta: String = "",
    val saldo: Double = 0.0,
    val depositos: Boolean = false
)
