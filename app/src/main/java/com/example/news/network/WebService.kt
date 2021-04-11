package com.example.news.network

import com.example.news.response.TopHeadlinesResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Add apis here
 */
interface WebService {

    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query("country") country: String?, @Query("apiKey") apiKey: String?): TopHeadlinesResponse
}