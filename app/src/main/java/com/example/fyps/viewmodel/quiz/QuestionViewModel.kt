package com.example.fyps.viewmodel.quiz

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fyps.model.Question
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class QuestionViewModel() : ViewModel() {

    private val db = FirebaseFirestore.getInstance()


    fun addOrUpdateQuestion(questionId: String?, itemType: String, question: String, answer: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        val questionData = hashMapOf("question" to question, "answer" to answer)
        val collectionName = if (itemType == "Unique Item") "UniqueCollection" else "NonUniqueCollection"

        val task = if (questionId.isNullOrEmpty()) {
            // Adding a new question
            db.collection(collectionName).add(questionData).addOnSuccessListener { documentReference ->
                val newQuestionId = documentReference.id
                db.collection(collectionName).document(newQuestionId).update("id", newQuestionId)
            }
        } else {
            // Updating an existing question, keeping the same ID
            db.collection(collectionName).document(questionId).set(questionData)
                .addOnSuccessListener {
                    db.collection(collectionName).document(questionId).update("id", questionId)
                }
        }

        task.addOnSuccessListener {
            onSuccess()
        }.addOnFailureListener { e ->
            onFailure(e.message ?: "Unknown error")
        }
    }


    fun getQuestionById(questionId: String, onSuccess: (Question) -> Unit, onFailure: (String) -> Unit) {
        // Try fetching from UniqueCollection
        db.collection("UniqueCollection").document(questionId).get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val question = document.toObject(Question::class.java)?.apply {
                        this.collectionName = "UniqueCollection" // Set collection name here
                    }
                    question?.let { onSuccess(it) }
                } else {
                    // If not in UniqueCollection, try NonUniqueCollection
                    db.collection("NonUniqueCollection").document(questionId).get()
                        .addOnSuccessListener { nonUniqueDoc ->
                            if (nonUniqueDoc.exists()) {
                                val question = nonUniqueDoc.toObject(Question::class.java)?.apply {
                                    this.collectionName = "NonUniqueCollection" // Set collection name here
                                }
                                question?.let { onSuccess(it) }
                            } else {
                                onFailure("Question not found in both collections")
                            }
                        }
                }
            }
            .addOnFailureListener { e ->
                onFailure(e.message ?: "Unknown Firestore error")
            }
    }




}