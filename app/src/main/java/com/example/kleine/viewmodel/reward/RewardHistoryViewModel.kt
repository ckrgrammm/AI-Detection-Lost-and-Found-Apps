package com.example.kleine.viewmodel.reward

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RewardHistoryViewModel : ViewModel() {
    val rewardHistory = MutableLiveData<List<Map<String, Any>>>()
    private val firestore = FirebaseFirestore.getInstance()
    private val userId = FirebaseAuth.getInstance().currentUser?.uid

    init {
        loadRewardHistory()
    }

    private fun loadRewardHistory() {
        userId?.let {
            val rewardHistoryCollection = firestore.collection("users").document(it).collection("rewardHistory")

            rewardHistoryCollection.addSnapshotListener { snapshot, exception ->
                if (exception != null || snapshot == null) {
                    // Handle the error
                    return@addSnapshotListener
                }

                val historyList = mutableListOf<Map<String, Any>>()
                for (document in snapshot.documents) {
                    historyList.add(document.data ?: mapOf())
                }

                rewardHistory.value = historyList
            }
        }
    }
}
