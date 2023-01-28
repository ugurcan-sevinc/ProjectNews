package com.ugrcaan.projectnews.model

import java.util.Objects

data class Source(val id: String?, val name: String)

data class NewsArticle (var source: Source,
                      var title: String,
                      var description: String,
                      var url: String,
                      var urlToImage: String,
                      var publishedAt: String)

data class NewsModel(
    val status: String,
    val totalResults: Int,
    val articles: ArrayList<NewsArticle>
)