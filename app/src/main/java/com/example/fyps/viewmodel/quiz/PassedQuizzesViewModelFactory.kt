package com.example.fyps.viewmodel.quiz

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fyps.database.QuizHistoryDao

class PassedQuizzesViewModelFactory(
    private val quizHistoryDao: QuizHistoryDao,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PassedQuizzesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PassedQuizzesViewModel(quizHistoryDao, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
