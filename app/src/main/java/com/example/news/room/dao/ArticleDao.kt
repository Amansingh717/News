package com.example.news.room.dao

import androidx.room.*
import com.example.news.room.entities.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Transaction
    suspend fun replaceDBTopHeadlines(articles: List<Article>?) {
        clearDBTopHeadlines()
        saveDBTopHeadlines(articles)
    }

    @Query("Select * from articles")
    fun getDBTopHeadlines(): Flow<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveDBTopHeadlines(articles: List<Article>?)

    @Query("Delete from articles")
    suspend fun clearDBTopHeadlines()
}