package com.example.fyps.fragments.shopping

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fyps.R
import com.example.fyps.adapters.viewpager.ViewPager2Images
import com.example.fyps.databinding.FragmentProductPreviewBinding
import com.example.fyps.databinding.RecyclerViewMaterialCommentBinding
import com.example.fyps.model.Comment
import com.example.fyps.model.CommentWithUserDetails
import com.example.fyps.model.Enrollment
import com.example.fyps.model.Material
import com.example.fyps.model.User
import com.example.fyps.viewmodel.comment.CommentViewModel
import com.example.fyps.viewmodel.user.UserViewModel
import com.google.android.material.card.MaterialCardView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.storage.FirebaseStorage
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Date


class MaterialPreviewFragment : Fragment() {
    private var _binding: FragmentProductPreviewBinding? = null
    private val binding get() = _binding!!
    private var material: Material? = null

    private val viewPagerAdapter = ViewPager2Images()

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    //comment use
    private var areCommentsVisible = true
    val userViewModel: UserViewModel by viewModels()
    val commentViewModel: CommentViewModel by viewModels {
        CommentViewModel.CommentViewModelFactory(userViewModel)
    }

    private val adapter by lazy {
        CommentsAdapter(
            listOf(),
            object : CommentsAdapter.AddCommentCallback {
                override fun onAddCommentClicked(commentText: String, materialId: String) {
                    submitComment(commentText, materialId)
                }
            },
            { userId, callback -> loadUserDetails(userId, callback) },
            requireContext(),
            firebaseAuth.currentUser?.uid ?: ""
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductPreviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onEnrollClick()

        material = arguments?.getParcelable("material")

        material?.let { mat ->
            binding.productModel = mat

            // Load the image using Glide
            Glide.with(this).load(mat.imageUrl).into(binding.materialImage)

            // Fetch venue and dateTime details from Firestore
            fetchMaterialDetails(mat.id)

            // Update the labels
            binding.tvColor.text = "Status: " + mat.status
            binding.tvSize.text = "Category: " + mat.category
            binding.tvVenue.text = "Venue: " + mat.venue
            binding.tvDateTime.text = "Date & Time: " + mat.dateTime
        } ?: run {
            Log.e("MaterialPreviewFragment", "Material is null!")
        }

        // Set the adapter to the RecyclerView
        binding.allMaterialComment.materialCommentData.adapter = this.adapter

        val materialId = material?.id
        commentViewModel.fetchComments(materialId.toString())

        commentViewModel.commentsWithUserDetails.observe(viewLifecycleOwner, Observer { commentsWithUserDetails ->
            val sortedComments = commentsWithUserDetails.sortedByDescending {
                it.comment.commentDate
            }

            // Update the adapter with new data
            this.adapter.setData(sortedComments)
        })

        // Initialize comments visibility
        areCommentsVisible = false
        binding.allMaterialComment.materialCommentData.visibility = if (areCommentsVisible) View.VISIBLE else View.GONE
        binding.allMaterialComment.downArrowComment.rotation = if (areCommentsVisible) 180f else 0f

        binding.allMaterialComment.commentTitle.setOnClickListener {
            toggleComments()
        }
        binding.allMaterialComment.downArrowComment.setOnClickListener {
            toggleComments()
        }
    }



    private fun fetchMaterialDetails(materialId: String) {
        FirebaseFirestore.getInstance().collection("Materials").document(materialId)
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val venue = document.getString("venue") ?: "Not specified"
                    val dateTime = document.getString("dateTime") ?: "Not specified"
                    val status = document.getString("status") ?: "Not specified"
                    val category = document.getString("category") ?: "Not specified"
                    val name = document.getString("name") ?: "Not specified"
                    val desc = document.getString("desc") ?: "Not specified"


                    binding.tvProductName.text = name
                    binding.tvProductDescription.text = desc
                    // Update the TextViews with labels
                    binding.tvVenue.text = Html.fromHtml("<b>Found Location</b> <br>$venue")
                    binding.tvDateTime.text = Html.fromHtml("<b>Reported Time</b> <br>$dateTime")
                    binding.tvColor.text = Html.fromHtml("<b>Item Status &nbsp &nbsp&nbsp</b> <br>$status")
                    binding.tvSize.text = Html.fromHtml("<b>Item Category</b> <br>$category")


                }
            }
            .addOnFailureListener { exception ->
                Log.e("MaterialPreviewFragment", "Error fetching material details: ", exception)
            }
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

            // Check if the user has already enrolled
            firestore.collection("enrollments")
                .whereEqualTo("userId", userId)
                .whereEqualTo("materialId", materialId)
                .get()
                .addOnSuccessListener { documents ->
                    if (!documents.isEmpty) {
                        Toast.makeText(context, "You have already claimed this item!", Toast.LENGTH_SHORT).show()
                        return@addOnSuccessListener
                    }

                    // If the user hasn't enrolled yet, proceed with the enrollment process
                    val materialRef = firestore.collection("Materials").document(materialId)

                    firestore.runTransaction { transaction ->
                        // Get the current state of the material
                        val snapshot = transaction.get(materialRef)

                        // Increment the enroll field value
                        val newEnrollValue = snapshot.getLong("claimed")?.plus(1) ?: 1L

                        // Update the enroll field
                        transaction.update(materialRef, "claimed", newEnrollValue)

                        // Create a new Enrollment object
                        val enrollment = Enrollment(userId = userId, materialId = materialId)

                        // Add the enrollment document and return the newEnrollValue for further use if needed
                        transaction.set(firestore.collection("enrollments").document(), enrollment)
                        newEnrollValue
                    }.addOnSuccessListener {
                        Toast.makeText(context, "Successfully claimed this item !", Toast.LENGTH_SHORT).show()

                        // Navigate back to HomeFragment
                        findNavController().navigate(R.id.action_materialDetailsFragment_to_homeFragment)
                    }.addOnFailureListener { exception ->
                        Log.w("MaterialPreviewFragment", "Error adding document", exception)
                        Toast.makeText(context, "Error claiming this item !", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w("MaterialPreviewFragment", "Error checking enrollment", exception)
                    Toast.makeText(context, "Error checking enrollment status!", Toast.LENGTH_SHORT).show()
                }
        }
    }


    private fun toggleComments() {
        areCommentsVisible = !areCommentsVisible
        binding.allMaterialComment.materialCommentData.visibility = if (areCommentsVisible) View.VISIBLE else View.GONE
        binding.allMaterialComment.downArrowComment.animate().rotation(if (areCommentsVisible) 180f else 0f).setDuration(300).start()

        if (areCommentsVisible) {
            binding.nestedScrollView.post {
                binding.nestedScrollView.scrollTo(0, binding.allMaterialComment.materialCommentData.top)
            }
        }
    }




    // Modified submitComment method to include commentText and materialId
    private fun submitComment(commentText: String, materialId: String) {
        if (commentText.isNotEmpty()) {
            val userId = firebaseAuth.currentUser?.uid ?: return

            val newComment = Comment(
                comment = commentText,
                userId = userId,
                commentDate = getCurrentDateTime(),
                materialId = materialId
            )

            firestore.collection("Comments").add(newComment)
                .addOnSuccessListener {
                    loadComments(materialId)
                }
                .addOnFailureListener {
                    Toast.makeText(context, "Failed to add comment", Toast.LENGTH_SHORT).show()
                }
        } else {
            Toast.makeText(context, "Please enter a comment", Toast.LENGTH_SHORT).show()
        }
    }


    private fun loadComments(materialId: String) {
        firestore.collection("Comments")
            .whereEqualTo("materialId", materialId)
            .orderBy("commentDate", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { queryDocumentSnapshots ->
                val commentsList = mutableListOf<CommentWithUserDetails>()
                for (docSnapshot in queryDocumentSnapshots) {
                    try {
                        // Assuming the 'comment' field in the document is actually a String and not a Comment object.
                        val comment = docSnapshot.getString("comment") ?: ""
                        val commentDate = docSnapshot.getString("commentDate") ?: ""
                        val replyComment = docSnapshot.getString("replyComment")
                        val replyDate = docSnapshot.getString("replyDate")
                        val replyPartnerId = docSnapshot.getString("replyPartnerId")
                        val userId = docSnapshot.getString("userId") ?: ""
                        val rating = docSnapshot.getLong("rating") ?: 0
                        val userName = docSnapshot.getString("userName")
                        val userImage = docSnapshot.getString("userImage")
                        val adminName = docSnapshot.getString("adminName")
                        val adminImage = docSnapshot.getString("adminImage")

                        val commentObj = Comment(
                            id = docSnapshot.id,
                            comment = comment,
                            commentDate = commentDate,
                            materialId = materialId,
                            replyComment = replyComment,
                            replyDate = replyDate,
                            replyPartnerId = replyPartnerId,
                            userId = userId,
                            rating = rating
                        )

                        val commentWithUserDetails = CommentWithUserDetails(
                            comment = commentObj,
                            userName = userName,
                            userImage = userImage,
                            adminName = adminName,
                            adminImage = adminImage
                        )

                        commentsList.add(commentWithUserDetails)
                    } catch (e: Exception) {
                        Log.e("MaterialPreviewFragment", "Error parsing comment", e)
                    }
                }
                updateCommentsAdapter(commentsList)
            }
            .addOnFailureListener { exception ->
                Log.e("MaterialPreviewFragment", "Error getting comments", exception)
            }
    }

    private fun updateCommentsAdapter(comments: List<CommentWithUserDetails>) {
        val adapter = binding.allMaterialComment.materialCommentData.adapter as? CommentsAdapter
        adapter?.setData(comments)
    }

    private fun loadUserDetails(userId: String, callback: (User?) -> Unit) {
        firestore.collection("users").document(userId).get()
            .addOnSuccessListener { document ->
                val user = document.toObject(User::class.java)
                callback(user)
            }
            .addOnFailureListener {
                Log.e("MaterialPreviewFragment", "Error getting user details", it)
                callback(null)
            }
    }


    private fun getCurrentDateTime(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return dateFormat.format(Date())
    }
    class CommentsAdapter(
        private var commentsWithUserDetails: List<CommentWithUserDetails>,
        private val addCommentCallback: AddCommentCallback,
        private val userDetailLoader: (String, (User?) -> Unit) -> Unit,
        private val context: Context,
        private val currentUserUid: String // Add this parameter


    ) : RecyclerView.Adapter<CommentsAdapter.CommentViewHolder>() {

        interface AddCommentCallback {
            fun onAddCommentClicked(commentText: String, materialId: String)
        }
        inner class CommentViewHolder(val binding: RecyclerViewMaterialCommentBinding) : RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = RecyclerViewMaterialCommentBinding.inflate(layoutInflater, parent, false)
            return CommentViewHolder(binding)
        }

        override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
            val commentWithDetails = commentsWithUserDetails[position]

            val context = holder.itemView.context

            // Check if there are comments available
            if (commentsWithUserDetails.isEmpty()) {
                // Show the "No comments" text if there are no comments
                holder.binding.noCommentsTextView.visibility = View.VISIBLE
                Log.d("CommentsAdapter", "No comments available")
            } else {
                // Hide the "No comments" text if there are comments
                holder.binding.noCommentsTextView.visibility = View.GONE
            }

            // Check if the comment is by the current user
            if (commentWithDetails.comment.userId == currentUserUid) {
                holder.itemView.setOnLongClickListener {
                    showDeleteCommentDialog(commentWithDetails.comment.id)
                    true
                }
            } else {
                // Disable long click for comments not by the current user
                holder.itemView.setOnLongClickListener(null)
                holder.itemView.isLongClickable = false
            }

            // Add Comment Button Click Listener
            holder.binding.addCommentButton.setOnClickListener {
                val commentText = holder.binding.addCommentEditText.text.toString().trim()
                if (commentText.isNotEmpty()) {
                    Log.d("CommentsAdapter", "Add Comment button clicked")
                    addCommentCallback.onAddCommentClicked(commentText, commentWithDetails.comment.materialId)
                }
            }


            // Load user details
            userDetailLoader(commentWithDetails.comment.userId) { user ->
                user?.let {
                    holder.binding.userName.text = "${user.firstName} ${user.lastName}"
                    Glide.with(context)
                        .load(user.imagePath)
                        .placeholder(R.drawable.user)
                        .into(holder.binding.userImage)
                }
            }

            // Set the comment and timestamp
            holder.binding.userComment.text = commentWithDetails.comment.comment
            holder.binding.commentTimestamp.text = commentWithDetails.comment.commentDate

        }


        private fun showDeleteCommentDialog(commentId: String) {
            // Create and show a dialog to confirm deletion
            AlertDialog.Builder(context)
                .setTitle("Delete Comment")
                .setMessage("Are you sure you want to delete this comment?")
                .setPositiveButton("Delete") { dialog, which ->
                    deleteComment(commentId)
                }
                .setNegativeButton("Cancel", null)
                .show()
        }

        private fun deleteComment(commentId: String) {
            // Delete the comment from Firestore
            FirebaseFirestore.getInstance().collection("Comments").document(commentId)
                .delete()
                .addOnSuccessListener {
                    Toast.makeText(context, "Comment deleted successfully", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(context, "Failed to delete comment: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }

        override fun getItemCount(): Int = commentsWithUserDetails.size

        fun setData(newCommentsWithUserDetails: List<CommentWithUserDetails>) {
            this.commentsWithUserDetails = newCommentsWithUserDetails
            notifyDataSetChanged()
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }








}
