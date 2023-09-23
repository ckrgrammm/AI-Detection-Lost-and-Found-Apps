package com.example.kleine.viewmodel.quiz

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

class SetsViewModel(materialId: String) : ViewModel() {

    private val db = FirebaseFirestore.getInstance()

    private val _sets = MutableLiveData<List<Pair<String, Int>>>()
    val sets: LiveData<List<Pair<String, Int>>>
        get() = _sets

    init {
        fetchSets(materialId)
    }

    private fun fetchSets(materialId: String) {
        db.collection("Materials")
            .document(materialId)
            .collection("Sets")
            .get()
            .addOnSuccessListener { documents ->
                if (documents != null && !documents.isEmpty) {
                    val setPairs = documents.map {
                        Pair(it.id, (it["setName"] as String).split(" - ").last().toInt())
                    }.sortedBy { it.second }
                    _sets.value = setPairs
                } else {
                    // Handle the case where the material doesn't have sets
                    Log.w("Firestore", "No sets exist in material with ID: $materialId")
                }
            }
            .addOnFailureListener { exception ->
                // Handle the failure
                Log.w("Firestore", "Error fetching documents", exception)
            }
    }


    fun addNewSet(materialId: String) {
        // Get the current number of sets in the Sets sub-collection
        db.collection("Materials")
            .document(materialId)
            .collection("Sets")
            .get()
            .addOnSuccessListener { querySnapshot ->
                val currentSetCount = querySnapshot.size()
                val newSetName = "SET - ${currentSetCount + 1}"

                val setMap = hashMapOf(
                    "setName" to newSetName
                )

                // Adding a new set document in the "Sets" sub-collection
                db.collection("Materials")
                    .document(materialId)
                    .collection("Sets")
                    .add(setMap)
                    .addOnSuccessListener { newDocumentReference ->
                        Log.d("Firestore", "New set added with ID: ${newDocumentReference.id}")
                        fetchSets(materialId)  // Refresh the list
                    }
                    .addOnFailureListener { e ->
                        Log.w("Firestore", "Error adding new set", e)
                    }
            }
            .addOnFailureListener { e ->
                Log.w("Firestore", "Error fetching current number of sets", e)
            }
    }

}