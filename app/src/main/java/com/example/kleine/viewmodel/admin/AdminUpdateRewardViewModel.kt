package com.example.kleine.viewmodel.admin

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class AdminUpdateRewardViewModel : ViewModel(){
    val rewardName = MutableLiveData<String>()
    val rewardDescription = MutableLiveData<String>()
    val rewardPoints = MutableLiveData<String>()
    val redeemLimit = MutableLiveData<String>()
    val imageUrl = MutableLiveData<String>()

    private val db = FirebaseFirestore.getInstance()
    val firebaseStorage = FirebaseStorage.getInstance()
    val updateResult = MutableLiveData<Boolean>()

    fun loadRewardDetails(documentId: String?) {
        documentId?.let {
            db.collection("Rewards")
                .document(it)
                .get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        rewardName.value = document.getString("rewardName")
                        rewardDescription.value = document.getString("rewardDescription")
                        rewardPoints.value = document.getLong("rewardPoints")?.toString()
                        redeemLimit.value = document.getLong("redeemLimit")?.toString()
                        imageUrl.value = document.getString("imageUrl")
                    } else {
                        // Handle the case where the document does not exist
                    }
                }
                .addOnFailureListener { exception ->
                    // Handle any errors here
                }
        }
    }

    fun updateRewardDetailsWithImage(documentId: String?, selectedImageUri: Uri?) {
        if (selectedImageUri != null) {
            // Create a reference to the location in Firebase Storage where you want to upload the image
            val storageRef = firebaseStorage.reference.child("rewards/${System.currentTimeMillis()}.jpg")

            // Upload the image to Firebase Storage
            val uploadTask = storageRef.putFile(selectedImageUri)

            // After uploading, get the download URL of the uploaded image
            uploadTask.continueWithTask { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        throw it
                    }
                }
                storageRef.downloadUrl
            }.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Set the imageUrl to the download URL of the uploaded image
                    imageUrl.value = task.result.toString()

                    // Update the Firestore document with the new details including the new imageUrl
                    updateRewardDetails(documentId)
                } else {
                    // Handle failure in image upload
                }
            }
        } else {
            // If no new image was selected, simply update the Firestore document with the existing details
            updateRewardDetails(documentId)
        }
    }

    private fun updateRewardDetails(documentId: String?) {
        documentId?.let {
            val updatedData = hashMapOf(
                "rewardName" to rewardName.value,
                "rewardDescription" to rewardDescription.value,
                "rewardPoints" to rewardPoints.value?.toInt(),
                "redeemLimit" to redeemLimit.value?.toInt(),
                "imageUrl" to imageUrl.value
            )

            db.collection("Rewards")
                .document(it)
                .update(updatedData as Map<String, Any>)
                .addOnSuccessListener {
                    updateResult.postValue(true)
                }
                .addOnFailureListener {
                    updateResult.postValue(false)
                }
        }
    }
}