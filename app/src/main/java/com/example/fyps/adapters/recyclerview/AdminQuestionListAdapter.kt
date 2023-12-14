package com.example.fyps.adapters.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fyps.databinding.RecycleviewQuestionBinding
import com.example.fyps.model.Question

class AdminQuestionListAdapter(
    private var questions: MutableList<Question>,
    private val listener: OnQuestionClickListener
) : RecyclerView.Adapter<AdminQuestionListAdapter.QuestionViewHolder>() {

    private var currentUserName: String = ""

    interface OnQuestionClickListener {
        fun onQuestionClick(questionId: String)
    }

    fun updateQuestions(newQuestions: List<Question>) {
        this.questions.clear()
        this.questions.addAll(newQuestions)
        notifyDataSetChanged() // Notify any registered observers that the data set has changed
    }

    inner class QuestionViewHolder(private val binding: RecycleviewQuestionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(question: Question) {
            binding.tvUniqueOrNonUniqueItem.text = question.question // Display the question
            binding.tvSetBy.text = currentUserName

            binding.custDetailBtn.setOnClickListener {
                listener.onQuestionClick(question.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecycleviewQuestionBinding.inflate(layoutInflater, parent, false)
        return QuestionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val question = questions[position]
        holder.bind(question)
    }

    override fun getItemCount(): Int = questions.size

    fun setCurrentUserName(userName: String) {
        currentUserName = userName
        notifyDataSetChanged() // This will trigger a rebind of the data
    }
}
