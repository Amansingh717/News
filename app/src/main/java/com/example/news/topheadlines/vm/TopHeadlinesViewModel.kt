package com.example.news.topheadlines.vm

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import com.example.news.ParentViewModel
import com.example.news.repos.MainRepository
import com.example.news.response.TopHeadlinesResponse
import com.example.news.room.entities.Article
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * We are using ROOM DB as the single source of truth, data fetched from api first gets save to db and then gets reflected on recycler view
 */
class TopHeadlinesViewModel(private val mainRepo: MainRepository) : ParentViewModel() {
    /*--------------------for DB operations--------------------------*/

    private val topHeadlinesCachedData: MediatorLiveData<List<Article>> = MediatorLiveData()
    fun topHeadlinesCachedData() = topHeadlinesCachedData

    fun getCachedTopHeadlines() {
        viewModelScope.launch {
            mainRepo.fetchCachedTopHeadlines().collect {
                topHeadlinesCachedData.value = it
            }
        }
    }

    private fun saveTopHeadlines(articles: List<Article>?) {
        viewModelScope.launch {
            mainRepo.saveTopHeadlinesToCache(articles)
        }
    }

    /*--------------------for API operations---------------------*/

    private val topHeadlinesResponse: MediatorLiveData<com.example.news.network.Output<TopHeadlinesResponse>> = MediatorLiveData()
    fun topHeadlinesResponse() = topHeadlinesResponse

    fun getTopHeadlines(country: String?, apiKey: String) {
        viewModelScope.launch {
            mainRepo.fetchTopHeadlines(country, apiKey).run {
                when (this) {
                    is com.example.news.network.Output.Success -> {
                        topHeadlinesResponse.value = this
                        saveTopHeadlines(this.data.articles)
                    }
                    else -> {
                        //Handle error here
                    }
                }
            }
        }
    }
}