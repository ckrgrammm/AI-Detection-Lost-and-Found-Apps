package com.example.kleine.viewmodel.admin

import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kleine.database.RewardDao
import com.example.kleine.resource.NetworkReceiver
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.launch

class AdminUpdateRewardViewModel(private val appContext: Context, private val rewardDao: RewardDao) : ViewModel(){
    val rewardName = MutableLiveData<String>()
    val rewardDescription = MutableLiveData<String>()
    val rewardPoints = MutableLiveData<String>()
    val redeemLimit = MutableLiveData<String>()
    val imageUrl = MutableLiveData<String>()

    private val db = FirebaseFirestore.getInstance()
    val firebaseStorage = FirebaseStorage.getInstance()
    val updateResult = MutableLiveData<Boolean>()

    private var isNetworkAvailable: Boolean = false

    private val networkReceiver = NetworkReceiver(
        onNetworkAvailable = {
            isNetworkAvailable = true
        },
        onNetworkUnavailable = {
            isNetworkAvailable = false
        }
    )

    init {
        // Register your NetworkReceiver here
        val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        appContext.registerReceiver(networkReceiver, intentFilter)

        // Manually check network availability before initial load
        val connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        isNetworkAvailable = connectivityManager.activeNetworkInfo?.isConnectedOrConnecting == true


    }

    fun loadRewardDetails(rewardName: String?) {
        rewardName?.let {
            db.collection("Rewards")
                .whereEqualTo("rewardName", it)
                .limit(1)  // Limiting to one document since reward names should be unique
                .get()
                .addOnSuccessListener { documents ->
                    if (documents.documents.isNotEmpty()) {
                        val document = documents.documents[0]
                        this.rewardName.value = document.getString("rewardName")
                        rewardDescription.value = document.getString("rewardDescription")
                        rewardPoints.value = document.getLong("rewardPoints")?.toString()
                        redeemLimit.value = document.getLong("redeemLimit")?.toString()
                        imageUrl.value = document.getString("imageUrl")
                    } else {
                        // Handle the case where no document with the given reward name exists
                    }
                }
                .addOnFailureListener { exception ->
                    // Handle any errors here
                }
        }
    }


    fun updateRewardDetailsWithImage(rewardName: String?, selectedImageUri: Uri?) {
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
                    updateRewardDetails(rewardName)
                } else {
                    // Handle failure in image upload
                }
            }
        } else {
            // If no new image was selected, simply update the Firestore document with the existing details
            updateRewardDetails(rewardName)
        }
    }

    private fun updateRewardDetails(currentRewardName: String?) {
        currentRewardName?.let {
            val updatedData = hashMapOf(
                "rewardName" to rewardName.value,
                "rewardDescription" to rewardDescription.value,
                "rewardPoints" to rewardPoints.value?.toInt(),
                "redeemLimit" to redeemLimit.value?.toInt(),
                "imageUrl" to imageUrl.value
            )

            db.collection("Rewards")
                .whereEqualTo("rewardName", it)
                .limit(1)  // Limiting to one document since reward names should be unique
                .get()
                .addOnSuccessListener { documents ->
                    if (documents.documents.isNotEmpty()) {
                        val document = documents.documents[0]
                        document.reference.update(updatedData as Map<String, Any>)
                            .addOnSuccessListener {
                                updateResult.postValue(true)
                            }
                            .addOnFailureListener {
                                updateResult.postValue(false)
                            }
                    } else {
                        // Handle the case where no document with the given reward name exists
                    }
                }
                .addOnFailureListener { exception ->
                    // Handle any errors here
                }
        }
    }


    fun checkRewardNameExists(currentRewardName: String?, newRewardName: String, callback: (Boolean) -> Unit) {
        if(isNetworkAvailable){
            db.collection("Rewards")
                .get()
                .addOnSuccessListener { documents ->
                    val existingNames = documents.mapNotNull { it.getString("rewardName") }.filter { it != currentRewardName }
                    callback(newRewardName in existingNames)
                }
                .addOnFailureListener {
                    updateResult.postValue(false)
                }
        } else {
            // If no connection, check using Room DB
            viewModelScope.launch {
                val count = if (currentRewardName != null) {
                    rewardDao.countByNameExcludingCurrent(newRewardName, currentRewardName)
                } else {
                    rewardDao.countByName(newRewardName)
                }
                callback(count > 0) // If count > 0, the reward name exists in Room DB, else it doesn't
            }
        }

    }

    override fun onCleared() {
        super.onCleared()
        appContext.unregisterReceiver(networkReceiver)
    }

}