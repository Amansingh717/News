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
        private var instance: TopHeadlinesDatabase? = null

        private const val DB_NAME = "topHeadlinesDb"

        fun getInstance(context: Context): TopHeadlinesDatabase? {
            if (instance == null) {
                synchronized(TopHeadlinesDatabase::class.java) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TopHeadlinesDatabase::class.java,
                        DB_NAME
                    ).build()
                }
            }
            return instance
        }
    }
}