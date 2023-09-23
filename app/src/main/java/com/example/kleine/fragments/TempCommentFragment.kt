package com.example.kleine.fragments

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
import com.example.kleine.R
import com.example.kleine.databinding.FragmentTempCommentBinding
import com.example.kleine.model.CommentWithUserDetails
import com.example.kleine.viewmodel.comment.CommentViewModel
import com.bumptech.glide.Glide
import androidx.recyclerview.widget.RecyclerView
import com.example.kleine.viewmodel.user.UserViewModel
import com.google.android.material.card.MaterialCardView
import com.google.firebase.storage.FirebaseStorage

class TempCommentFragment : Fragment() {

    private lateinit var binding: FragmentTempCommentBinding
    val userViewModel: UserViewModel by viewModels()
    val commentViewModel: CommentViewModel by viewModels {
        CommentViewModel.CommentViewModelFactory(userViewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTempCommentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CommentsAdapter(listOf())
        binding.allMaterialComment.materialCommentData.adapter = adapter

        val materialId = arguments?.getString("documentId") ?: return

        commentViewModel.fetchComments(materialId)

        commentViewModel.commentsWithUserDetails.observe(viewLifecycleOwner, Observer { commentsWithUserDetails ->
            Log.d("TempCommentFragment", "Data received: $commentsWithUserDetails")
            adapter.setData(commentsWithUserDetails)
        })
    }

    inner class CommentsAdapter(
        private var commentsWithUserDetails: List<CommentWithUserDetails>
    ) : RecyclerView.Adapter<CommentsAdapter.CommentViewHolder>() {
        inner class CommentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val commentTextView: TextView = view.findViewById(R.id.userComment)
            val commentDateTextView: TextView = view.findViewById(R.id.userCommentDate)
            val userNameTextView: TextView = view.findViewById(R.id.commentUserTextTitle)
            val userImageView: ImageView = view.findViewById(R.id.userImage)
            val replyTextView: TextView = view.findViewById(R.id.userReplyText)
            val replyDateTextView: TextView = view.findViewById(R.id.replyCommentDate)
            val partnerNameTextView: TextView = view.findViewById(R.id.commentReplyUserTextTitle)
            val partnerImageView: ImageView = view.findViewById(R.id.partnerImage)
            val ratingTextView: TextView = view.findViewById(R.id.rating)
            val replyCommentDate: TextView = view.findViewById(R.id.replyCommentDate)
            val arrow: MaterialCardView = view.findViewById(R.id.firstReplyCommentArrowImg)
            val firstReplyCommentImg: MaterialCardView = view.findViewById(R.id.firstReplyCommentImg)
            val userReplyCommentCard: ConstraintLayout = view.findViewById(R.id.userReplyCommentCard)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_material_comment, parent, false)
            return CommentViewHolder(view)
        }

        override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
            val commentWithUserDetails = commentsWithUserDetails[position]
            val comment = commentWithUserDetails.comment
            holder.commentTextView.text = comment.comment
            holder.commentDateTextView.text = comment.commentDate
            holder.userNameTextView.text = commentWithUserDetails.userName
            holder.ratingTextView.text = comment.rating.toString()
            if (commentWithUserDetails.userImage != null) {
                val storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(commentWithUserDetails.userImage)
                storageReference.downloadUrl.addOnSuccessListener { uri ->
                    Glide.with(holder.userImageView.context)
                        .load(uri.toString())
                        .into(holder.userImageView)
                }
            }
            if (commentWithUserDetails.partnerName != null) {
                holder.replyTextView.text = comment.replyComment
                holder.replyDateTextView.text = comment.replyDate
                holder.partnerNameTextView.text = commentWithUserDetails.partnerName
                if (commentWithUserDetails.partnerImage != null) {
                    val storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(commentWithUserDetails.partnerImage)
                    storageReference.downloadUrl.addOnSuccessListener { uri ->
                        Glide.with(holder.partnerImageView.context)
                            .load(uri.toString())
                            .into(holder.partnerImageView)
                    }
                }
            } else {
                holder.arrow.visibility = View.GONE
                holder.firstReplyCommentImg.visibility = View.GONE
                holder.userReplyCommentCard.visibility = View.GONE
                holder.replyCommentDate.visibility = View.GONE
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