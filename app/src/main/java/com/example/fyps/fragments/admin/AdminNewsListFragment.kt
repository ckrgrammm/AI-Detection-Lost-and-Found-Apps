package com.example.fyps.fragments.admin

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fyps.R
import com.example.fyps.adapters.recyclerview.AdminNewsListAdapter
import com.example.fyps.databinding.FragmentAdminNewsListBinding
import com.example.fyps.model.News
import com.google.firebase.firestore.FirebaseFirestore

class AdminNewsListFragment : Fragment() {

    private lateinit var binding: FragmentAdminNewsListBinding
    private lateinit var newsAdapter: AdminNewsListAdapter

    private val firestore by lazy { FirebaseFirestore.getInstance() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAdminNewsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the adapter
        newsAdapter = AdminNewsListAdapter(mutableListOf()) { newsItem ->
            val action = AdminNewsListFragmentDirections.actionAdminNewsListFragmentToAdminEditNewsFragment(newsItem.id)
            findNavController().navigate(action)
        }

        // Setup RecyclerView
        binding.recycleView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newsAdapter
        }

        // Fetch news items from Firestore
        fetchNews()

        // Set up click listener for the Add News button
        binding.addUserBtn.setOnClickListener {
            navigateToAddNewsFragment()
        }
    }

    private fun fetchNews() {
        firestore.collection("News").get()
            .addOnSuccessListener { documents ->
                val newsItems = documents.mapNotNull { it.toObject(News::class.java) }
                newsAdapter.updateNewsList(newsItems) // Update your adapter's list
            }
            .addOnFailureListener { exception ->
                // Handle exception
                Log.e("AdminNewsListFragment", "Error fetching news", exception)
            }
    }
    private fun navigateToAddNewsFragment() {
        findNavController().navigate(R.id.action_adminNewsListFragment_to_adminAddNewsFragment)
    }

    private fun AdminNewsListAdapter.updateNewsList(newsItems: List<News>) {
        // Assuming you have a method in your adapter to update the list
        this.updateNewsList(newsItems) // Assuming submitList is the method in your adapter to update the list
        notifyDataSetChanged()
    }

}

