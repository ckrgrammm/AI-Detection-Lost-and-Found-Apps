package com.example.fyps.fragments.shopping

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fyps.R
import com.example.fyps.activities.ShoppingActivity
import com.example.fyps.adapters.recyclerview.MaterialAdapter
import com.example.fyps.adapters.recyclerview.MenuAdapter
import com.example.fyps.adapters.recyclerview.NewsAdapter
import com.example.fyps.adapters.recyclerview.SearchRecyclerAdapter
import com.example.fyps.database.SharedPreferencesHelper
import com.example.fyps.databinding.FragmentHomeBinding
import com.example.fyps.model.News

import com.example.fyps.viewmodel.shopping.ShoppingViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.example.fyps.resource.Resource
import com.example.fyps.util.PokemonColorUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
    val TAG = "HomeFragment"
    private lateinit var viewModel: ShoppingViewModel
    private lateinit var binding: FragmentHomeBinding
    private lateinit var searchAdapter: SearchRecyclerAdapter
    private lateinit var materialAdapter: MaterialAdapter
    private lateinit var newsAdapter: NewsAdapter


    var job: Job? = null

    private val sharedPreferencesHelper by lazy { SharedPreferencesHelper(requireContext()) }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (activity as ShoppingActivity).viewModel
    }
    

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize RecyclerView for displaying categories
        setupRecyclerView()
        setupSearchRecyclerView()
        setupSearchObserver()
        viewModel.getMaterials()
        setupNewsRecyclerView()

        viewModel.fetchNews()

        // Observe news data
        viewModel.listNews.observe(viewLifecycleOwner, Observer { newsItems ->
            newsAdapter.updateNewsList(newsItems)
        })


        // Observe the materialsLiveData to get the materials data
        viewModel.materialsLiveData.observe(viewLifecycleOwner, Observer { resource ->
            when (resource) {
                is Resource.Success -> {
                    resource.data?.let { materials ->
                        val uniqueCategories = materials.map { it.category }.distinct()
                        val menuAdapter = binding.recyclerViewMenu.adapter as? MenuAdapter
                        menuAdapter?.updateCategories(uniqueCategories)
                    }
                }else ->{

                }
                // Handle other cases...
            }
        })


        binding.searchText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Implement if necessary
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Implement if necessary
            }

            override fun afterTextChanged(s: Editable?) {
                val query = s.toString().trim()
                if (query.isNotEmpty()) {
                    performSearch(query)
                    showSearchResultsView()
                } else {
                    job?.cancel() // Cancel ongoing search
                    hideSearchResultsView()
                    showOriginalContent()
                }
            }


        })
    }


    private fun setupNewsRecyclerView() {
        // Initialize NewsAdapter
        newsAdapter = NewsAdapter(listOf(), requireContext())
        binding.recyclerViewNews.adapter = newsAdapter
        binding.recyclerViewNews.layoutManager = LinearLayoutManager(context)
    }


    private fun setupSearchRecyclerView() {
        searchAdapter = SearchRecyclerAdapter()
        searchAdapter.onItemClick = { material ->
            val action = HomeFragmentDirections.actionHomeFragmentToMaterialDetailsFragment(material)
            findNavController().navigate(action)
        }
        binding.rvSearch.apply {
            adapter = searchAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }


    private fun setupSearchObserver() {
        viewModel.searchResults.observe(viewLifecycleOwner, Observer { resource ->
            val currentQuery = binding.searchText.text.toString().trim()
            if (currentQuery.isNotEmpty()) {
                when (resource) {
                    is Resource.Success -> {
                        resource.data?.let { materials ->
                            searchAdapter.differ.submitList(materials)
                            showSearchResultsView()
                        }
                    }
                    else ->{

                    }
                    // Handle other cases...
                }
            }
        })
    }



    private fun performSearch(query: String) {
        job?.cancel()
        job = CoroutineScope(Dispatchers.IO).launch {
            delay(500L) // Debounce
            if (isActive) { // Check if the coroutine is still active
                viewModel.searchMaterials(query) // Implement this based on your viewModel
            }
        }
    }


    private fun showSearchResultsView() {
        binding.rvSearch.visibility = View.VISIBLE

        hideOtherComponents()
    }

    private fun hideSearchResultsView() {
        binding.rvSearch.visibility = View.GONE

        showOriginalContent()
    }

    private fun hideOtherComponents() {
        // Hide non-search related UI components
        binding.recyclerViewMenu.visibility = View.GONE
        binding.recyclerViewNews.visibility = View.GONE
        binding.linearNews.visibility = View.GONE
    }

    private fun showOriginalContent() {
        // Show original content like RecyclerViews for news and categories
        binding.recyclerViewMenu.visibility = View.VISIBLE
        binding.recyclerViewNews.visibility = View.VISIBLE
        binding.linearNews.visibility = View.VISIBLE

    }



    // Define the onItemClick method
    private fun onCategoryClick(category: String) {
        // Implement what happens when a category is clicked
        // For example, navigate to another fragment or show a toast message
        Log.d(TAG, "Category clicked: $category")
        val action = HomeFragmentDirections.actionHomeFragmentToCategoryDetails(category)
        findNavController().navigate(action)
    }


    private fun setupRecyclerView() {
        // Initialize MenuAdapter and MaterialAdapter
        val menuAdapter = MenuAdapter(mutableListOf(), requireContext(), this::onCategoryClick)
        binding.recyclerViewMenu.adapter = menuAdapter
        binding.recyclerViewMenu.layoutManager = GridLayoutManager(context, 2)

        materialAdapter = MaterialAdapter()
        materialAdapter.onItemClick = { material ->
            val bundle = Bundle()
            bundle.putParcelable("material", material)
            findNavController().navigate(R.id.action_allOrdersFragment_to_materialDetailsFragment, bundle)
        }

        // Setup RecyclerView for news
        binding.recyclerViewNews.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewNews.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        // Observe news data
        viewModel.listNews.observe(viewLifecycleOwner, Observer { newsItems ->
            newsAdapter.updateNewsList(newsItems)
        })
    }






    private fun onItemClick() {
        materialAdapter.onItemClick = { material ->
            if (material != null) {
                val bundle = Bundle()
                bundle.putParcelable("material", material)
                findNavController().navigate(R.id.action_allOrdersFragment_to_materialDetailsFragment, bundle)
            } else {
                Log.e(TAG, "Material object is null")
            }
        }
    }






    override fun onResume() {
        super.onResume()
        val bottomNavigation =
            requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()

    }

}