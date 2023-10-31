package com.example.fyps.fragments.reward

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.fyps.R
import com.example.fyps.adapters.recyclerview.RewardHistoryAdapter
import com.example.fyps.database.HelpDatabase
import com.example.fyps.databinding.FragmentRewardHistoryBinding
import com.example.fyps.viewmodel.reward.RewardHistoryViewModel
import com.example.fyps.viewmodel.reward.RewardHistoryViewModelFactory

class RewardHistoryFragment : Fragment() {
    private lateinit var binding: FragmentRewardHistoryBinding
    private lateinit var viewModel: RewardHistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_reward_history, container, false)
        val rewardHistoryDao = HelpDatabase.getDatabase(requireContext()).rewardHistoryDao()
        val appContext = requireActivity().application
        val viewModelFactory = RewardHistoryViewModelFactory(rewardHistoryDao, appContext)
        viewModel = ViewModelProvider(this, viewModelFactory).get(RewardHistoryViewModel::class.java)


        viewModel.rewardHistory.observe(viewLifecycleOwner, Observer { history ->
            if (history.isNullOrEmpty()) {
                binding.textViewNoHistoryMsg.visibility = View.VISIBLE
            } else {
                binding.textViewNoHistoryMsg.visibility = View.GONE
                val adapter = RewardHistoryAdapter(history)
                binding.rewardHistoryRecyclerView.adapter = adapter
            }
        })

        return binding.root
    }
}
