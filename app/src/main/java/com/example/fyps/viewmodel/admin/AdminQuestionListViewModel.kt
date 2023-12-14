package com.example.fyps.viewmodel.admin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fyps.model.Question
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class AdminQuestionListViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()

    private val _questions = MutableLiveData<List<Question>>()
    val questions: LiveData<List<Question>> = _questions

    init {
        fetchQuestions()
    }

    private fun fetchQuestions() {
        val uniqueQuestionsTask = db.collection("UniqueCollection").get()
        val nonUniqueQuestionsTask = db.collection("NonUniqueCollection").get()

        Tasks.whenAllSuccess<QuerySnapshot>(uniqueQuestionsTask, nonUniqueQuestionsTask)
            .addOnSuccessListener { results ->
                val allQuestions = mutableListOf<Question>()

                results.forEachIndexed { index, querySnapshot ->
                    val collectionName = if (index == 0) "UniqueCollection" else "NonUniqueCollection"
                    querySnapshot.documents.forEach { document ->
                        val question = document.toObject(Question::class.java)?.apply {
                            this.collectionName = collectionName
                        }
                        question?.let { allQuestions.add(it) }
                    }
                }

                _questions.postValue(allQuestions)
            }
            .addOnFailureListener {
                // Handle the error
            }
    }


    fun fetchCurrentUserDetails(onResult: (String) -> Unit) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            db.collection("users").document(userId).get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        val firstName = document.getString("firstName") ?: ""
                        val lastName = document.getString("lastName") ?: ""
                        val fullName = "$firstName $lastName"
                        onResult(fullName)
                    }
                }
                .addOnFailureListener {
                    onResult("Unknown")
                }
        } else {
            onResult("Unknown")
        }
    }
}