package com.example.kleine.fragments.shopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kleine.databinding.FragmentProductPreviewBinding
import com.example.kleine.model.Material

class MaterialPreviewFragment : Fragment() {
    private var _binding: FragmentProductPreviewBinding? = null
    private val binding get() = _binding!!
    private var material: Material? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductPreviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve the passed argument
        material = arguments?.getParcelable("material")

        // Check if the material is not null
        if (material != null) {
            // Bind the material data to the views using data binding
            binding.productModel = material
        } else {
            // Handle the case where material is null
            // For instance, you can show a message to the user or navigate back
            fragmentManager?.popBackStack()

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
