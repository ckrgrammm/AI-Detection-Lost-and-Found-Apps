package com.example.fyps.fragments.quiz

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.fyps.R
import com.example.fyps.databinding.QuestionEditBinding
import com.example.fyps.viewmodel.quiz.QuestionViewModel

class EditQuestionFragment : Fragment() {

    private lateinit var binding: QuestionEditBinding
    private lateinit var viewModel: QuestionViewModel
    private var questionId: String? = null // To be set when editing a question

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = QuestionEditBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[QuestionViewModel::class.java]

        val localQuestionId = arguments?.getString("questionId")
        if (localQuestionId.isNullOrBlank()) {
            Toast.makeText(context, "Question ID is missing", Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
        } else {
            questionId = localQuestionId // Set the questionId for use in the fragment
            setupSpinner()
            setupSubmitButton()
            loadQuestionData(localQuestionId)
        }
        return binding.root
    }


    private fun setupSpinner() {
        val items = listOf("Unique Item", "Non-Unique Item")
        Log.d("EditQuestionFragment", "Spinner Items: $items")
        val adapter = ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, items)
        (binding.spinnerItemCategory as AutoCompleteTextView).setAdapter(adapter)
    }

    private fun setupSubmitButton() {
        binding.buttonUpdate.setOnClickListener {
            handleSubmit()
        }
    }

    private fun handleSubmit() {
        val itemType = binding.spinnerItemCategory.text.toString()
        val questionText = binding.editQuestion.text.toString().trim()
        val answer = binding.editAnswer.text.toString().trim()

        if (itemType.isNotBlank() && questionText.isNotBlank() && answer.isNotBlank()) {
            viewModel.addOrUpdateQuestion(questionId, itemType, questionText, answer,
                onSuccess = {
                    Toast.makeText(context, if (questionId == null) "Question added successfully" else "Question updated successfully", Toast.LENGTH_SHORT).show()
                    findNavController().navigateUp()
                },
                onFailure = { errorMsg ->
                    Toast.makeText(context, "Failed to add/update question: $errorMsg", Toast.LENGTH_SHORT).show()
                }
            )
        } else {
            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadQuestionData(questionId: String) {
        viewModel.getQuestionById(questionId,
            onSuccess = { question ->
                Log.d("EditQuestionFragment", "Question: ${question.question}, Answer: ${question.answer}, Item Type: ${question.itemType}")

                binding.editQuestion.setText(question.question)
                binding.editAnswer.setText(question.answer)

                // Set the spinner based on the collection name
                val itemType = when (question.collectionName) {
                    "UniqueCollection" -> "Unique Item"
                    "NonUniqueCollection" -> "Non-Unique Item"
                    else -> "" // Or any default value
                }
                binding.spinnerItemCategory.setText(itemType, false)

            },
            onFailure = { errorMsg ->
                Toast.makeText(context, "Failed to load question: $errorMsg", Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
            }
        )
    }



}
