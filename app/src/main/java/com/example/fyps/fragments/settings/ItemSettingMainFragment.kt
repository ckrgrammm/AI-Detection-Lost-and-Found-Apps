package com.example.fyps.fragments.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fyps.R
import com.example.fyps.databinding.FragmentDashboardBinding // Replace with your generated binding class
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ItemSettingMainFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding

    private val db = FirebaseFirestore.getInstance()
    private val mAuth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        fetchTotalViews()
        setupCardViewListeners()
        return _binding!!.root
    }

    private fun setupCardViewListeners() {
        binding?.cvEditItem?.setOnClickListener {
            val action = ItemSettingMainFragmentDirections.actionProfileFragmentToItemSettingMainFragmentToItemSettingFragment("editItem")
            findNavController().navigate(action)
        }

        binding?.cvItemClaimed?.setOnClickListener {
            val action = ItemSettingMainFragmentDirections.actionProfileFragmentToItemSettingMainFragmentToItemSettingFragment("changeStatus")
            findNavController().navigate(action)
        }

    }

    private fun fetchTotalViews() {
        val currentUserId = mAuth.currentUser?.uid ?: return

        db.collection("Materials")
            .whereEqualTo("partnershipsID", currentUserId)
            .addSnapshotListener { snapshots, e ->
                if (e != null) {
                    // Handle any errors here
                    return@addSnapshotListener
                }

                var totalViews = 0
                for (document in snapshots!!) {
                    val views = document.getLong("view")?.toInt() ?: 0
                    totalViews += views
                }
                binding?.totalCalorieSpent?.text = totalViews.toString()
            }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
