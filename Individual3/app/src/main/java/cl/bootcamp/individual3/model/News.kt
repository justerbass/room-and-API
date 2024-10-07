package cl.bootcamp.individual3.model


data class News(
    val source: Source,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
)

data class Source(
    val id: String,
    val name: String?
)

data class NewsResponse(

    val articles: List<News>?
)