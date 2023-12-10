package com.example.fyps.viewmodel.admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fyps.firebaseDatabase.FirebaseDb

class AdminFormViewModelFactory(private val firebaseDatabase: FirebaseDb) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AdminFormViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AdminFormViewModel(firebaseDatabase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}