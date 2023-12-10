package com.example.fyps.viewmodel.admin

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fyps.model.Status
import com.example.fyps.model.User
import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.firestore.FirebaseFirestore

class AdminDashboardViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()

    // LiveData to hold total claimed, total users, total views
    val totalClaimed: MutableLiveData<Long> = MutableLiveData()
    val totalUsers: MutableLiveData<Long> = MutableLiveData()
    val totalViews: MutableLiveData<Long> = MutableLiveData()
    val userName: MutableLiveData<String> = MutableLiveData()
    val userImageUrl: MutableLiveData<String> = MutableLiveData()
    val adminUsers: MutableLiveData<List<User>> = MutableLiveData()


    fun fetchTotalClaimed() {
        db.collection("Materials")
            .get()
            .addOnSuccessListener { documents ->
                var claimedCount = 0L
                for (document in documents) {
                    claimedCount += document.getLong("enroll") ?: 0
                }
                totalClaimed.postValue(claimedCount)
            }
            .addOnFailureListener { exception ->
                Log.w("AdminDashboardViewModel", "Error getting documents: ", exception)
            }
    }

    fun fetchTotalUsers() {
        db.collectionGroup("users")
            .get()
            .addOnSuccessListener { documents ->
                totalUsers.postValue(documents.size().toLong())
            }
            .addOnFailureListener { exception ->
                Log.w("AdminDashboardViewModel", "Error getting documents: ", exception)
            }
    }

    fun fetchTotalViews() {
        db.collection("Materials")
            .get()
            .addOnSuccessListener { documents ->
                var viewCount = 0L
                for (document in documents) {
                    viewCount += document.getLong("view") ?: 0
                }
                totalViews.postValue(viewCount)
            }
            .addOnFailureListener { exception ->
                Log.w("AdminDashboardViewModel", "Error getting documents: ", exception)
            }
    }



    fun fetchCurrentUser() {
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            db.collection("users").document(currentUser.uid).get()
                .addOnSuccessListener { documentSnapshot ->
                    val user = documentSnapshot.toObject(User::class.java)
                    userName.postValue("${user?.firstName} ${user?.lastName}")
                    userImageUrl.postValue(user?.imagePath)
                }
                .addOnFailureListener { e ->
                    Log.w("AdminDashboardViewModel", "Error fetching user data", e)
                }
        }
    }


}
