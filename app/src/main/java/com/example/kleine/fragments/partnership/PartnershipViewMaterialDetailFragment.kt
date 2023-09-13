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
import androidx.recyclerview.widget.RecyclerView
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
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

interface OnCommentClickListener {
    fun onCommentReplyClicked(position: Int)
}

class PartnershipViewMaterialDetailFragment : Fragment(), OnCommentClickListener {
    val TAG = "PartnershipViewMaterialDetailFragment"
    private lateinit var binding: FragmentPartnershipViewMaterialDetailBinding

    override fun onCommentReplyClicked(position: Int) {
        findNavController().navigate(R.id.action_partnershipViewMaterialDetailFragment_to_replyCommentFragment)
    }

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

        val documentId = arguments?.getString("documentId") ?: return

        // Initialize Firestore
        val db = FirebaseFirestore.getInstance()

        // Fetch specific fields from Firestore
        db.collection("Materials").document(documentId)
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val materialName = document.getString("name") ?: ""
                    val viewCount = document.getLong("view") ?: 0
                    val enrollCount = document.getLong("enroll") ?: 0
                    val graduateCount = document.getLong("graduate") ?: 0
                    val imageUrl = document.getString("imageUrl") ?: ""

                    // Update your UI elements with these values
                    // For example, if you have TextViews to display these, you can set their text.
                    binding.textTitle.text = materialName
                    binding.viewNum.text = viewCount.toString()
                    binding.enrollNum.text = enrollCount.toString()
                    binding.graduateNum.text = graduateCount.toString()
                    val storageReference =
                        FirebaseStorage.getInstance().getReferenceFromUrl(imageUrl)
                    storageReference.downloadUrl.addOnSuccessListener { uri ->
                        Glide.with(binding.root.context)
                            .load(uri.toString())
                            .into(binding.viewHeaderBackground) // Replace with the actual ID of your ImageView
                    }
                }
            }
        val quizLayout = view.findViewById<View>(R.id.quiz_layout)
        val commentLayout = view.findViewById<View>(R.id.comment_layout)

        // For quizLayout
        val quizCard = quizLayout.findViewById<MaterialCardView>(R.id.quiz_card)
        val downArrowQuiz = quizLayout.findViewById<ImageView>(R.id.down_arrow)
        val quizData = quizLayout.findViewById<RecyclerView>(R.id.quizData)

        // For commentLayout
        val commentCard = commentLayout.findViewById<MaterialCardView>(R.id.comment_card)
        val downArrowComment = commentLayout.findViewById<ImageView>(R.id.down_arrow_comment)
        val commentData = quizLayout.findViewById<RecyclerView>(R.id.commentData)

        downArrowQuiz.setOnClickListener {
            val visibility = if (quizCard.visibility == View.VISIBLE) View.GONE else View.VISIBLE

            // Toggle visibility
            quizCard.visibility = visibility
            quizData.visibility = visibility
        }

        downArrowComment.setOnClickListener {
            val visibility = if (commentCard.visibility == View.VISIBLE) View.GONE else View.VISIBLE

            // Toggle visibility
            commentCard.visibility = visibility
            commentData.visibility = visibility
        }

    }


}