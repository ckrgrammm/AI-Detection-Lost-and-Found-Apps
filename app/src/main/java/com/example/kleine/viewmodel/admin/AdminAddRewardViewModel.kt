package com.example.kleine.viewmodel.admin

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kleine.model.Reward
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class AdminAddRewardViewModel : ViewModel() {

    val firebaseStorage = FirebaseStorage.getInstance()
    val firestore = FirebaseFirestore.getInstance()

    val uploadSuccess = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()

    fun saveReward(imageUri: Uri?, rewardName: String, rewardDescription: String, rewardPoints: Int, redeemLimit: Int) {
        if (imageUri != null) {
            val imageRef = firebaseStorage.reference.child("rewards/${System.currentTimeMillis()}.jpg")

            imageRef.putFile(imageUri).addOnSuccessListener {
                imageRef.downloadUrl.addOnSuccessListener { uri ->
                    val imageUrl = uri.toString()
                    saveRewardToFirestore(imageUrl, rewardName, rewardDescription, rewardPoints, redeemLimit)
                }
            }.addOnFailureListener {exception ->
                val errorMsg = "Error uploading image: ${exception.message}"
                Log.e("AdminAddRewardVM", errorMsg)
                errorMessage.value = "Error uploading image."
            }
        } else {
            errorMessage.value = "Please select an image."
        }
    }

    private fun saveRewardToFirestore(imageUrl: String, rewardName: String, rewardDescription: String, rewardPoints: Int, redeemLimit: Int) {
        val reward = hashMapOf(
            "imageUrl" to imageUrl,
            "rewardName" to rewardName,
            "rewardDescription" to rewardDescription,
            "rewardPoints" to rewardPoints,
            "redeemLimit" to redeemLimit,
            "redeemedCount" to 0
        )

        firestore.collection("Rewards").add(reward)
            .addOnSuccessListener {
                uploadSuccess.value = true
            }
            .addOnFailureListener { e ->
                errorMessage.value = "Error adding document: ${e.message}"
            }
    }

}