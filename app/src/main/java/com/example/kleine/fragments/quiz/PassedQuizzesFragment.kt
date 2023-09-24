package com.example.kleine.fragments.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kleine.R
import com.example.kleine.adapters.recyclerview.PassedQuizzesAdapter
import com.example.kleine.databinding.FragmentPassedQuizzesBinding
import com.example.kleine.viewmodel.quiz.PassedQuizzesViewModel

class PassedQuizzesFragment : Fragment() {
    private lateinit var binding: FragmentPassedQuizzesBinding
    private lateinit var viewModel: PassedQuizzesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_passed_quizzes, container, false)
        viewModel = ViewModelProvider(this).get(PassedQuizzesViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = PassedQuizzesAdapter(mutableListOf())
        binding.passedQuizzesRecyclerview.layoutManager = LinearLayoutManager(requireContext())

        binding.passedQuizzesRecyclerview.adapter = adapter

        viewModel.passedQuizzes.observe(viewLifecycleOwner, Observer { quizzes ->
            adapter.updateQuizzes(quizzes)
        })


        return binding.root
    }
}

