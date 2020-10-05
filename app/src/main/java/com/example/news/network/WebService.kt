package com.example.news.network

import com.example.news.response.TopHeadlinesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Add apis here
 */
interface WebService {

    @GET("top-headlines")
    fun getTopHeadlines(@Query("country") country: String?, @Query("apiKey") apiKey: String?): Call<TopHeadlinesResponse>
}