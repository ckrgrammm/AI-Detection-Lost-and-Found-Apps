package com.example.kleine.fragments.settings

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kleine.R
import com.example.kleine.activities.ShoppingActivity
import com.example.kleine.adapters.recyclerview.AllOrdersAdapter
import com.example.kleine.adapters.recyclerview.MaterialAdapter
import com.example.kleine.databinding.FragmentAllOrdersBinding
import com.example.kleine.model.Enrollment
import com.example.kleine.model.Material
import com.example.kleine.resource.Resource
import com.example.kleine.viewmodel.shopping.ShoppingViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore


class AllOrdersFragment : Fragment() {

    val TAG = "AllOrdersFragment"
    private lateinit var viewModel: ShoppingViewModel
    private lateinit var binding: FragmentAllOrdersBinding
    private lateinit var allOrdersAdapter: AllOrdersAdapter
    private lateinit var materialAdapter: MaterialAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (activity as ShoppingActivity).viewModel
        viewModel.getUserOrders()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAllOrdersBinding.inflate(inflater)
        activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.visibility = View.GONE
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchEnrolledMaterials()
        setupRecyclerView()
        observeAllOrders()
        onCloseClick()
        onItemClick()
        binding.imgCloseOrders.setOnClickListener {
            findNavController().navigateUp()
        }
    }


    private fun fetchEnrolledMaterials() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid

        if (userId != null) {
            val firestore = FirebaseFirestore.getInstance()
            firestore.collection("enrollments")
                .whereEqualTo("userId", userId)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    val enrollments = querySnapshot.documents.mapNotNull { document ->
                        document.toObject(Enrollment::class.java)
                    }
                    fetchMaterialsForEnrollments(enrollments)
                    Log.d(TAG, "Number of enrollments fetched: ${enrollments.size}")

                }
                .addOnFailureListener { exception ->
                    Log.e(TAG, "Error fetching user enrollments", exception)
                    // Handle the error appropriately
                }
        } else {
            // Handle the case where the user is not logged in
        }
    }

    private fun fetchMaterialsForEnrollments(enrollments: List<Enrollment>) {
        val firestore = FirebaseFirestore.getInstance()

        // Filter out empty strings and remove duplicate IDs
        val materialIds = enrollments.map { it.materialId }.filter { it.isNotEmpty() }.distinct()

        // Log the IDs being used in the query for debugging purposes
        Log.d(TAG, "Attempting to fetch materials with IDs: $materialIds")

        // Only proceed with the query if there are valid IDs to search for
        if (materialIds.isNotEmpty()) {
            firestore.collection("Materials")
                .whereIn(FieldPath.documentId(), materialIds) // Updated line
                .get()
                .addOnSuccessListener { querySnapshot ->
                    val materials = querySnapshot.documents.mapNotNull { document ->
                        val material = document.toObject(Material::class.java)
                        material?.id = document.id // Set the id of the Material object
                        material
                    }
                    Log.d(TAG, "Materials fetched successfully: $materials")
                    displayMaterials(materials)
                }
                .addOnFailureListener { exception ->
                    Log.e(TAG, "Error fetching materials", exception)
                    // Handle the error appropriately
                }
        } else {
            Log.w(TAG, "No valid material IDs to fetch")
            // Handle the case where there are no valid material IDs
        }
    }


    private fun displayMaterials(materials: List<Material>) {
        Log.d(TAG, "Displaying materials: ${materials.size}")
        materialAdapter.differ.submitList(materials)
        materialAdapter.notifyDataSetChanged() // Force redraw
    }



    private fun onItemClick() {
        materialAdapter.onItemClick = { material ->
            val bundle = Bundle()
            bundle.putParcelable("material", material)
            findNavController().navigate(R.id.action_allOrdersFragment_to_materialDetailsFragment, bundle)
        }
    }






    private fun onCloseClick() {
        binding.imgCloseOrders.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun observeAllOrders() {
        viewModel.userOrders.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Loading -> {
                    showLoading()
                    return@observe
                }

                is Resource.Success -> {
                    hideLoading()
                    val orders = response.data
                    if (orders!!.isEmpty())
                        binding.apply {
                            imgEmptyBox.visibility = View.VISIBLE
                            imgEmptyBoxTexture.visibility = View.VISIBLE
                            tvEmptyOrders.visibility = View.VISIBLE
                            return@observe
                        }
                    binding.apply {
                        imgEmptyBox.visibility = View.GONE
                        imgEmptyBoxTexture.visibility = View.GONE
                        tvEmptyOrders.visibility = View.GONE
                    }
                    allOrdersAdapter.differ.submitList(orders)
                    return@observe
                }

                is Resource.Error -> {
                    hideLoading()
                    Toast.makeText(
                        activity,
                        resources.getText(R.string.error_occurred),
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.e(TAG, response.message.toString())
                    return@observe
                }
            }
        }
    }

    private fun hideLoading() {
        binding.progressbarAllOrders.visibility = View.GONE

    }

    private fun showLoading() {
        binding.progressbarAllOrders.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        materialAdapter = MaterialAdapter()
        binding.rvAllOrders.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = materialAdapter
        }
    }
}