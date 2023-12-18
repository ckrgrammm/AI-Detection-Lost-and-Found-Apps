package com.example.fyps.fragments.settings

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fyps.R
import com.example.fyps.SpacingDecorator.VerticalSpacingItemDecorator
import com.example.fyps.activities.ChatActivity
import com.example.fyps.activities.ShoppingActivity
import com.example.fyps.adapters.recyclerview.CartRecyclerAdapter
import com.example.fyps.adapters.recyclerview.QuestionAdapter
import com.example.fyps.databinding.FragmentOrderDetailsBinding
import com.example.fyps.model.CourseDocument
import com.example.fyps.model.Material
import com.example.fyps.model.User
import com.example.fyps.viewmodel.shopping.ShoppingViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.random.Random


class OrderDetails : Fragment() {
    val TAG = "OrderDetails"
    val args by navArgs<OrderDetailsArgs>()
    private lateinit var binding: FragmentOrderDetailsBinding
    private lateinit var viewModel: ShoppingViewModel
    private lateinit var productsAdapter: CartRecyclerAdapter
    private lateinit var questionAdapter: QuestionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (activity as ShoppingActivity).viewModel
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
        val materialId = arguments?.getString("materialDocId") ?: ""
        val partnershipID = arguments?.getString("partnershipID") ?: ""
        Log.d("TAG", partnershipID)
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        // Reference to the Firestore database
        val firestore = FirebaseFirestore.getInstance()




        // Initialize your adapter with an empty list
        questionAdapter = QuestionAdapter(emptyList())
        binding.recyQuestion.adapter = questionAdapter
        binding.recyQuestion.layoutManager = LinearLayoutManager(requireContext())

        // Assuming you have the 'isUnique' field as a boolean in your arguments or you get it from somewhere
        val isUnique = arguments?.getBoolean("isUnique") ?:"" // Replace ?? with actual logic to get isUnique value

            // Initialize the RecyclerView
        setupRecyclerview()
        // Initialize the QuestionAdapter and RecyclerView
        setupRecyclerView()

        // Fetch the user's enrolled courses
        firestore.collection("Materials").document(materialId).collection("Courses")
            .get()
            .addOnSuccessListener { coursesQuerySnapshot ->
                val courseDocuments = coursesQuerySnapshot.documents.mapNotNull { document ->
                    document.toObject(CourseDocument::class.java)
                }
                // Update the adapter with the fetched CourseDocuments
                productsAdapter.submitList(courseDocuments)
            }
            .addOnFailureListener { exception ->
                // Handle the error appropriately
                Log.e(TAG, "Error fetching courses", exception)
            }

        firestore.collection("Materials").document(materialId)
            .get()
            .addOnSuccessListener { documentSnapshot ->
                val material = documentSnapshot.toObject(Material::class.java)
                material?.let {
                    fetchQuestions(it.isUnique)
                }
            }


        //rating view
        if (userId != "" && materialId != "") {
            val db = FirebaseFirestore.getInstance()
            db.collection("Comments")
                .whereEqualTo("userId", userId)
                .whereEqualTo("materialId", materialId)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    binding.ratingBtn.isEnabled = querySnapshot.documents.isEmpty()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(requireContext(), "Error checking comments: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }

        val showReviewDialogButton: Button = binding.ratingBtn
        showReviewDialogButton.setOnClickListener {
            showReviewDialog(materialId)
        }

        binding.btnChat.setOnClickListener {
            val firestore = FirebaseFirestore.getInstance()
            val collectionPath = if (isUnique as Boolean) "UniqueCollection" else "NonUniqueCollection"

            firestore.collection(collectionPath)
                .get()
                .addOnSuccessListener { documents ->
                    val questions = documents.mapNotNull { it.getString("question") }
                    val randomQuestion = if (questions.isNotEmpty()) {
                        questions[Random.nextInt(questions.size)]
                    } else {
                        "No questions available"
                    }

                    // Fetch the selected user's document ID and start ChatActivity with the random question
                    val documentReference = firestore.document("users/$userId")
                    documentReference.get()
                        .addOnSuccessListener { documentSnapshot ->
                            if (documentSnapshot.exists()) {
                                val user = documentSnapshot.toObject(User::class.java)
                                val intent = Intent(context, ChatActivity::class.java).apply {
                                    putExtra("userId", partnershipID)
                                    putExtra("userName", user?.firstName)
                                    putExtra("userImagePath", user?.imagePath)
                                    putExtra("randomQuestion", randomQuestion) // Pass the random question to ChatActivity
                                }
                                startActivity(intent)
                            }
                        }
                        .addOnFailureListener { exception ->
                            Log.e("FirestoreQuery", "Error getting documents: $exception")
                            Toast.makeText(context, "Failed to start chat: ${exception.message}", Toast.LENGTH_SHORT).show()
                        }
                }
                .addOnFailureListener { e ->
                    Toast.makeText(context, "Failed to fetch questions: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
                }
        }


    }


    private fun fetchQuestions(isUnique: Boolean) {
        val collectionPath = if (isUnique) "UniqueCollection" else "NonUniqueCollection"
        FirebaseFirestore.getInstance().collection(collectionPath)
            .get()
            .addOnSuccessListener { documents ->
                val newQuestions = documents.mapNotNull { it.getString("question") }
                questionAdapter.updateQuestions(newQuestions)
            }
            .addOnFailureListener { e ->
                Toast.makeText(requireContext(), "Error fetching questions: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
    }




    private fun setupRecyclerView() {
        questionAdapter = QuestionAdapter(emptyList())
        binding.recyQuestion.adapter = questionAdapter
        binding.recyQuestion.layoutManager = LinearLayoutManager(requireContext())
    }



    private fun downloadDocument(documentUrl: String) {
        val uri = Uri.parse(documentUrl)
        val request = DownloadManager.Request(uri)

        // Set the MIME type of the file
        val mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(documentUrl))
        request.setMimeType(mimeType)

        // Set the destination path and file name
        val fileName = "downloaded_file.${MimeTypeMap.getFileExtensionFromUrl(documentUrl)}"
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)

        // Set the notification to be visible and shows the download progress
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)

        val downloadManager = context?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        downloadManager.enqueue(request)
    }


    private fun setupRecyclerview() {
        productsAdapter = CartRecyclerAdapter().apply {
            onDocumentDownloadClick = { documentUrl ->
                downloadDocument(documentUrl)
            }
        }

    }




    //rating view
    private fun showReviewDialog(materialId: String?) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_review, null)
        val dialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .create()

        val ratingBar: RatingBar = dialogView.findViewById(R.id.rating_bar)
        val etReview: EditText = dialogView.findViewById(R.id.et_review)
        val btnSubmit: Button = dialogView.findViewById(R.id.btn_submit)

        btnSubmit.setOnClickListener {
            val rating = ratingBar.rating
            val review = etReview.text.toString()

            // Validate the input
            if (rating == 0f || review.isEmpty()) {
                val errorMessage = buildString {
                    if (rating == 0f) append("• Rating cannot be empty.\n")
                    if (review.isEmpty()) append("• Review cannot be empty.\n")
                }
                Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
            } else {
                binding.ratingBtn.isEnabled = false
                btnSubmit.isEnabled = false
                etReview.isEnabled = false
                ratingBar.isEnabled = false
                // Format the current time as a string
                val currentTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(
                    Date()
                )

                // Get the current user ID
                val userId = FirebaseAuth.getInstance().currentUser?.uid ?: run {
                    Toast.makeText(requireContext(), "Error: User not signed in", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                // Create a Map object with the data to be added to Firestore
                val commentData = mapOf(
                    "comment" to review,
                    "commentDate" to currentTime,
                    "materialId" to materialId,
                    "rating" to rating.toDouble(),
                    "replyComment" to "",
                    "replyDate" to "",
                    "replyPartnerId" to "",
                    "userId" to userId
                )

                // Get a reference to the Firestore database
                val db = FirebaseFirestore.getInstance()

                // Add a new document to the "Comments" collection
                db.collection("Comments")
                    .add(commentData)
                    .addOnSuccessListener { commentRef ->
                        // Comment posted successfully
                        Toast.makeText(requireContext(), "Comment posted successfully", Toast.LENGTH_SHORT).show()

                        // Calculate the new average rating
                        db.collection("Comments")
                            .whereEqualTo("materialId", materialId)
                            .get()
                            .addOnSuccessListener { commentsSnapshot ->
                                val ratings = commentsSnapshot.documents.map { it.getDouble("rating") ?: 0.0 }
                                val averageRating = ratings.sum() / ratings.size

                                if (materialId != null) {
                                    db.collection("Materials")
                                        .document(materialId)
                                        .update("rating", averageRating)
                                        .addOnSuccessListener {
                                            // Average rating updated successfully
                                            Toast.makeText(requireContext(), "Average rating updated successfully", Toast.LENGTH_SHORT).show()
                                        }
                                        .addOnFailureListener { e ->
                                            // Error updating average rating
                                            Toast.makeText(requireContext(), "Error updating average rating: ${e.message}", Toast.LENGTH_SHORT).show()
                                        }
                                }
                            }
                            .addOnFailureListener { e ->
                                // Error calculating average rating
                                Toast.makeText(requireContext(), "Error calculating average rating: ${e.message}", Toast.LENGTH_SHORT).show()
                            }

                        // Close the review dialog
                        dialog.dismiss()
                    }
                    .addOnFailureListener { e ->
                        // Error posting comment
                        Toast.makeText(requireContext(), "Error posting comment: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            }
        }
        dialog.show()
    }

}