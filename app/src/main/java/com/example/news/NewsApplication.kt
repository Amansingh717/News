package com.example.news

import android.app.Application
import com.example.news.repos.MainRepository
import com.example.news.room.database.TopHeadlinesDatabase

class NewsApplication : Application() {
    init {
        context = this
    }

    private val database by lazy { TopHeadlinesDatabase.getInstance(this) }
    val mainRepository by lazy { MainRepository(database.articleDao()) }

    companion object {
        private lateinit var context: NewsApplication
        fun getAppContext(): NewsApplication = context
    }
}