package com.example.fyps.fragments.shopping

import android.R
import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.fyps.databinding.MaterialAddBinding
import com.example.fyps.model.Material
import com.example.fyps.viewmodel.material.MaterialViewModel
import com.google.firebase.auth.FirebaseAuth
import java.text.SimpleDateFormat
import java.util.*
import com.example.fyps.activities.CameraActivity
import java.io.File

class AddMaterialFragment : Fragment() {

    private lateinit var binding: MaterialAddBinding
    private lateinit var viewModel: MaterialViewModel

    private val REQUEST_CODE_IMAGE_PICK = 1
    private val REQUEST_CODE_CAMERA_ACTIVITY = 100

    private var selectedImageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MaterialAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MaterialViewModel::class.java)

        setupDateTimePicker()
        setupCategorySpinner()
        setupButtonListeners()
    }

    private fun setupDateTimePicker() {
        binding.editTextDateTime.setOnClickListener {
            showDateTimePickerDialog()
        }
    }

    private fun setupCategorySpinner() {
        val categories = arrayOf("Electronics", "Clothing", "Personal Items", "Other")
        val adapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, categories)
        val autoCompleteTextView = binding.spinnerItemCategory as AutoCompleteTextView
        autoCompleteTextView.setAdapter(adapter)
    }

    private fun setupButtonListeners() {
        binding.buttonSelectImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, REQUEST_CODE_IMAGE_PICK)
        }

        binding.buttonOpenCamera.setOnClickListener {
            val intent = Intent(context, CameraActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_CAMERA_ACTIVITY)
        }

        binding.buttonSubmit.setOnClickListener {
            handleSubmit()
        }
    }

    private fun handleSubmit() {
        val name = binding.editTextItemName.text.toString()
        val description = binding.editTextItemDescription.text.toString()
        val category = binding.spinnerItemCategory.text.toString()

        if (name.isEmpty() || description.isEmpty() || category.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill all required fields", Toast.LENGTH_SHORT).show()
            return
        }

        val material = Material(
            name = name,
            desc = description,
            category = category,
            status = "Lost",
            partnershipsID = getUserDocumentId()
        )

        viewModel.addMaterial(material, selectedImageUri, null)
        Toast.makeText(requireContext(), "Material submitted successfully", Toast.LENGTH_SHORT).show()
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
                val dateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault())
                binding.editTextDateTime.setText(dateFormat.format(selectedDate.time))
            }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false)
            timePickerDialog.show()
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DAY_OF_MONTH))
        datePickerDialog.show()
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
        selectedImageUri = data?.data
        binding.imageViewCourseBanner.setImageURI(selectedImageUri)
    }

    private fun handleCameraActivityResult(data: Intent?) {
        val detectedObjectName = data?.getStringExtra("DetectedObjectName")
        binding.editTextItemName.setText(detectedObjectName)

        val filename = data?.getStringExtra("CapturedImageFilename")
        filename?.let {
            val file = File(requireContext().filesDir, it)
            selectedImageUri = Uri.fromFile(file)
            binding.imageViewCourseBanner.setImageURI(selectedImageUri)
        }
    }

    private fun getUserDocumentId(): String {
        val auth = FirebaseAuth.getInstance()
        return auth.currentUser?.uid ?: ""
    }

    companion object {
        private const val REQUEST_CODE_IMAGE_PICK = 1
        private const val REQUEST_CODE_CAMERA_ACTIVITY = 100
    }
}
