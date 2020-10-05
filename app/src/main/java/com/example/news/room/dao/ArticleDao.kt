package com.example.news.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.news.room.entities.Article

@Dao
interface ArticleDao {

    @Transaction
    fun replaceDBTopHeadlines(articles: List<Article>?) {
        clearDBTopHeadlines()
        saveDBTopHeadlines(articles)
    }

    @Query("Select * from articles")
    fun getDBTopHeadlines(): LiveData<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveDBTopHeadlines(articles: List<Article>?)

    @Query("Delete from articles")
    fun clearDBTopHeadlines()
}