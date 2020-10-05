package com.example.news.topheadlines.vm

import androidx.lifecycle.MediatorLiveData
import com.example.news.ParentViewModel
import com.example.news.network.NetworkResponse
import com.example.news.repos.MainRepository
import com.example.news.response.TopHeadlinesResponse
import com.example.news.room.dao.ArticleDao
import com.example.news.room.entities.Article

/**
 * We are using ROOM DB as the single source of truth, data fetched from api first gets save to db and then gets reflected on recycler view
 */
class TopHeadlinesViewModel : ParentViewModel() {
    private val mainRepo by lazy { MainRepository() }

    fun setDao(dao: ArticleDao?) {
        mainRepo.setDao(dao)
    }

    /*--------------------for DB operations--------------------------*/

    private val topHeadlinesCachedData: MediatorLiveData<List<Article>> = MediatorLiveData()
    fun topHeadlinesCachedData() = topHeadlinesCachedData

    fun getCachedTopHeadlines() {
        val listLiveData = mainRepo.fetchCachedTopHeadlines()
        if (listLiveData != null) {
            topHeadlinesCachedData.addSource(listLiveData) {
                topHeadlinesCachedData.value = it
            }
        }
    }

    /*--------------------for API operations---------------------*/

    private val topHeadlinesResponse: MediatorLiveData<TopHeadlinesResponse> = MediatorLiveData()
    fun topHeadlinesResponse() = topHeadlinesResponse

    fun getTopHeadlines(country: String?, apiKey: String) {
        topHeadlinesResponse.addSource(mainRepo.fetchTopHeadlines(country, apiKey)) { response ->
            when (response.status) {
                is NetworkResponse.Status.SUCCESS -> {
                    topHeadlinesResponse.value = response.data
                    mainRepo.saveTopHeadlinesToCache(response.data?.articles)
                }
            }
        }
    }
}