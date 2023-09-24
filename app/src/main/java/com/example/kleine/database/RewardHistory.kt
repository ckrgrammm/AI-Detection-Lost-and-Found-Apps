package com.example.kleine.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reward_history")
data class RewardHistory(
    @PrimaryKey
    val userDocId: String,
    val redeemedDate: Long, // Store as a timestamp (Long)
    val rewardName: String,
    val rewardDetails: String
)
