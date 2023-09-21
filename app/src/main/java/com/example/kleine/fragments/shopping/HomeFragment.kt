package com.example.kleine.fragments.shopping

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kleine.R
import com.example.kleine.activities.ShoppingActivity
import com.example.kleine.adapters.recyclerview.MaterialAdapter
import com.example.kleine.adapters.viewpager.HomeViewpagerAdapter
import com.example.kleine.databinding.FragmentHomeBinding
import com.example.kleine.fragments.categories.*
import com.example.kleine.fragments.categories.HomeProductsFragment
import com.example.kleine.viewmodel.shopping.ShoppingViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.firestore.QuerySnapshot
import com.example.kleine.model.Material
import com.example.kleine.resource.Resource



class HomeFragment : Fragment() {
    val TAG = "HomeFragment"
    private lateinit var viewModel: ShoppingViewModel
    private lateinit var binding: FragmentHomeBinding


    private lateinit var materialList: List<Material>
    private lateinit var materialAdapter: MaterialAdapter


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


        binding.frameAdd.setOnClickListener {
//            findNavController().navigate(R.id.action_homeFragment_to_joinPartnerFragment)
        }

        binding.fragmeMicrohpone.setOnClickListener {
            val snackBar = requireActivity().findViewById<CoordinatorLayout>(R.id.snackBar_coordinator)
            Snackbar.make(snackBar,resources.getText(R.string.g_coming_soon),Snackbar.LENGTH_SHORT).show()
        }



        // Initialize RecyclerView and Adapter
        materialAdapter = MaterialAdapter()  // No arguments here
        binding.productListRecycler.adapter = materialAdapter
        binding.productListRecycler.layoutManager = LinearLayoutManager(context)

        // Fetch materials from ViewModel and observe LiveData
        viewModel.getMaterials()  // This will update LiveData in ViewModel

        viewModel.materialsLiveData.observe(viewLifecycleOwner) { resource ->
            when (resource.status) {
                Resource.Status.SUCCESS -> {
                    Log.d(TAG, "Fetched materials successfully. Item count: ${resource.data?.size}")
                    materialAdapter.differ.submitList(resource.data)
                }

                Resource.Status.ERROR -> {
                    Log.e(TAG, "Error fetching materials: ${resource.message}")
                }

                Resource.Status.LOADING -> {
                    Log.d(TAG, "Loading materials")
                }
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