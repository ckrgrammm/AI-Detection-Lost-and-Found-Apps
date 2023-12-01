package com.example.fyps.fragments.shopping

import android.annotation.SuppressLint
import android.os.Bundle
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

        // Retrieve the passed argument
        material = arguments?.getParcelable("material")

        material?.let { mat ->
            binding.productModel = mat

            // Load the image using Glide
            Glide.with(this)
                .load(mat.imageUrl)
                .into(binding.materialImage)

        } ?: run {
            Log.e("MaterialPreviewFragment", "Material is null!")
        }

        //comment use
        val adapter = CommentsAdapter(listOf())
        binding.allMaterialComment.materialCommentData.adapter = adapter

        val materialId = material?.id
        commentViewModel.fetchComments(materialId.toString())

        commentViewModel.commentsWithUserDetails.observe(viewLifecycleOwner, Observer { commentsWithUserDetails ->
            val sortedComments = commentsWithUserDetails.sortedByDescending {
                it.comment.commentDate
            }

            adapter.setData(sortedComments)
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



        // Ensuring the view is completely created before accessing its elements
        binding.allMaterialComment.root.post {
            val addCommentButton = binding.allMaterialComment.root.findViewById<Button>(R.id.addCommentButton)
            addCommentButton?.setOnClickListener {
                Log.d("Mate", "add button clicked")

                submitComment()
            }
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
                        Toast.makeText(context, "You have already enrolled in this course!", Toast.LENGTH_SHORT).show()
                        return@addOnSuccessListener
                    }

                    // If the user hasn't enrolled yet, proceed with the enrollment process
                    val materialRef = firestore.collection("Materials").document(materialId)

                    firestore.runTransaction { transaction ->
                        // Get the current state of the material
                        val snapshot = transaction.get(materialRef)

                        // Increment the enroll field value
                        val newEnrollValue = snapshot.getLong("enroll")?.plus(1) ?: 1L

                        // Update the enroll field
                        transaction.update(materialRef, "enroll", newEnrollValue)

                        // Create a new Enrollment object
                        val enrollment = Enrollment(userId = userId, materialId = materialId)

                        // Add the enrollment document and return the newEnrollValue for further use if needed
                        transaction.set(firestore.collection("enrollments").document(), enrollment)
                        newEnrollValue
                    }.addOnSuccessListener {
                        Toast.makeText(context, "Successfully enrolled in the course!", Toast.LENGTH_SHORT).show()

                        // Navigate back to HomeFragment
                        findNavController().navigate(R.id.action_materialDetailsFragment_to_homeFragment)
                    }.addOnFailureListener { exception ->
                        Log.w("MaterialPreviewFragment", "Error adding document", exception)
                        Toast.makeText(context, "Error enrolling in the course!", Toast.LENGTH_SHORT).show()
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
    }



    private fun submitComment() {
        // Correctly accessing the EditText from included layout
        val commentEditText = binding.allMaterialComment.root.findViewById<EditText>(R.id.addCommentEditText)
        val commentText = commentEditText.text.toString().trim()

        if (commentText.isNotEmpty()) {
            val userId = firebaseAuth.currentUser?.uid ?: return
            val newComment = Comment(
                comment = commentText,
                userId = userId,
                commentDate = getCurrentDateTime(),
                materialId = material?.id ?: return
            )
            firestore.collection("Comments").add(newComment)
                .addOnSuccessListener {
                    commentEditText.text.clear()
                    loadComments(material?.id ?: return@addOnSuccessListener)
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
            .addOnSuccessListener { documents ->
                val commentsList = documents.toObjects(CommentWithUserDetails::class.java)
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
    inner class CommentsAdapter(
            private var commentsWithUserDetails: List<CommentWithUserDetails>
    ) : RecyclerView.Adapter<CommentsAdapter.CommentViewHolder>() {

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


            // Load user details
            loadUserDetails(commentWithDetails.comment.userId) { user ->
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

            // Check if there is an admin reply
            commentWithDetails.comment.replyComment?.let { reply ->
                holder.binding.adminReply.visibility = View.VISIBLE
                holder.binding.adminReply.text = reply
                holder.binding.replyTimestamp.visibility = View.VISIBLE
                holder.binding.replyTimestamp.text = commentWithDetails.comment.replyDate ?: ""

                // Set the admin name if available
//                holder.binding.adminName.text = commentWithDetails.adminName ?: "Admin"

                // Load the admin image if available
                commentWithDetails.adminImage?.let { imageUrl ->
                    Glide.with(context)
                        .load(imageUrl)
                        .placeholder(R.drawable.user)
                        .into(holder.binding.partnerImage)
                } ?: holder.binding.partnerImage.setImageResource(R.drawable.user)
            } ?: run {
                // Hide the reply section if there is no reply
                holder.binding.adminReply.visibility = View.GONE
                holder.binding.replyTimestamp.visibility = View.GONE
                holder.binding.partnerImage.visibility = View.GONE
//                holder.binding.adminName.visibility = View.GONE // Hide admin name if no reply
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
