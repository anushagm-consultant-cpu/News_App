package com.example.news_app

data class NewsResponse(
    val articles: List<Article>
)

data class Article(
    val title: String?,
    val description: String?,
    val urlToImage: String?,

)
