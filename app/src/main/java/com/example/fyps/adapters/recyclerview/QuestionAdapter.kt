package com.example.fyps.adapters.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.fyps.R
import com.google.firebase.firestore.FirebaseFirestore

class QuestionAdapter(private var questions: List<String>) : RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {

    class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val questionText: TextView = itemView.findViewById(R.id.question)

        fun bind(question: String) {
            questionText.text = question
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.partner_item_question, parent, false)
        return QuestionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.bind(questions[position])
    }

    override fun getItemCount() = minOf(questions.size, 2) // Display a maximum of 2 items

    fun updateQuestions(newQuestions: List<String>) {
        questions = newQuestions
        notifyDataSetChanged()
    }



}
