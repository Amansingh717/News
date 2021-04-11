package com.example.news.repos

import androidx.annotation.WorkerThread
import com.example.news.ParentRepository
import com.example.news.network.Interactor
import com.example.news.network.NetworkManager
import com.example.news.network.Output
import com.example.news.response.TopHeadlinesResponse
import com.example.news.room.dao.ArticleDao
import com.example.news.room.entities.Article
import kotlinx.coroutines.flow.Flow

class MainRepository(private var dao: ArticleDao) : ParentRepository() {

    /**
     * This method fetches data from WEB API
     */
    suspend fun fetchTopHeadlines(country: String? = null, apiKey: String? = null): Output<TopHeadlinesResponse> {
        return Interactor.handleRequest {
            NetworkManager.getWebService().getTopHeadlines(country = country, apiKey = apiKey)
        }
    }

    /**
     * This method fetches data from ROOM DB
     */
    fun fetchCachedTopHeadlines(): Flow<List<Article>> {
        return dao.getDBTopHeadlines()
    }

    /**
     * This method saves data to ROOM DB
     */
    @WorkerThread
    suspend fun saveTopHeadlinesToCache(articles: List<Article>?) {
        dao.replaceDBTopHeadlines(articles)
    }
}