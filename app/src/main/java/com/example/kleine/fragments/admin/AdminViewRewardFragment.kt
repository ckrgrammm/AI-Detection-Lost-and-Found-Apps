package com.example.kleine.fragments.admin

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kleine.R
import com.example.kleine.adapters.recyclerview.RewardAdapter
import com.example.kleine.databinding.FragmentAdminViewRewardBinding
import com.example.kleine.model.Reward
import com.example.kleine.viewmodel.admin.AdminViewRewardViewModel
import com.example.kleine.viewmodel.quiz.PlayViewModel

class AdminViewRewardFragment : Fragment() {

    private lateinit var binding: FragmentAdminViewRewardBinding
    private lateinit var viewModel: AdminViewRewardViewModel
    private lateinit var adapter: RewardAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_admin_view_reward, container, false)
        viewModel = ViewModelProvider(this).get(AdminViewRewardViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner
        // Set LayoutManager for the RecyclerView
        binding.rv.layoutManager = LinearLayoutManager(context)
        adapter = RewardAdapter(mutableListOf()).apply {
            onEditButtonClick = { documentId ->
                val action = AdminViewRewardFragmentDirections.actionAdminViewRewardFragmentToAdminUpdateRewardFragment(documentId)
                findNavController().navigate(action)
            }
            onDeleteButtonClick = { documentId ->
                showDeleteConfirmationDialog(documentId)
            }
        }
        binding.rv.adapter = adapter

        viewModel.rewards.observe(viewLifecycleOwner) { rewards ->
            adapter.updateData(rewards)
        }

        viewModel.deleteResult.observe(viewLifecycleOwner) { success ->
            if (success) {
                Toast.makeText(context, "Reward successfully deleted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Failed to delete reward", Toast.LENGTH_SHORT).show()
            }
        }

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_adminViewRewardFragment_to_adminAddRewardFragment)
        }

        return binding.root
    }

    private fun showDeleteConfirmationDialog(documentId: String) {
        AlertDialog.Builder(requireContext())
            .setTitle("Delete Reward")
            .setMessage("Are you sure you want to delete this reward?")
            .setPositiveButton("Yes") { _, _ ->
                viewModel.deleteReward(documentId)
            }
            .setNegativeButton("No", null)
            .show()
    }
}

