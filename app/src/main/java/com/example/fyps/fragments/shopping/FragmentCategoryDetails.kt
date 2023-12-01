package com.example.fyps.fragments.shopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fyps.adapters.recyclerview.MaterialAdapter
import com.example.fyps.databinding.FragmentCategoryDetailBinding
import com.example.fyps.model.Material
import com.example.fyps.viewmodel.material.MaterialViewModel

class FragmentCategoryDetails : Fragment() {

    private var _binding: FragmentCategoryDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var materialViewModel: MaterialViewModel
    private lateinit var materialAdapter: MaterialAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        materialViewModel = ViewModelProvider(requireActivity()).get(MaterialViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryDetailBinding.inflate(inflater, container, false)
        val view = binding.root

        val args = FragmentCategoryDetailsArgs.fromBundle(requireArguments())
        val category = args.category

        // Bind the category to the layout
        binding.productModel = Material(category = category)

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize your RecyclerView adapter
        materialAdapter = MaterialAdapter()

        // Setup RecyclerView
        binding.productListRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = materialAdapter
        }

        val args = FragmentCategoryDetailsArgs.fromBundle(requireArguments())
        val category = args.category

        // Set the title with the category name
        binding.toolbar.title = category

        // Fetch materials by category and observe changes
        materialViewModel.fetchMaterialsByCategory(category)
        materialViewModel.materialList.observe(viewLifecycleOwner) { materialsData ->
            val materials = materialsData.map { materialData ->
                Material(
                    materialData.id,
                    materialData.name,
                    materialData.desc,
                    0, // Provide a default value for pass (you can change this as needed)
                    materialData.rating.toDouble(), // Convert the rating to Double
                    materialData.category,
                    materialData.status,
                    0, // Provide a default value for view (you can change this as needed)
                    0, // Provide a default value for claimed (you can change this as needed)
                    materialData.imageUrl
                )
            }
            materialAdapter.differ.submitList(materials)
            // Hide the progress bar if materialsData is not null
            if (materialsData != null) {
                binding.progressBar.visibility = View.GONE
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
