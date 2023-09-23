package com.example.kleine.fragments.partnership

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kleine.R
import com.example.kleine.databinding.FragmentPartnershipViewMaterialDetailBinding
import com.example.kleine.model.Comment
import com.example.kleine.model.CommentWithUserDetails
import com.example.kleine.viewmodel.comment.CommentViewModel
import com.example.kleine.viewmodel.material.MaterialViewModel
import com.example.kleine.viewmodel.user.UserViewModel
import com.google.android.material.card.MaterialCardView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

interface OnCommentClickListener {
    fun onCommentReplyClicked(position: Int)
}

class PartnershipViewMaterialDetailFragment : Fragment(), OnCommentClickListener {
    val TAG = "PartnershipViewMaterialDetailFragment"
    private lateinit var binding: FragmentPartnershipViewMaterialDetailBinding
    private val materialViewModel: MaterialViewModel by viewModels()
    val commentViewModel: CommentViewModel by viewModels {
        CommentViewModel.CommentViewModelFactory(userViewModel)
    }
    val userViewModel: UserViewModel by viewModels()

    override fun onCommentReplyClicked(position: Int) {
        findNavController().navigate(R.id.action_partnershipViewMaterialDetailFragment_to_replyCommentFragment)
    }

    override fun onCreateView (
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPartnershipViewMaterialDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CommentsAdapter(listOf())
        binding.commentLayout.commentData.adapter = adapter
        val documentId = arguments?.getString("documentId") ?: return
        commentViewModel.fetchComments(documentId)
        commentViewModel.commentsWithUserDetails.observe(viewLifecycleOwner, Observer { commentsWithUserDetails ->
            Log.d("TempCommentFragment", "Data received: $commentsWithUserDetails")
            adapter.setData(commentsWithUserDetails)
        })

        materialViewModel.materialEngageData.observe(viewLifecycleOwner) { materialEngageData ->
            // Update UI with material engage data
            binding.textTitle.text = materialEngageData?.name ?: ""
            binding.viewNum.text = materialEngageData?.view.toString()
            binding.enrollNum.text = materialEngageData?.enroll.toString()
            binding.graduateNum.text = materialEngageData?.graduate.toString()

            if (materialEngageData?.imageUrl?.isNotEmpty() == true) {
                val storageReference = FirebaseStorage.getInstance()
                    .getReferenceFromUrl(materialEngageData?.imageUrl.toString())
                storageReference.downloadUrl.addOnSuccessListener { uri ->
                    Glide.with(binding.root.context)
                        .load(uri.toString())
                        .into(binding.viewHeaderBackground)
                }
            }
        }


        // Fetch material engage data and image URI using MaterialViewModel
        materialViewModel.fetchMaterialsEngageData(documentId)

        val quizLayout = view.findViewById<View>(R.id.quiz_layout)
        val commentLayout = view.findViewById<View>(R.id.comment_layout)

        // For quizLayout
        val quizCard = quizLayout.findViewById<MaterialCardView>(R.id.quiz_card)
        val downArrowQuiz = quizLayout.findViewById<ImageView>(R.id.down_arrow)
        val quizData = quizLayout.findViewById<RecyclerView>(R.id.quizData)

        // For commentLayout
        val commentCard = commentLayout.findViewById<MaterialCardView>(R.id.comment_card)
        val downArrowComment = commentLayout.findViewById<ImageView>(R.id.down_arrow_comment)
        val commentData = commentLayout.findViewById<RecyclerView>(R.id.commentData)

        downArrowQuiz.setOnClickListener {
            val visibility = if (quizCard.visibility == View.VISIBLE) View.GONE else View.VISIBLE
            quizCard.visibility = visibility
            quizData.visibility = visibility
        }

        downArrowComment.setOnClickListener {
            val visibility = if (commentCard.visibility == View.VISIBLE) View.GONE else View.VISIBLE
            commentCard.visibility = visibility
            commentData.visibility = visibility
        }
    }
    inner class CommentsAdapter(
        private var commentsWithUserDetails: List<CommentWithUserDetails>
    ) : RecyclerView.Adapter<CommentsAdapter.CommentViewHolder>() {
        inner class CommentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val userCommentImage: ImageView = view.findViewById(R.id.userCommentImage)
            val commentUserTextTitle: TextView = view.findViewById(R.id.commentUserTextTitle)
            val commentUserDate: TextView = view.findViewById(R.id.commentUserDate)
            val userRating: TextView = view.findViewById(R.id.userRating)
            val userComment: TextView = view.findViewById(R.id.userComment)
            val commentReply: TextView = view.findViewById(R.id.commentReply)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_comment_detail, parent, false)
            return CommentViewHolder(view)
        }

        override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
            val commentWithUserDetails = commentsWithUserDetails[position]
            val comment = commentWithUserDetails.comment
            holder.commentUserTextTitle.text = commentWithUserDetails.userName
            holder.commentUserDate.text = comment.commentDate
            holder.userRating.text = comment.rating.toString()
            holder.userComment.text = comment.comment

            if (commentWithUserDetails.userImage != null) {
                val storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(commentWithUserDetails.userImage)
                storageReference.downloadUrl.addOnSuccessListener { uri ->
                    Glide.with(holder.userCommentImage.context)
                        .load(uri.toString())
                        .into(holder.userCommentImage)
                }
            }
            if (comment.replyPartnerId != null) {
                //commentReply set background color to grey and not clickable
            } else {
                //commentReply set background color to green and clickable
                //after click link to comment reply fragment
            }
        }

        override fun getItemCount(): Int {
            return commentsWithUserDetails.size
        }

        fun setData(newCommentsWithUserDetails: List<CommentWithUserDetails>) {
            this.commentsWithUserDetails = newCommentsWithUserDetails
            notifyDataSetChanged()
        }
    }
}
