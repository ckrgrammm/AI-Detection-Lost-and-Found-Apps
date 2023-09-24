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

class OrderDetails : Fragment() {
    val TAG = "OrderDetails"
    val args by navArgs<OrderDetailsArgs>()
    private lateinit var binding: FragmentOrderDetailsBinding
    private lateinit var viewModel: ShoppingViewModel
    private lateinit var productsAdapter: CartRecyclerAdapter
    private lateinit var courseDocument: CourseDocument


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (activity as ShoppingActivity).viewModel
//        viewModel.getOrderAddressAndProducts(args.order)
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

        // Retrieve the Order object if passed
        val order = arguments?.getParcelable<Order>("order")
        // Use the order object as needed...




        // Retrieve the CourseDocument object if passed
        val courseDocument = arguments?.getParcelable<CourseDocument>("courseDocument")

        // Setup Download Click Listener
        val downloadImageView: ImageView = view.findViewById(R.id.tv_quantity)
        downloadImageView.setOnClickListener {
            // Check if courseDocument is not null before accessing its properties
            if (courseDocument != null) {
                downloadDocument(courseDocument.documentUrl)
            } else {
                // Handle the case where courseDocument is null
                Toast.makeText(context, "Document URL is not available", Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun downloadDocument(documentUrl: String) {
        val uri = Uri.parse(documentUrl)
        val request = DownloadManager.Request(uri)
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        val downloadManager = context?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        downloadManager.enqueue(request)
    }

    private fun onCloseImageClick() {
        binding.imgCloseOrder.setOnClickListener {
            findNavController().navigateUp()
        }
    }


    private fun hideProductsLoading() {
        binding.apply {
            progressbarOrder.visibility = View.GONE
            rvProducts.visibility = View.VISIBLE
            tvProducts.visibility = View.VISIBLE
            linear.visibility = View.VISIBLE
            line1.visibility = View.VISIBLE
        }
    }

    private fun showProductsLoading() {
        binding.apply {
            progressbarOrder.visibility = View.VISIBLE
            rvProducts.visibility = View.INVISIBLE
            tvProducts.visibility = View.INVISIBLE
            linear.visibility = View.INVISIBLE
            line1.visibility = View.INVISIBLE
        }
    }

    private fun setupRecyclerview() {
        productsAdapter = CartRecyclerAdapter("From Order Detail")
        binding.rvProducts.apply {
            adapter = productsAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(VerticalSpacingItemDecorator(23))
        }
    }



    private fun hideAddressLoading() {
        binding.apply {
            progressbarOrder.visibility = View.GONE
            stepView.visibility = View.VISIBLE
            tvShoppingAddresses.visibility = View.VISIBLE
            linearAddress.visibility = View.VISIBLE
        }
    }

    private fun showAddressLoading() {
        binding.apply {
            binding.apply {
                progressbarOrder.visibility = View.VISIBLE
                stepView.visibility = View.INVISIBLE
                tvShoppingAddresses.visibility = View.INVISIBLE
                linearAddress.visibility = View.INVISIBLE
            }
        }
    }


}