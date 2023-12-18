package com.example.fyps.fragments.admin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.fyps.databinding.NewsEditBinding
import com.example.fyps.model.News
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class AdminEditNewsFragment : Fragment() {
    private lateinit var binding: NewsEditBinding
    private val storageReference by lazy { FirebaseStorage.getInstance().reference }
    private lateinit var currentNews: News
    private var newPreviewImageUrl: String? = null
    private var newImageUrls: MutableList<String> = mutableListOf()
    private val firestore: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = NewsEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newsId = AdminEditNewsFragmentArgs.fromBundle(requireArguments()).newsId
        fetchNewsData(newsId)

        binding.buttonSelectPreviewImage.setOnClickListener {
            selectImageFromGallery(REQUEST_CODE_PREVIEW_IMAGE)
        }

        binding.buttonSelectImage.setOnClickListener {
            selectImageFromGallery(REQUEST_CODE_IMAGE)
        }

        binding.buttonSubmit.setOnClickListener {
            submitNewsUpdate()
        }
    }

    private fun fetchNewsData(newsId: String) {
        firestore.collection("News").document(newsId).get()
            .addOnSuccessListener { documentSnapshot ->
                val news = documentSnapshot.toObject(News::class.java)
                news?.let {
                    currentNews = it
                    bindNewsToUI() // Bind the data to UI
                }
            }
            .addOnFailureListener {
                // Handle any errors
                Toast.makeText(context, "Failed to load news data", Toast.LENGTH_SHORT).show()
            }
    }



    private fun bindNewsToUI() {
        binding.editTextPreviewNewsTitle.setText(currentNews.previewText)
        binding.editTextShortDescription.setText(currentNews.shortDesc)
        binding.editTextLongDescription.setText(currentNews.longDesc)
        Glide.with(this).load(currentNews.previewImage).into(binding.imageViewCourseBanner)
        // You might also want to display other images
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
                    newPreviewImageUrl = selectedImageUri.toString()
                    // You might want to update the ImageView here to show the new preview image
                    Glide.with(this).load(newPreviewImageUrl).into(binding.imageViewCourseBanner)
                }
                REQUEST_CODE_IMAGE -> {
                    selectedImageUri?.let { uri ->
                        newImageUrls.add(uri.toString())
                        // Here you might want to update a UI element to show the newly selected image
                    }
                }
            }
        }
    }


    private fun submitNewsUpdate() {
        val title = binding.editTextPreviewNewsTitle.text.toString()
        val shortDesc = binding.editTextShortDescription.text.toString()
        val longDesc = binding.editTextLongDescription.text.toString()

        if (title.isNotEmpty() && shortDesc.isNotEmpty() && longDesc.isNotEmpty()) {
            val updatedNews = News(
                id = currentNews.id,
                previewText = title,
                previewImage = newPreviewImageUrl ?: currentNews.previewImage,
                shortDesc = shortDesc,
                longDesc = longDesc,
                publishDate = currentNews.publishDate,
                imageUrls = if (newImageUrls.isNotEmpty()) newImageUrls else currentNews.imageUrls
            )
            updateNewsInFirebase(updatedNews)
        } else {
            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateNewsInFirebase(updatedNews: News) {
        val db = FirebaseFirestore.getInstance()
        db.collection("News").document(updatedNews.id).set(updatedNews)
            .addOnSuccessListener {
                Toast.makeText(context, "News updated successfully", Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
            }
            .addOnFailureListener { e ->
                Toast.makeText(context, "Error updating news: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    companion object {
        private const val REQUEST_CODE_PREVIEW_IMAGE = 1
        private const val REQUEST_CODE_IMAGE = 2
    }
}
