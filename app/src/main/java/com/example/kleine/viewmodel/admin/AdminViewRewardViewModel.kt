package com.example.kleine.viewmodel.admin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kleine.model.Reward
import com.google.firebase.firestore.FirebaseFirestore

class AdminViewRewardViewModel : ViewModel() {
    val rewards = MutableLiveData<List<Reward>>()
    val deleteResult = MutableLiveData<Boolean>()

    init {
        // Assuming you have a Firestore reference to the rewards collection
        val rewardsCollection = FirebaseFirestore.getInstance().collection("Rewards")

        rewardsCollection.addSnapshotListener { snapshot, exception ->
            if (exception != null || snapshot == null) {
                // Handle the error
                return@addSnapshotListener
            }

            val rewardList = mutableListOf<Reward>()
            for (document in snapshot.documents) {
                val reward = document.toObject(Reward::class.java)
                if (reward != null) {
                    reward.documentId = document.id // Set the documentId field
                    rewardList.add(reward)
                }
            }

            rewards.value = rewardList
        }
    }

    fun deleteReward(documentId: String) {
        val rewardsCollection = FirebaseFirestore.getInstance().collection("Rewards")
        rewardsCollection.document(documentId)
            .delete()
            .addOnSuccessListener {
                // Log success
                deleteResult.value = true
            }
            .addOnFailureListener { e ->
                // Handle failure
                deleteResult.value = false
            }
    }
}
