package com.example.news.topheadlines.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.news.room.entities.Article

class TopHeadlinesDiffCallback(private val oldItems: ArrayList<Article>, val newItems: ArrayList<Article>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldItems.size

    override fun getNewListSize(): Int = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val old = oldItems[oldItemPosition]
        val new = newItems[newItemPosition]
        return old.source?.id == new.source?.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val old = oldItems[oldItemPosition]
        val new = newItems[newItemPosition]
        return false
    }
}