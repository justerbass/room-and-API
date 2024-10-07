package cl.bootcamp.individual3.state

import cl.bootcamp.individual3.model.Source


data class NewsState(
    val source: Source?,
    val author: String? = "",
    val title: String = "",
    val description: String? = "",
    val url: String = "",
    val urlToImage: String? = ""
)

data class SourceState(
    val id :String = "",
    val name :String? = "")

