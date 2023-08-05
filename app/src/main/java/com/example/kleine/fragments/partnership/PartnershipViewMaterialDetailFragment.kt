package com.example.kleine.fragments.partnership

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.kleine.BuildConfig
import com.example.kleine.R
import com.example.kleine.activities.LunchActivity
import com.example.kleine.activities.ShoppingActivity
import com.example.kleine.databinding.FragmentProfileBinding
import com.example.kleine.databinding.FragmentViewPartnershipBinding
import com.example.kleine.databinding.FragmentPartnershipViewMaterialDetailBinding
import com.example.kleine.model.User
import com.example.kleine.resource.Resource
import com.example.kleine.util.Constants.Companion.UPDATE_ADDRESS_FLAG
import com.example.kleine.viewmodel.shopping.ShoppingViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.card.MaterialCardView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth


class PartnershipViewMaterialDetailFragment : Fragment() {
    val TAG = "PartnershipViewMaterialDetailFragment"
    private lateinit var binding: FragmentPartnershipViewMaterialDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

        val quizLayout = view.findViewById<View>(R.id.quiz_layout)
        val commentLayout = view.findViewById<View>(R.id.comment_layout)

        // For quizLayout
        val quizCard = quizLayout.findViewById<MaterialCardView>(R.id.quiz_card)
        val downArrowQuiz = quizLayout.findViewById<ImageView>(R.id.down_arrow)
        val firstDataQuiz = quizLayout.findViewById<MaterialCardView>(R.id.firstData)
        val firstImgQuiz = quizLayout.findViewById<MaterialCardView>(R.id.firstImg)
        val secondDataQuiz = quizLayout.findViewById<MaterialCardView>(R.id.secondData)
        val secondImgQuiz = quizLayout.findViewById<MaterialCardView>(R.id.secondImg)
        val thirdDataQuiz = quizLayout.findViewById<MaterialCardView>(R.id.thirdData)
        val thirdImgQuiz = quizLayout.findViewById<MaterialCardView>(R.id.thirdImg)

        // For commentLayout
        val commentCard = commentLayout.findViewById<MaterialCardView>(R.id.comment_card)
        val downArrowComment = commentLayout.findViewById<ImageView>(R.id.down_arrow_comment)
        val firstDataComment = commentLayout.findViewById<MaterialCardView>(R.id.firstCommentData)
        val firstImgComment = commentLayout.findViewById<MaterialCardView>(R.id.firstCommentImg)
        val secondDataComment = commentLayout.findViewById<MaterialCardView>(R.id.secondCommentData)
        val secondImgComment = commentLayout.findViewById<MaterialCardView>(R.id.secondCommentImg)
        val thirdDataComment = commentLayout.findViewById<MaterialCardView>(R.id.thirdCommentData)
        val thirdImgComment = commentLayout.findViewById<MaterialCardView>(R.id.thirdCommentImg)

        downArrowQuiz.setOnClickListener {
            val visibility = if (quizCard.visibility == View.VISIBLE) View.GONE else View.VISIBLE

            // Toggle visibility
            quizCard.visibility = visibility
            firstDataQuiz.visibility = visibility
            firstImgQuiz.visibility = visibility
            secondDataQuiz.visibility = visibility
            secondImgQuiz.visibility = visibility
            thirdDataQuiz.visibility = visibility
            thirdImgQuiz.visibility = visibility
        }

        downArrowComment.setOnClickListener {
            val visibility = if (commentCard.visibility == View.VISIBLE) View.GONE else View.VISIBLE

            // Toggle visibility
            commentCard.visibility = visibility
            firstDataComment.visibility = visibility
            firstImgComment.visibility = visibility
            secondDataComment.visibility = visibility
            secondImgComment.visibility = visibility
            thirdDataComment.visibility = visibility
            thirdImgComment.visibility = visibility
        }
        onCommentReplyClick()

    }

    private fun onCommentReplyClick() {
        binding.commentLayout.commentReply.setOnClickListener {
            findNavController().navigate(R.id.action_partnershipViewMaterialDetailFragment_to_replyCommentFragment)
        }
        binding.commentLayout.commentReply2.setOnClickListener {
            findNavController().navigate(R.id.action_partnershipViewMaterialDetailFragment_to_replyCommentFragment)
        }
        binding.commentLayout.commentReply3.setOnClickListener {
            findNavController().navigate(R.id.action_partnershipViewMaterialDetailFragment_to_replyCommentFragment)
        }
    }


}