package com.example.fyps.fragments.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fyps.R
import com.example.fyps.adapters.recyclerview.AdminQuestionListAdapter
import com.example.fyps.databinding.FragmentQuestionListBinding
import com.example.fyps.viewmodel.admin.AdminQuestionListViewModel

class AdminQuestionListFragment : Fragment(), AdminQuestionListAdapter.OnQuestionClickListener {

    private lateinit var binding: FragmentQuestionListBinding
    private lateinit var viewModel: AdminQuestionListViewModel
    private lateinit var adapter: AdminQuestionListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentQuestionListBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[AdminQuestionListViewModel::class.java]

        setupRecyclerView()
        observeViewModel()
        setupAddButtonListener()
        fetchCurrentUserDetails()

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.recycleViewQuestion.layoutManager = LinearLayoutManager(context)
        // Initialize the adapter with a mutable list
        adapter = AdminQuestionListAdapter(mutableListOf(), this) // Pass 'this' as the listener
        binding.recycleViewQuestion.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.questions.observe(viewLifecycleOwner) { questions ->
            adapter.updateQuestions(questions)
        }
    }

    private fun setupAddButtonListener() {
        binding.addUserBtn.setOnClickListener {
            // Assuming you have the navigation action defined in your navigation graph
            findNavController().navigate(R.id.action_adminQuestionListFragment_to_adminaddQuestionFragment)
        }
    }


    private fun fetchCurrentUserDetails() {
        viewModel.fetchCurrentUserDetails { userName ->
            // Update the 'tv_setBy' TextView in each item of the RecyclerView
            adapter.setCurrentUserName(userName)
            // Set by: +userName
        }
    }

    override fun onQuestionClick(questionId: String) {
        // Using Safe Args to pass the questionId to EditQuestionFragment
        val action = AdminQuestionListFragmentDirections.actionAdminQuestionListFragmentToEditQuestionFragment(questionId)
        findNavController().navigate(action)
    }

}
