package com.example.fyps.viewmodel.admin

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fyps.database.RewardDao

class AdminViewRewardViewModelFactory(
    private val appContext: Context,
    private val rewardDao: RewardDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AdminViewRewardViewModel::class.java)) {
            return AdminViewRewardViewModel(appContext, rewardDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}