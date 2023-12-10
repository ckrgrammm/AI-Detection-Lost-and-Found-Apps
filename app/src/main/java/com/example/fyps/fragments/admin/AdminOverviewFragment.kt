package com.example.fyps.fragments.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.fyps.R
import com.example.fyps.databinding.FragmentReportDashboardBinding
import com.example.fyps.viewmodel.admin.AdminDashboardViewModel

class AdminOverviewFragment : Fragment() {
    private lateinit var binding: FragmentReportDashboardBinding
    private lateinit var viewModel: AdminDashboardViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentReportDashboardBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(AdminDashboardViewModel::class.java)
        observeViewModel()
        return binding.root
    }

    private fun observeViewModel() {


        viewModel.userName.observe(viewLifecycleOwner) { name ->
            binding.total.text = "Welcome,\n$name "
        }

        viewModel.userImageUrl.observe(viewLifecycleOwner) { imageUrl ->
            if (!imageUrl.isNullOrEmpty()) {
                Glide.with(this).load(imageUrl).into(binding.profileTV)
            } else {
                binding.profileTV.setImageResource(R.drawable.img_person)
            }
        }



        viewModel.totalClaimed.observe(viewLifecycleOwner) { claimed ->
            // Update UI with total claimed
            binding.claimedcountTV.text = "$claimed"
        }

        viewModel.totalUsers.observe(viewLifecycleOwner) { users ->
            // Update UI with total users
            binding.usercountTV.text = "$users"
        }

        viewModel.totalViews.observe(viewLifecycleOwner) { views ->
            // Update UI with total views
            binding.viewcountTV.text = "$views"
        }

        // Fetch data
        viewModel.fetchTotalClaimed()
        viewModel.fetchTotalUsers()
        viewModel.fetchTotalViews()
        viewModel.fetchCurrentUser()
    }


}




