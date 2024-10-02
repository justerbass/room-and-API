package cl.bootcamp.integrador1.model

data class ApiResponse(
    val results: List<Results> = emptyList()
)

data class Results (
    val name: UserName?,
    val picture: UserPicture?,
    val location: UserLocation?
)
