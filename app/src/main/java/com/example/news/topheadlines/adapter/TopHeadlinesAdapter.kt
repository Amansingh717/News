package com.example.news.topheadlines.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.news.databinding.TopHeadlinesItemBinding
import com.example.news.room.entities.Article

class TopHeadlinesAdapter(private val clickListener: (View?, Article?) -> Unit) : RecyclerView.Adapter<TopHeadlinesAdapter.TopHeadlinesHolder>() {
    class TopHeadlinesHolder(private val itemBinding: TopHeadlinesItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun update(item: Article, clickListener: (View?, Article?) -> Unit) {
            with(itemBinding) {
                modelItem = item
                Glide.with(root.context)
                    .load(item.urlToImage)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView)
                root.setOnClickListener { clickListener(it, item) }
                executePendingBindings()
            }
        }
    }

    private val itemList = arrayListOf<Article>()

    fun updateList(newItemsList: List<Article>) {
        val diffResult = DiffUtil.calculateDiff(
            TopHeadlinesDiffCallback(
                oldItems = itemList,
                newItems = newItemsList as ArrayList<Article>
            )
        )
        this.itemList.clear()
        this.itemList.addAll(newItemsList)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopHeadlinesHolder {
        return TopHeadlinesHolder(TopHeadlinesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TopHeadlinesHolder, position: Int) {
        holder.update(itemList[position], clickListener)
    }

    override fun getItemCount(): Int = itemList.size
}