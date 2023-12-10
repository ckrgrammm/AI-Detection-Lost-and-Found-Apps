package com.example.fyps.viewmodel.admin

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fyps.model.User
import com.google.firebase.firestore.FirebaseFirestore

class AdminListViewModel : ViewModel() {
    val adminUsers: MutableLiveData<List<User>> = MutableLiveData()
    var deleteUserLiveData: MutableLiveData<User?> = MutableLiveData()

    fun fetchAdminUsers() {
        FirebaseFirestore.getInstance()
            .collection("users")
            .whereEqualTo("status", "ADMINS") // Assuming 'status' is stored as a String
            .get()
            .addOnSuccessListener { documents ->
                val usersList = documents.mapNotNull { it.toObject(User::class.java) }
                adminUsers.postValue(usersList)
            }
            .addOnFailureListener { e ->
                Log.w("AdminListViewModel", "Error fetching admin users", e)
            }
    }

    fun getDeleteUserObservable(): MutableLiveData<User?> {
        return  deleteUserLiveData
    }

}
