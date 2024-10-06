package cl.bootcamp.individual3.state


data class ArticleState(
    val id :String? = "",
    val name :String? = "",
    val author: String? = "",
    val title: String = "",
    val description: String? = "",
    val url: String = "",
    val urlToImage: String? = ""
)


