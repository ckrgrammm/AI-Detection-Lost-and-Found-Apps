package com.example.fyps.adapters.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fyps.databinding.RecyclerviewSearchItemBinding
import com.example.fyps.model.Material

class SearchRecyclerAdapter : RecyclerView.Adapter<SearchRecyclerAdapter.SearchViewHolder>() {
    var onItemClick: ((Material) -> Unit)? = null

    inner class SearchViewHolder(val binding: RecyclerviewSearchItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick?.invoke(differ.currentList[position])
                }
            }
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Material>() {
        override fun areItemsTheSame(oldItem: Material, newItem: Material): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Material, newItem: Material): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            RecyclerviewSearchItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val material = differ.currentList[position]
        holder.binding.tvSearchedWord.text = material.name
        // No need to set the click listener here as it's set in the ViewHolder's init block
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}
