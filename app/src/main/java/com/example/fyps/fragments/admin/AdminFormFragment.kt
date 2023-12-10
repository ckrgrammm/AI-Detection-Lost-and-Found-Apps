package com.example.fyps.fragments.admin

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.fyps.resource.Resource
import com.example.fyps.databinding.FragmentAdminFormBinding
import com.example.fyps.firebaseDatabase.FirebaseDb
import com.example.fyps.viewmodel.admin.AdminFormViewModel
import com.example.fyps.viewmodel.admin.AdminFormViewModelFactory
import java.io.ByteArrayOutputStream

class AdminFormFragment : Fragment() {

    private var _binding: FragmentAdminFormBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AdminFormViewModel

    private val imageRequestCode = 1
    private var imageArray: ByteArray? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAdminFormBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, AdminFormViewModelFactory(FirebaseDb())).get(AdminFormViewModel::class.java)

        binding.uploadButton.setOnClickListener {
            pickImageFromGallery()
        }

        binding.createButton.setOnClickListener {
            createAdmin()
        }

        observeAdminCreationStatus()
        return binding.root
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, imageRequestCode)
    }

    private fun createAdmin() {
        val firstName = binding.eTextFirst.text.toString().trim()
        val lastName = binding.eTextLast.text.toString().trim()
        val email = binding.eTextEmail.text.toString().trim()

        if (validateInput(firstName, lastName, email)) {
            imageArray?.let { image ->
                // Upload image and observe the upload status
                viewModel.uploadProfileImage(image) { imageUrl ->
                    viewModel.createAdminAccount(firstName, lastName, email, imageUrl)
                }
            } ?: run {
                viewModel.createAdminAccount(firstName, lastName, email,"")

            }
        } else {
            Toast.makeText(requireContext(), "Please fill in all fields correctly", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validateInput(firstName: String, lastName: String, email: String): Boolean {
        return firstName.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == imageRequestCode && resultCode == Activity.RESULT_OK) {
            data?.data?.let { imageUri ->
                Glide.with(this).load(imageUri).into(binding.profileImg)
                imageArray = compressImage(imageUri)
            }
        }
    }

    private fun observeImageUploadStatus(firstName: String, lastName: String, email: String) {
        viewModel.uploadStatus.observe(viewLifecycleOwner) { status ->
            when (status) {
                is Resource.Loading -> {
                    // Show a loading indicator, e.g., a ProgressBar
                }
                is Resource.Success -> {
                    // Image upload successful, now create admin with the image path
                    val imagePath = status.data ?: ""
                    viewModel.createAdminAccount(firstName, lastName, email, imagePath)
//                    Toast.makeText(requireContext(), "Admin created successfully", Toast.LENGTH_SHORT).show()
//                    findNavController().navigateUp()


                }
                is Resource.Error -> {
                    // Handle the error, show a message to the user
                    Toast.makeText(requireContext(), "Error: ${status.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun observeImageUploadStatus() {
        viewModel.uploadStatus.observe(viewLifecycleOwner) { status ->
            when (status) {
                is Resource.Loading -> {
                    // Show a loading indicator
                }
                is Resource.Success -> {
                    // Image uploaded successfully, admin account creation is handled in `createAdmin`
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), "Error uploading image: ${status.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun observeAdminCreationStatus() {
        viewModel.adminCreationStatus.observe(viewLifecycleOwner) { status ->
            when (status) {
                is Resource.Loading -> {
                    // Show a loading indicator for admin account creation
                }
                is Resource.Success -> {
                    Toast.makeText(requireContext(), "Admin created successfully", Toast.LENGTH_SHORT).show()
                    findNavController().navigateUp()
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), "Error creating admin: ${status.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun compressImage(imageUri: Uri?): ByteArray {
        val imageInBitmap = MediaStore.Images.Media.getBitmap(activity?.contentResolver, imageUri)
        val imageByteArray = ByteArrayOutputStream()
        imageInBitmap.compress(Bitmap.CompressFormat.JPEG, 20, imageByteArray)
        return imageByteArray.toByteArray()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
