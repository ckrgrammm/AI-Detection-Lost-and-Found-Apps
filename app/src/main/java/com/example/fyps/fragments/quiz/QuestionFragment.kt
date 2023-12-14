package com.example.fyps.fragments.quiz

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.fyps.databinding.QuestionAddBinding
import com.example.fyps.viewmodel.quiz.QuestionViewModel
class QuestionFragment : Fragment() {
    private lateinit var binding: QuestionAddBinding
    private lateinit var viewModel: QuestionViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = QuestionAddBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(QuestionViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSpinner()
        binding.buttonSubmit.setOnClickListener { handleSubmit() }
    }

    private fun setupSpinner() {
        val items = listOf("Unique Item", "Non-Unique Item")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items)
        (binding.spinnerItemType as AutoCompleteTextView).setAdapter(adapter)
    }

    private fun handleSubmit() {
        val itemType = binding.spinnerItemType.text.toString()
        val question = binding.editQuestion.text.toString().trim()
        val answer = binding.editAnswer.text.toString().trim()

        if (itemType.isNotBlank() && question.isNotBlank() && answer.isNotBlank()) {
            viewModel.addOrUpdateQuestion(
                questionId = null, // null because it's a new question
                itemType = itemType,
                question = question,
                answer = answer,
                onSuccess = {
                    Toast.makeText(context, "Question added successfully", Toast.LENGTH_SHORT).show()
                    findNavController().navigateUp()
                },
                onFailure = { errorMsg ->
                    Toast.makeText(context, "Failed to add question: $errorMsg", Toast.LENGTH_SHORT).show()
                }
            )
        } else {
            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
        }
    }
}
