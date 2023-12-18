package com.example.fyps.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fyps.adapters.recyclerview.NewsAdapter
import com.example.fyps.databinding.FragmentNewsBinding
import com.example.fyps.model.News
import com.example.fyps.viewmodel.shopping.ShoppingViewModel


class NewsFragment : Fragment() {
    private var _binding: FragmentNewsBinding? = null // Update this to use the correct binding for the Fragment layout
    private val binding get() = _binding!!
    private lateinit var viewModel: ShoppingViewModel

    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false) // Update this to use the correct binding for the Fragment layout
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNewsRecyclerView()
        fetchNews()
    }

    private fun setupNewsRecyclerView() {
        newsAdapter = NewsAdapter(listOf(), requireContext())
        binding.rvNews.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newsAdapter
        }
    }



    private fun fetchNews() {
        // Fetch news data here, for example, from a ViewModel or a static list
        val sampleNews = listOf(
            News("Title 1", "Preview Text 1", "https://example.com/image1.jpg", "Short Desc 1", "Long Desc 1", "Date 1"),
            // Add more sample news
        )
        newsAdapter.updateNewsList(sampleNews)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
