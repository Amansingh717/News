package com.example.news.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.news.room.dao.ArticleDao
import com.example.news.room.entities.Article

@Database(entities = [Article::class], version = 1)
abstract class TopHeadlinesDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao

    companion object {
        @Volatile
        private var INSTANCE: TopHeadlinesDatabase? = null

        private const val DB_NAME = "topHeadlinesDb"

        fun getInstance(context: Context): TopHeadlinesDatabase {
            return INSTANCE ?: synchronized(TopHeadlinesDatabase::class.java) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TopHeadlinesDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}