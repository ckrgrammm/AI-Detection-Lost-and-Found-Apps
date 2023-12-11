package com.example.fyps.fragments.shopping

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fyps.R
import com.example.fyps.activities.ShoppingActivity
import com.example.fyps.adapters.recyclerview.CategoriesRecyclerAdapter
import com.example.fyps.adapters.recyclerview.SearchRecyclerAdapter
import com.example.fyps.databinding.FragmentSearchBinding
import com.example.fyps.resource.Resource
import com.example.fyps.viewmodel.shopping.ShoppingViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*

class SearchFragment : Fragment() {
    private val TAG = "SearchFragment"
    private lateinit var binding: FragmentSearchBinding
    private lateinit var inputMethodManger: InputMethodManager
    private lateinit var viewModel: ShoppingViewModel
    private lateinit var categoriesAdapter: CategoriesRecyclerAdapter
    private lateinit var searchAdapter: SearchRecyclerAdapter
    private lateinit var inputMethodManager: InputMethodManager



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        viewModel = (activity as ShoppingActivity).viewModel
        viewModel.getCategories()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSearchRecyclerView()
        showKeyboardAutomatically()
        onHomeClick()
        searchMaterials()
        observeSearchResults()
        onSearchTextClick()
        onCancelTvClick()


        binding.linearSearch.setOnClickListener {
            binding.searchText.requestFocus()
            inputMethodManager.showSoftInput(binding.searchText, InputMethodManager.SHOW_IMPLICIT)
        }


    }



    private fun onCancelTvClick() {
        binding.tvCancel.setOnClickListener {
            searchAdapter.differ.submitList(emptyList())
            binding.searchText.setText("")
            hideCancelTv()
        }
    }

    private fun onSearchTextClick() {
        searchAdapter.onItemClick = { material ->
            val bundle = Bundle()
            bundle.putParcelable("material", material)

            val imm =
                activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm!!.hideSoftInputFromWindow(requireView().windowToken, 0)

            findNavController().navigate(
                R.id.action_searchFragment_to_materialPreviewFragment,
                bundle
            )
        }
    }

    private fun setupSearchRecyclerView() {
        searchAdapter = SearchRecyclerAdapter()
        binding.rvSearch.apply {
            adapter = searchAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun showCancelTv() {
        binding.tvCancel.visibility = View.VISIBLE
    }


    private fun observeSearchResults() {
        viewModel.searchResults.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Loading -> {
                    Log.d(TAG, "Loading")
                }
                is Resource.Success -> {
                    val materials = response.data
                    searchAdapter.differ.submitList(materials)
                    showCancelTv()
                }
                is Resource.Error -> {
                    Log.e(TAG, response.message.toString())
                    showCancelTv()
                }
            }
        })
    }

    var job: Job? = null
    private fun searchMaterials() {
        binding.searchText.addTextChangedListener { query ->
            val queryTrim = query.toString().trim()
            if (queryTrim.isNotEmpty()) {
                val searchQuery = queryTrim.capitalize()
                job?.cancel()
                job = CoroutineScope(Dispatchers.IO).launch {
                    delay(500L)
                    viewModel.searchMaterials(searchQuery)
                }
            } else {
                searchAdapter.differ.submitList(emptyList())
                hideCancelTv()
            }
        }
    }


    private fun showChancelTv() {
        binding.tvCancel.visibility = View.VISIBLE
    }

    private fun hideCancelTv() {
        binding.tvCancel.visibility = View.GONE
    }

    private fun onHomeClick() {
        val btm = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)
        btm?.menu?.getItem(0)?.setOnMenuItemClickListener {
            activity?.onBackPressed()
            true
        }
    }

    private fun showKeyboardAutomatically() {
        inputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        binding.searchText.postDelayed({
            binding.searchText.requestFocus()
            inputMethodManager.showSoftInput(binding.searchText, InputMethodManager.SHOW_IMPLICIT)
        }, 100)
    }


    override fun onDestroy() {
        super.onDestroy()
        if (binding.searchText.isFocused) {
            inputMethodManager.hideSoftInputFromWindow(binding.searchText.windowToken, 0)
        }
    }
    override fun onResume() {
        super.onResume()

        val bottomNav = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav?.visibility = View.VISIBLE
    }

}