package com.example.news

import android.app.Application

class NewsApplication : Application() {
    init {
        context = this
    }

    companion object {
        private lateinit var context: NewsApplication
        fun getAppContext(): NewsApplication = context
    }
}