package com.example.fyps.fragments.shopping

import android.R
import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.fyps.databinding.MaterialAddBinding
import com.example.fyps.model.Material
import com.example.fyps.viewmodel.material.MaterialViewModel
import com.google.firebase.auth.FirebaseAuth
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class AddMaterialFragment : Fragment() {

    private lateinit var binding: MaterialAddBinding // Corrected binding class name
    private lateinit var viewModel: MaterialViewModel


    private val REQUEST_CODE_IMAGE_PICK = 1
    private val REQUEST_CODE_DOCUMENT_PICK = 2

    private var selectedImageUri: Uri? = null
    private var selectedDocumentUri: Uri? = null
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
        // Retrieve the user's document ID (replace with your actual method)
        val userDocumentId = getUserDocumentId()
        binding.textViewPartnershipID.text = "Partnership ID: $userDocumentId"


        // Set up the OnClickListener for the EditText
        binding.editTextDateTime.setOnClickListener {
            showDateTimePickerDialog()
        }

        // Setup Spinner for Item Category
        val categories = arrayOf("Electronics", "Clothing", "Personal Items", "Other") // Customize as needed
        val adapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, categories)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerItemCategory.adapter = adapter

        binding.buttonSelectImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, REQUEST_CODE_IMAGE_PICK)

            if (selectedImageUri == null) {
                Toast.makeText(requireContext(), "Please select an image", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }

        binding.buttonOpenCamera.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (takePictureIntent.resolveActivity(requireContext().packageManager) != null) {
                startActivityForResult(takePictureIntent, REQUEST_CODE_IMAGE_CAPTURE)
            } else {
                Toast.makeText(requireContext(), "Unable to open camera", Toast.LENGTH_SHORT).show()
            }
        }



        binding.buttonSubmit.setOnClickListener {
            // Validate and get data from UI elements
            val name = binding.editTextItemName.text.toString()
            val description = binding.editTextItemDescription.text.toString()
            val category = binding.spinnerItemCategory.selectedItem.toString()
            val status = binding.textViewStatus.text.toString()

            if (name.isEmpty() || description.isEmpty() || category.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill all required fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val material = Material(
                name = name,
                desc = description,
                category = category,
                status = status,
                partnershipsID = getUserDocumentId()
            )

            // Call ViewModel to add material and optionally upload selected files
            viewModel.addMaterial(material, selectedImageUri, selectedDocumentUri)

            Toast.makeText(requireContext(), "Material submitted successfully", Toast.LENGTH_SHORT).show()

            // Navigate up (assuming you are using Navigation Component)
            findNavController().navigateUp()
        }

    }


    private fun showDateTimePickerDialog() {
        val currentDate = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(requireContext(), { _, year, monthOfYear, dayOfMonth ->
            val selectedDate = Calendar.getInstance()
            selectedDate.set(year, monthOfYear, dayOfMonth)

            val timePickerDialog = TimePickerDialog(requireContext(), { _, hourOfDay, minute ->
                selectedDate.set(Calendar.HOUR_OF_DAY, hourOfDay)
                selectedDate.set(Calendar.MINUTE, minute)

                val dateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault())
                binding.editTextDateTime.setText(dateFormat.format(selectedDate.time))
            }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), true)

            timePickerDialog.show()
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DAY_OF_MONTH))

        datePickerDialog.show()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_IMAGE_PICK -> {
                    selectedImageUri = data?.data

                    // Check MIME type
                    val mimeType = context?.contentResolver?.getType(selectedImageUri!!)
                    if (mimeType != null && (mimeType == "image/jpeg" || mimeType == "image/png")) {
                        binding.imageViewCourseBanner.setImageURI(selectedImageUri)
                    } else {
                        Toast.makeText(context, "Please select a valid image type (JPEG or PNG)", Toast.LENGTH_SHORT).show()
                        selectedImageUri = null // Reset the URI since it's not valid
                    }
                }
                REQUEST_CODE_IMAGE_CAPTURE -> {
                    val imageBitmap = data?.extras?.get("data") as Bitmap
                    // You need to handle this Bitmap. For example, you can display it in an ImageView or save it
                    // For displaying:
                    binding.imageViewCourseBanner.setImageBitmap(imageBitmap)

                    // If you want to save this image or do more processing, you'll need additional code here.
                }
            }
        }
    }




    private fun getUserDocumentId(): String {
        // Initialize Firebase Authentication
        val auth = FirebaseAuth.getInstance()

        // Check if a user is currently authenticated
        val user = auth.currentUser

        // If a user is authenticated, you can retrieve their UID (user ID)
        return user?.uid ?: ""
    }




    companion object {
        private const val REQUEST_CODE_IMAGE_PICK = 1
        private const val REQUEST_CODE_IMAGE_CAPTURE = 3
    }
}




