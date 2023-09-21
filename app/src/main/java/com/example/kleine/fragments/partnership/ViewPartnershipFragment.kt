package com.example.kleine.fragments.partnership

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.kleine.R
import com.example.kleine.databinding.FragmentViewPartnershipBinding
import com.example.kleine.model.Partnership
import com.example.kleine.model.PartnershipStatus
import com.example.kleine.model.Status
import com.example.kleine.viewmodel.partnership.PartnershipViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User

interface OnViewPartnerPdfClickListener {
    fun onPdfClick(pdfUrl: String)
}
class ViewPartnershipFragment : Fragment(), OnViewPartnerPdfClickListener {
    val TAG = "ViewPartnershipFragment"
    private lateinit var binding: FragmentViewPartnershipBinding
    private val partnershipViewModel: PartnershipViewModel by viewModels()


    override fun onCreateView (
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewPartnershipBinding.inflate(inflater, container, false)

        binding.closePdfButton.setOnClickListener {
            binding.pdfView.visibility = View.GONE
            it.visibility = View.GONE  // Hide the close button
        }
        binding.quitPartnership.setOnClickListener {
            quitPartnership()
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchPartnerDetailsFromFirestore()
        onViewMaterialClick()
        onUpdatePartnershipClick()

    }

    private fun onViewMaterialClick() {
        binding.viewMaterial.setOnClickListener {
            findNavController().navigate(R.id.action_viewPartnershipFragment_to_partnershipViewMaterialFragment)
        }
    }

    private fun quitPartnership() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val db = FirebaseFirestore.getInstance()
            db.collection("Partnerships")
                .whereEqualTo("userId", userId)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    if (!querySnapshot.isEmpty) {
                        val documentRef = querySnapshot.documents[0].reference
                        documentRef.update("status", PartnershipStatus.quit)
                            .addOnSuccessListener {
                                val partnershipRef = db.collection("users").document(userId)
                                partnershipRef.update("status", Status.USERS)
                                Log.d(TAG, "Partnership status successfully updated to 'quit'")
                                // Navigating back to the previous fragment
                                findNavController().popBackStack()
                            }
                            .addOnFailureListener { exception ->
                                Log.d(TAG, "Error updating partnership status: ", exception)
                            }
                    } else {
                        Log.d(TAG, "No such partnership exists for this user")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d(TAG, "Error finding partnership: ", exception)
                }
        }
    }


    private fun onUpdatePartnershipClick() {
        binding.updatePartnership.setOnClickListener {
            findNavController().navigate(R.id.action_viewPartnershipFragment_to_updatePartnershipFragment)
        }
    }
    private fun displayPartnerDetails(partner: Partnership) {
        binding.institutionNameText.text = partner.instiName
        binding.institutionTypeText.text = partner.instiType
        binding.locationText.text = partner.location
        binding.contactNumText.text = partner.contactNum

        val pdfFilesName = partner.documentationName.split("|")
        val pdfFiles = partner.documentation.split("|")

        if (pdfFilesName.isNotEmpty()) {
            binding.documentText1.text = pdfFilesName[0]
            binding.documentText1.setOnClickListener {
                onPdfClick(pdfFiles[0])
            }
        }

        if (pdfFilesName.size >= 2) {
            binding.documentText2.text = pdfFilesName[1]
            binding.documentText2.setOnClickListener {
                onPdfClick(pdfFiles[1])
            }
        } else {
            binding.documentText2.visibility = View.GONE
        }
    }

    private fun fetchPartnerDetailsFromFirestore() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val db = FirebaseFirestore.getInstance()
            db.collection("Partnerships")
                .whereEqualTo("userId", userId)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    if (!querySnapshot.isEmpty) {
                        val partnership = querySnapshot.documents[0].toObject(Partnership::class.java)
                        if (partnership != null) {
                            displayPartnerDetails(partnership)
                        } else {
                            Log.d(TAG, "No partner found")
                        }
                    } else {
                        Log.d(TAG, "No such document")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d(TAG, "Error getting documents: ", exception)
                }

        }
    }

    override fun onPdfClick(pdfUrl: String) {
        partnershipViewModel.loadPdfIntoView(
            pdfUrl,
            binding.pdfView,
            binding.closePdfButton
        )
    }




}