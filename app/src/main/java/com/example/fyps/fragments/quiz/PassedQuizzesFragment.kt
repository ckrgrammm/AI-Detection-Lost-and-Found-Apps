package com.example.fyps.fragments.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fyps.R
import com.example.fyps.adapters.recyclerview.PassedQuizzesAdapter
import com.example.fyps.database.HelpDatabase
import com.example.fyps.databinding.FragmentPassedQuizzesBinding
import com.example.fyps.viewmodel.quiz.PassedQuizzesViewModel
import com.example.fyps.viewmodel.quiz.PassedQuizzesViewModelFactory

class PassedQuizzesFragment : Fragment() {
    private lateinit var binding: FragmentPassedQuizzesBinding
    private lateinit var viewModel: PassedQuizzesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_passed_quizzes, container, false)
        // Assuming you have a reference to your QuizHistoryDao and Application
        val application = requireNotNull(this.activity).application
        val quizHistoryDao = HelpDatabase.getDatabase(application).quizHistoryDao()

        val viewModelFactory = PassedQuizzesViewModelFactory(quizHistoryDao, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(PassedQuizzesViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = PassedQuizzesAdapter(mutableListOf())
        binding.passedQuizzesRecyclerview.layoutManager = LinearLayoutManager(requireContext())

        binding.passedQuizzesRecyclerview.adapter = adapter

        viewModel.passedQuizzes.observe(viewLifecycleOwner, Observer { quizzes ->
            if (quizzes.isNullOrEmpty()) {
                binding.textViewNoHistoryMsg.visibility = View.VISIBLE
            } else {
                binding.textViewNoHistoryMsg.visibility = View.GONE
                adapter.updateQuizzes(quizzes)
            }
        })


        return binding.root
    }
}

