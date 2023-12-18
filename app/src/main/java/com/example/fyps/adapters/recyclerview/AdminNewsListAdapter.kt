package com.example.fyps.adapters.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fyps.R
import com.example.fyps.model.News

class AdminNewsListAdapter(private var newsList: MutableList<News>,
                           private val onEditNewsClick: (News) -> Unit // Add this lambda function property
) : RecyclerView.Adapter<AdminNewsListAdapter.NewsViewHolder>() {

    fun updateNewsList(newsItems: List<News>) {
        newsList.clear()
        newsList.addAll(newsItems)
        notifyDataSetChanged()
    }

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val previewImageView: ImageView = itemView.findViewById(R.id.previewImage)
        val previewTitleView: TextView = itemView.findViewById(R.id.tv_previewTitle)
        val publishDateView: TextView = itemView.findViewById(R.id.tv_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsItem = newsList[position]
        Glide.with(holder.itemView.context).load(newsItem.previewImage).into(holder.previewImageView)
        holder.previewTitleView.text = newsItem.previewText
        holder.publishDateView.text = newsItem.publishDate

        // Set the click listener for the button
        holder.itemView.findViewById<ImageView>(R.id.cust_detail_btn).setOnClickListener {
            onEditNewsClick(newsItem) // Invoke the lambda function with the news item
        }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }
}
