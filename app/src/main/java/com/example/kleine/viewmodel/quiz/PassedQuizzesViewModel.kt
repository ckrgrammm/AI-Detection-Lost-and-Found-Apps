package com.example.kleine.viewmodel.quiz

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kleine.model.PassedQuiz
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class PassedQuizzesViewModel : ViewModel() {

    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    private val _passedQuizzes = MutableLiveData<List<PassedQuiz>>()
    val passedQuizzes: LiveData<List<PassedQuiz>> = _passedQuizzes

    init {
        loadPassedQuizzes()
    }
    private fun loadPassedQuizzes() {
        val userId = auth.currentUser?.uid ?: return
        val userDocument = firestore.collection("users").document(userId)

        userDocument.collection("quizHistory").get().addOnSuccessListener { querySnapshot ->
            val deferreds = mutableListOf<Deferred<PassedQuiz?>>()

            for (document in querySnapshot.documents) {
                val timestamp = document.getTimestamp("date")
                val date = timestamp?.toDate()?.toString() ?: ""
                val materialId = document.getString("materialId") ?: ""
                val setId = document.getString("setId") ?: ""
                val score = document.getString("score") ?: ""

                val deferred = CoroutineScope(Dispatchers.IO).async {
                    try {
                        val materialSnapshot = firestore.collection("Materials").document(materialId).get().await()
                        val materialName = materialSnapshot.getString("name") ?: "Material Name Not Found"
                        val setSnapshot = firestore.collection("Materials").document(materialId).collection("Sets").document(setId).get().await()
                        val setName = setSnapshot.getString("setName") ?: "Set Name Not Found"

                        PassedQuiz(
                            userDocumentId = document.id,
                            materialName = materialName,
                            date = date,
                            setName = setName,
                            score = score
                        )
                    } catch (e: Exception) {
                        Log.e("PassedQuizzesViewModel", "Error fetching material or set name", e)
                        null
                    }
                }
                deferreds.add(deferred)
            }

            CoroutineScope(Dispatchers.Main).launch {
                val results = deferreds.awaitAll()
                _passedQuizzes.value = results.filterNotNull()
            }
        }.addOnFailureListener { exception ->
            Log.e("PassedQuizzesViewModel", "Error fetching quiz history", exception)
        }
    }


}



