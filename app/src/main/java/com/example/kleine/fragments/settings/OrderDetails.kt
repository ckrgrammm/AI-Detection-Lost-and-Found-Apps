package com.example.kleine.fragments.settings

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kleine.R
import com.example.kleine.SpacingDecorator.VerticalSpacingItemDecorator
import com.example.kleine.activities.ShoppingActivity
import com.example.kleine.adapters.recyclerview.CartRecyclerAdapter
import com.example.kleine.databinding.FragmentOrderDetailsBinding
import com.example.kleine.model.Address
import com.example.kleine.model.CourseDocument
import com.example.kleine.model.Material
import com.example.kleine.model.Order
import com.example.kleine.resource.Resource
import com.example.kleine.util.Constants.Companion.ORDER_CONFIRM_STATE
import com.example.kleine.util.Constants.Companion.ORDER_Delivered_STATE
import com.example.kleine.util.Constants.Companion.ORDER_PLACED_STATE
import com.example.kleine.util.Constants.Companion.ORDER_SHIPPED_STATE
import com.example.kleine.viewmodel.shopping.ShoppingViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class OrderDetails : Fragment() {
    val TAG = "OrderDetails"
    val args by navArgs<OrderDetailsArgs>()
    private lateinit var binding: FragmentOrderDetailsBinding
    private lateinit var viewModel: ShoppingViewModel
    private lateinit var productsAdapter: CartRecyclerAdapter
    private var material: Material? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (activity as ShoppingActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderDetailsBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Reference to the Firestore database
        val firestore = FirebaseFirestore.getInstance()

        // Initialize the RecyclerView
        setupRecyclerview()

        // Fetch all Materials documents
        firestore.collection("Materials").get().addOnSuccessListener { materialsQuerySnapshot ->
            val allCourseDocuments = mutableListOf<CourseDocument>()
            for (materialDocument in materialsQuerySnapshot) {
                // Fetch Courses sub-collection for each Material document and update the adapter
                materialDocument.reference.collection("Courses").get().addOnSuccessListener { coursesQuerySnapshot ->
                    val courseDocuments = coursesQuerySnapshot.documents.mapNotNull { document ->
                        document.toObject(CourseDocument::class.java)
                    }
                    allCourseDocuments.addAll(courseDocuments)
                    // Update the adapter with the fetched CourseDocuments
                    productsAdapter.submitList(allCourseDocuments)
                }.addOnFailureListener { exception ->
                    // Handle the error appropriately
                    Log.e(TAG, "Error fetching courses", exception)
                }
            }
        }.addOnFailureListener { exception ->
            // Handle the error appropriately
            Log.e(TAG, "Error fetching materials", exception)
        }
    }





    private fun downloadDocument(documentUrl: String) {
        val uri = Uri.parse(documentUrl)
        val request = DownloadManager.Request(uri)
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        val downloadManager = context?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        downloadManager.enqueue(request)
    }

    private fun setupRecyclerview() {
        productsAdapter = CartRecyclerAdapter().apply {
            onDocumentDownloadClick = { documentUrl ->
                downloadDocument(documentUrl)
            }
        }
        binding.rvProducts.apply {
            adapter = productsAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(VerticalSpacingItemDecorator(23))
        }
    }






}