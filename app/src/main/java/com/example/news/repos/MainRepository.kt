package com.example.news.repos

import androidx.lifecycle.LiveData
import com.example.news.network.ApiInteractor
import com.example.news.network.NetworkManager
import com.example.news.network.NetworkResponse
import com.example.news.response.TopHeadlinesResponse
import com.example.news.room.dao.ArticleDao
import com.example.news.room.entities.Article
import com.example.news.utility.AppExecutors
import retrofit2.Call

class MainRepository {
    private var dao: ArticleDao? = null

    fun setDao(dao: ArticleDao?) {
        this.dao = dao
    }

    /**
     * This method fetches data from WEB API
     */
    fun fetchTopHeadlines(country: String? = null, apiKey: String? = null): LiveData<NetworkResponse<TopHeadlinesResponse>> {
        return object : ApiInteractor<TopHeadlinesResponse>() {
            override fun createCall(): Call<TopHeadlinesResponse> {
                return NetworkManager.getWebService().getTopHeadlines(country = country, apiKey = apiKey)
            }
        }.getAsLiveData
    }

    /**
     * This method fetches data from ROOM DB
     */
    fun fetchCachedTopHeadlines(): LiveData<List<Article>>? {
        return dao?.getDBTopHeadlines()
    }

    /**
     * This method saves data to ROOM DB
     */
    fun saveTopHeadlinesToCache(articles: List<Article>?) {
        AppExecutors.diskIO().execute {
            dao?.replaceDBTopHeadlines(articles)
        }
    }
}