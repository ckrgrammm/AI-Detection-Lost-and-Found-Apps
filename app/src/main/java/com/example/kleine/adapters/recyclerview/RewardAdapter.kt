package com.example.kleine.adapters.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kleine.R
import com.example.kleine.model.Reward

class RewardAdapter(var rewards: MutableList<Reward>) : RecyclerView.Adapter<RewardAdapter.RewardViewHolder>() {
    var onEditButtonClick: ((String) -> Unit)? = null // Lambda function to handle edit button click
    var onDeleteButtonClick: ((String) -> Unit)? = null

    class RewardViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        // Initialize your views here
        val rewardImage: ImageView = view.findViewById(R.id.rewardImage)
        val rewardNameText: TextView = view.findViewById(R.id.rewardNameText)
        val rewardDescriptionText: TextView = view.findViewById(R.id.rewardDescriptionText)
        val rewardPointsText: TextView = view.findViewById(R.id.rewardPointsText)
        val redeemLimitText: TextView = view.findViewById(R.id.redeemLimitText)
        val redeemedCountText: TextView = view.findViewById(R.id.redeemedCountText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RewardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.admin_view_reward_item, parent, false)
        return RewardViewHolder(view)
    }

    override fun onBindViewHolder(holder: RewardViewHolder, position: Int) {
        val reward = rewards[position]
        // Bind the reward data to your views here
        holder.rewardNameText.text = reward.rewardName
        holder.rewardDescriptionText.text = reward.rewardDescription
        holder.rewardPointsText.text = reward.rewardPoints.toString()
        holder.redeemLimitText.text = reward.redeemLimit.toString()
        holder.redeemedCountText.text = reward.redeemedCount.toString()

        // Load the image from `imageLocation` using Glide
        Glide.with(holder.itemView.context)
            .load(reward.imageUrl) // Here, imageLocation is the URL string
            .into(holder.rewardImage)

        holder.view.findViewById<Button>(R.id.btnEdit).setOnClickListener {
            onEditButtonClick?.invoke(reward.documentId) // Invoke the lambda function when edit is clicked
        }

        holder.view.findViewById<Button>(R.id.btnDelete).setOnClickListener {
            onDeleteButtonClick?.invoke(reward.documentId) // Invoke the lambda function when delete is clicked
        }
    }

    override fun getItemCount() = rewards.size

    fun updateData(newRewards: List<Reward>) {
        rewards.clear()
        rewards.addAll(newRewards)
        notifyDataSetChanged()
    }
}

