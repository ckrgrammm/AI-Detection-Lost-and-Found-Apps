package com.example.kleine.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.kleine.R
import com.example.kleine.databinding.FragmentTempRatingBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TempRatingFragment : Fragment() {
    private val TAG = "TempRatingFragment"
    private lateinit var binding: FragmentTempRatingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTempRatingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val materialId = arguments?.getString("documentId")
        val userId = FirebaseAuth.getInstance().currentUser?.uid


        // Check if the user has already posted a comment for this material
        if (userId != "" && materialId != "") {
            val db = FirebaseFirestore.getInstance()
            db.collection("Comments")
                .whereEqualTo("userId", userId)
                .whereEqualTo("materialId", materialId)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    binding.btnShowReviewDialog.isEnabled = querySnapshot.documents.isEmpty()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(requireContext(), "Error checking comments: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }

        val showReviewDialogButton: Button = binding.btnShowReviewDialog
        showReviewDialogButton.setOnClickListener {
            showReviewDialog(materialId)
        }
    }

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
                binding.btnShowReviewDialog.isEnabled = false
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
