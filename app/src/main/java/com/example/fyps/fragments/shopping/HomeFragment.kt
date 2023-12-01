package com.example.fyps.fragments.shopping

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
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


class HomeFragment : Fragment() {
    val TAG = "HomeFragment"
    private lateinit var viewModel: ShoppingViewModel
    private lateinit var binding: FragmentHomeBinding
    private lateinit var materialAdapter: MaterialAdapter
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

        // Call getMaterials() to fetch materials
        viewModel.getMaterials()

        // Observe the materialsLiveData to get the materials data
        viewModel.materialsLiveData.observe(viewLifecycleOwner, Observer { resource ->
            when (resource) {
                is Resource.Success -> {
                    resource.data?.let { materials ->
                        // Extract unique categories
                        val uniqueCategories = materials.map { it.category }.distinct()
                        binding.recyclerViewMenu.adapter =
                            MenuAdapter(uniqueCategories, requireContext(), this::onCategoryClick)
                    }
                }
                is Resource.Loading -> {
                    // Handle loading state
                }
                is Resource.Error -> {
                    // Handle error state
                }
            }
        })
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
        // Ensure your RecyclerView's ID matches with your layout
        binding.recyclerViewMenu.apply {
            binding.recyclerViewMenu.layoutManager = GridLayoutManager(context, 2)

            binding.recyclerViewNews.layoutManager = LinearLayoutManager(context)

            binding.recyclerViewNews.addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }


        viewModel.getListNews().observe(viewLifecycleOwner, Observer {
            val items: List<News> = it
            binding.recyclerViewNews.adapter = NewsAdapter(items, requireContext())
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