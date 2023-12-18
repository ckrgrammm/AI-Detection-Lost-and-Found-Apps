package com.example.fyps.fragments.admin

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fyps.databinding.NewsAddBinding
import com.example.fyps.model.News
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

class AdminAddNewsFragment : Fragment() {

    private lateinit var binding: NewsAddBinding
    private var previewImageUrl: String = ""
    private var imageUrls: MutableList<String> = mutableListOf()

    private val storageReference by lazy { FirebaseStorage.getInstance().reference }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NewsAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSelectPreviewImage.setOnClickListener {
            selectImageFromGallery(REQUEST_CODE_PREVIEW_IMAGE)
        }

        binding.buttonSelectImage.setOnClickListener {
            selectImageFromGallery(REQUEST_CODE_IMAGE)
        }

        binding.buttonSubmit.setOnClickListener {
            submitNews()
        }
    }

    private fun selectImageFromGallery(requestCode: Int) {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, requestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && data != null) {
            val selectedImageUri = data.data
            when (requestCode) {
                REQUEST_CODE_PREVIEW_IMAGE -> {
                    previewImageUrl = selectedImageUri.toString()
                    // You can update an ImageView to show the selected preview image
                }
                REQUEST_CODE_IMAGE -> {
                    imageUrls.add(selectedImageUri.toString())
                    // Update a UI element to show multiple selected images
                }
            }
        }
    }

    private fun submitNews() {
        val title = binding.editTextPreviewNewsTitle.text.toString()
        val shortDesc = binding.editTextShortDescription.text.toString()
        val longDesc = binding.editTextLongDescription.text.toString()

        if (title.isNotEmpty() && shortDesc.isNotEmpty() && longDesc.isNotEmpty() && previewImageUrl.isNotEmpty()) {
            uploadImages(previewImageUrl, imageUrls) { previewUrl, imageUrlsList ->
                val news = News(
                    previewText = title, // Assuming you want to use the title as previewText
                    previewImage = previewUrl,
                    shortDesc = shortDesc,
                    longDesc = longDesc,
                    publishDate = getCurrentDateTime(),
                    imageUrls = imageUrlsList
                )
                uploadNewsToFirebase(news)
            }
        } else {
            Toast.makeText(context, "Please fill all fields and select images", Toast.LENGTH_SHORT).show()
        }
    }

    private fun uploadImages(previewImageUri: String, imageUris: List<String>, callback: (String, List<String>) -> Unit) {
        val uploadTasks = mutableListOf<Task<Uri>>()

        // Upload preview image
        val previewImageRef = storageReference.child("news_images/${UUID.randomUUID()}")
        uploadTasks.add(uploadImageAndGetUri(previewImageUri, previewImageRef))

        // Upload additional images
        imageUris.forEach { imageUri ->
            val imageRef = storageReference.child("news_images/${UUID.randomUUID()}")
            uploadTasks.add(uploadImageAndGetUri(imageUri, imageRef))
        }

        // Handle the completion of all uploads
        Tasks.whenAllSuccess<Uri>(uploadTasks).addOnSuccessListener { uris ->
            val urlStrings = uris.map { it.toString() }
            val previewUrl = urlStrings.first()
            val otherImageUrls = urlStrings.drop(1)
            callback(previewUrl, otherImageUrls)
        }
    }

    // Helper function to upload an image and get its download URI
    private fun uploadImageAndGetUri(imageUriString: String, storageRef: StorageReference): Task<Uri> {
        val imageUri = Uri.parse(imageUriString)
        return storageRef.putFile(imageUri).continueWithTask {
            if (!it.isSuccessful) {
                it.exception?.let { throw it }
            }
            storageRef.downloadUrl
        }
    }




    private fun getCurrentDateTime(): String {
        // Format the current date and time
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return dateFormat.format(Date())
    }

    private fun uploadNewsToFirebase(news: News) {
        val db = FirebaseFirestore.getInstance()
        val newsId = db.collection("News").document().id // Generate a new ID
        news.id = newsId // Set the ID in the News object

        db.collection("News").document(newsId).set(news)
            .addOnSuccessListener {
                Toast.makeText(context, "News added successfully", Toast.LENGTH_SHORT).show()
                Log.d("AdminAddNewsFragment", "News added successfully: $newsId")
                findNavController().navigateUp()
            }
            .addOnFailureListener { e ->
                Toast.makeText(context, "Failed to add news: ${e.message}", Toast.LENGTH_SHORT).show()
                Log.e("AdminAddNewsFragment", "Failed to add news", e)
            }
    }

    companion object {
        private const val REQUEST_CODE_PREVIEW_IMAGE = 1
        private const val REQUEST_CODE_IMAGE = 2
    }


}
