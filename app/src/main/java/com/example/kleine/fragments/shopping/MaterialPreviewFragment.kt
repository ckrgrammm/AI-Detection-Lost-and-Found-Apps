package com.example.kleine.fragments.shopping

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.kleine.R
import com.example.kleine.adapters.viewpager.ViewPager2Images
import com.example.kleine.databinding.FragmentProductPreviewBinding
import com.example.kleine.model.Enrollment
import com.example.kleine.model.Material
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class MaterialPreviewFragment : Fragment() {
    private var _binding: FragmentProductPreviewBinding? = null
    private val binding get() = _binding!!
    private var material: Material? = null

    private val viewPagerAdapter = ViewPager2Images()

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductPreviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onEnrollClick()


        // Set up the ViewPager2
        binding.viewpager2Images.adapter = viewPagerAdapter

        // Retrieve the passed argument
        material = arguments?.getParcelable("material")

        material?.let { mat ->
            binding.productModel = mat

            // Convert the single imageUrl into a list and submit to the adapter
            viewPagerAdapter.differ.submitList(listOf(mat.imageUrl))

        } ?: run {
            Log.e("MaterialPreviewFragment", "Material is null!")
        }

        // Assuming you have one image per material for now
        // If there are multiple images, then update this value accordingly
        // binding.circleIndicator.indicatorItemCount = 1
        // Update: Commented the above line as it might not be available based on the library version.

        binding.viewpager2Images.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                // Update the CircleIndicator's selected position
                // You might need to check if such a method is available in your library version
                // binding.circleIndicator.setSelection(position)
                // Update: Commented the above line as it might not be available based on the library version.
            }
        })
    }



    private fun onEnrollClick() {
        binding.btnEnroll.setOnClickListener {
            Log.d("MaterialPreviewFragment", "Button Clicked")

            // Get the current user ID
            val userId = firebaseAuth.currentUser?.uid ?: run {
                Toast.makeText(context, "User not logged in!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Get the selected material ID
            val materialId = material?.id ?: run {
                Toast.makeText(context, "Material ID is null!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Create a new Enrollment object
            val enrollment = Enrollment(userId = userId, materialId = materialId)

            // Save the enrollment to Firebase Firestore
            firestore.collection("enrollments").add(enrollment)
                .addOnSuccessListener {
                    Toast.makeText(context, "Successfully enrolled in the course!", Toast.LENGTH_SHORT).show()

                    // Navigate back to HomeFragment
                    findNavController().navigateUp()
                }
                .addOnFailureListener { exception ->
                    Log.w("MaterialPreviewFragment", "Error adding document", exception)
                    Toast.makeText(context, "Error enrolling in the course!", Toast.LENGTH_SHORT).show()
                }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
