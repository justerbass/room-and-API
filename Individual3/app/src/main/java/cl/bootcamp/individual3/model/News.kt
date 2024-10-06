package cl.bootcamp.individual3.model

import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("source") val source: Source,
    @SerializedName("author") val author: String?,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String?,
    @SerializedName("url") val url: String,
    @SerializedName("urlToImage") val urlToImage: String?,
)

data class Source(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String
)

data class NewsResponse(
    @SerializedName("status") val status: String,
    @SerializedName("totalResults") val totalResults: Int,
    @SerializedName("articles") val articles: List<Article>
)