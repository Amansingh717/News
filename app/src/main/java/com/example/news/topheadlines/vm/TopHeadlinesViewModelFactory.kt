package com.example.news.topheadlines.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.news.repos.MainRepository

class TopHeadlinesViewModelFactory(private val repository: MainRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TopHeadlinesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TopHeadlinesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}