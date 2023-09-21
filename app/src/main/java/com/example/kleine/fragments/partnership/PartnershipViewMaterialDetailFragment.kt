package com.example.kleine.fragments.partnership

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kleine.R
import com.example.kleine.databinding.FragmentPartnershipViewMaterialDetailBinding
import com.example.kleine.viewmodel.material.MaterialViewModel
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

        val documentId = arguments?.getString("documentId") ?: return
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
}
