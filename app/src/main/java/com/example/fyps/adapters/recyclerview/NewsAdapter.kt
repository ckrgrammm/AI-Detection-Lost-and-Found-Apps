package com.example.fyps.adapters.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fyps.R
import com.example.fyps.databinding.ItemNewsBinding
import com.example.fyps.model.News

class NewsAdapter(private var newsList: List<News>, private val context: Context) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemNewsBinding.inflate(layoutInflater, parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsItem = newsList[position]
        holder.binding.textViewName.text = newsItem.previewText
        holder.binding.textViewDate.text = newsItem.publishDate

        // Use Glide or similar library to load images
        Glide.with(context)
            .load(newsItem.previewImage)
            .into(holder.binding.imageView)
    }

    override fun getItemCount() = newsList.size

    fun updateNewsList(newNewsList: List<News>) {
        newsList = newNewsList
        notifyDataSetChanged()
    }
}
