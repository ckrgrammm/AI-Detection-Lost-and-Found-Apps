package com.example.fyps.fragments.partnership

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.fyps.R
import com.example.fyps.activities.CameraActivity
import com.example.fyps.databinding.MaterialEditBinding
import com.example.fyps.model.Material
import com.example.fyps.viewmodel.material.MaterialViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class EditMaterialFragment : Fragment() {

    private lateinit var binding: MaterialEditBinding
    private lateinit var viewModel: MaterialViewModel
    private var materialId: String? = null
    private var selectedImageUri: Uri? = null
    private val REQUEST_CODE_IMAGE_PICK = 1
    private val REQUEST_CODE_CAMERA_ACTIVITY = 100
    private var currentImageUrl: String? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = MaterialEditBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(MaterialViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        materialId = arguments?.getString("materialId")

        if (materialId == null) {
            Toast.makeText(context, "Error: Material ID not provided", Toast.LENGTH_SHORT).show()
            return
        }

        val buttonDelete = view.findViewById<Button>(R.id.buttonDelete)
        buttonDelete.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.lightRed))

        binding.buttonDelete.setOnClickListener {
            deleteMaterial()
        }

        setupCategorySpinner()
        setupDateTimePicker()
        setupButtonListeners()

        fetchMaterialDetails()
    }

    private fun fetchMaterialDetails() {
        FirebaseFirestore.getInstance().collection("Materials").document(materialId!!)
            .get().addOnSuccessListener { document ->
                if (document.exists()) {
                    val material = document.toObject(Material::class.java)
                    material?.let {
                        binding.editTextItemName.setText(it.name)
                        binding.editTextItemDescription.setText(it.desc)
                        binding.spinnerItemCategory.setText(it.category, false)
                        binding.editTextDateTime.setText(it.dateTime)
                        binding.editTextItemVenue.setText(it.venue)
                        currentImageUrl = it.imageUrl // Store current image URL

                        // Use Glide to load the image
                        if (isAdded) {
                            Glide.with(this@EditMaterialFragment)
                                .load(it.imageUrl)
                                .into(binding.imageViewCourseBanner)
                        }

                    }
                }
            }.addOnFailureListener {
                Toast.makeText(context, "Failed to fetch material details", Toast.LENGTH_SHORT).show()
            }
    }


    private fun setupDateTimePicker() {
        binding.editTextDateTime.setOnClickListener {
            showDateTimePickerDialog()
        }
    }

    private fun setupCategorySpinner() {
        val categories = arrayOf("Electronics", "Fashion", "Personal Items", "Sports", "Books", "Fitness", "Stationery", "Other")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, categories)
        (binding.spinnerItemCategory as AutoCompleteTextView).setAdapter(adapter)
    }

    private fun setupButtonListeners() {
        binding.buttonSelectImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, REQUEST_CODE_IMAGE_PICK)
        }

        binding.buttonSubmit.setOnClickListener {
            handleSubmit()
        }


        binding.buttonOpenCamera.setOnClickListener {
            val cameraIntent = Intent(context, CameraActivity::class.java)
            startActivityForResult(cameraIntent, REQUEST_CODE_CAMERA_ACTIVITY)
        }
    }








    private fun handleSubmit() {
        if (materialId.isNullOrBlank()) {
            Toast.makeText(requireContext(), "Error: No valid material ID", Toast.LENGTH_SHORT).show()
            return
        }

        val name = binding.editTextItemName.text.toString().trim()
        val description = binding.editTextItemDescription.text.toString().trim()
        val category = binding.spinnerItemCategory.text.toString().trim()
        val venue = binding.editTextItemVenue.text.toString().trim()
        val dateTime = binding.editTextDateTime.text.toString().trim()

        if (name.isEmpty() || description.isEmpty() || category.isEmpty() || venue.isEmpty() || dateTime.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill all required fields", Toast.LENGTH_SHORT).show()
            return
        }

        // Check if dateTime is empty and set it to current date and time if necessary
        val finalDateTime = if (dateTime.isEmpty()) {
            getCurrentDateTime()
        } else {
            dateTime
        }

        val imageUrl = selectedImageUri?.toString() ?: currentImageUrl // Use current image if no new image selected

        val updatedMaterial = Material(
            id = materialId!!,
            name = name,
            desc = description,
            category = category,
            isUnique = category == "Electronics", // Set isUnique true if category is Electronics
            venue = venue,
            dateTime = finalDateTime,
            status = "Status : Lost",
            partnershipsID = getUserDocumentId(),
            imageUrl = imageUrl ?: "" // Use the selected or current image URL
        )

        viewModel.updateMaterial(updatedMaterial, selectedImageUri)
        Toast.makeText(requireContext(), "Material updated successfully", Toast.LENGTH_SHORT).show()
        findNavController().navigateUp()
    }



    private fun showDateTimePickerDialog() {
        val currentDate = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(requireContext(), { _, year, month, dayOfMonth ->
            val selectedDate = Calendar.getInstance()
            selectedDate.set(year, month, dayOfMonth)
            val timePickerDialog = TimePickerDialog(requireContext(), { _, hour, minute ->
                selectedDate.set(Calendar.HOUR_OF_DAY, hour)
                selectedDate.set(Calendar.MINUTE, minute)
                if (selectedDate.timeInMillis > currentDate.timeInMillis) {
                    Toast.makeText(requireContext(), "Future dates are not allowed", Toast.LENGTH_SHORT).show()
                    binding.editTextDateTime.setText(getCurrentDateTime()) // Use without parameters
                } else {
                    val dateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault())
                    binding.editTextDateTime.setText(dateFormat.format(selectedDate.time))
                }
            }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false)
            timePickerDialog.show()
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DAY_OF_MONTH))

        // Prevent selecting future dates
        datePickerDialog.datePicker.maxDate = currentDate.timeInMillis

        datePickerDialog.show()
    }


    private fun getCurrentDateTime(): String {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }



    private fun getUserDocumentId(): String {
        val auth = FirebaseAuth.getInstance()
        return auth.currentUser?.uid ?: ""
    }
    private fun deleteMaterial() {
        if (materialId != null) {
            FirebaseFirestore.getInstance().collection("Materials").document(materialId!!)
                .delete()
                .addOnSuccessListener {
                    Toast.makeText(context, "Material deleted successfully", Toast.LENGTH_SHORT).show()
                    findNavController().navigateUp() // Navigate back after deletion
                }
                .addOnFailureListener { e ->
                    Toast.makeText(context, "Error deleting material: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        } else {
            Toast.makeText(context, "Error: No Material ID found", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_IMAGE_PICK -> handleImageSelectionResult(data)
                REQUEST_CODE_CAMERA_ACTIVITY -> handleCameraActivityResult(data)
            }
        }
    }

    private fun handleImageSelectionResult(data: Intent?) {
        // Handle image selection from gallery
        selectedImageUri = data?.data
        if (selectedImageUri != null) {
            Glide.with(this)
                .load(selectedImageUri)
                .into(binding.imageViewCourseBanner)
        } else {
            Toast.makeText(context, "Image selection failed.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleCameraActivityResult(data: Intent?) {
        // Handle image capture from camera
        val detectedObjectName = data?.getStringExtra("DetectedObjectName")
        binding.editTextItemName.setText(detectedObjectName)

        val filename = data?.getStringExtra("CapturedImageFilename")
        filename?.let {
            val file = File(requireContext().filesDir, it)
            selectedImageUri = Uri.fromFile(file)
            Glide.with(this)
                .load(selectedImageUri)
                .into(binding.imageViewCourseBanner)
        } ?: Toast.makeText(context, "Camera capture failed.", Toast.LENGTH_SHORT).show()
    }

    // Add these companion object constants if they don't already exist
    companion object {
        private const val REQUEST_CODE_IMAGE_PICK = 1
        private const val REQUEST_CODE_CAMERA_ACTIVITY = 100
    }

}
