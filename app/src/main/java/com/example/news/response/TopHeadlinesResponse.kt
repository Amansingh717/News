package com.example.news.response

import com.example.news.room.entities.Article
import com.google.gson.annotations.SerializedName

data class TopHeadlinesResponse(
    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("totalResults")
    val totalResults: Int? = null,

    @field:SerializedName("articles")
    val articles: List<Article>? = null
)