package com.example.kleine.viewmodel.partnership

import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.kleine.model.MaterialEngageData
import com.example.kleine.model.Partnership
import com.example.kleine.model.PartnershipStatus
import com.github.barteksc.pdfviewer.PDFView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class PartnershipViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val _partnershipsList = MutableLiveData<List<Partnership>>()
    val partnershipsList: LiveData<List<Partnership>> = _partnershipsList
    private val _uploadStatus = MutableLiveData<String>()
    val uploadStatus: LiveData<String> get() = _uploadStatus
    private val storage = FirebaseStorage.getInstance()

    fun fetchApprovedPartnerships() {
        db.collection("Partnerships")
            .whereEqualTo("status", PartnershipStatus.accepted.name) // Filter by status
            .get()
            .addOnSuccessListener { result ->
                val partnershipList = mutableListOf<Partnership>()
                for (document in result) {
                    val partnership = document.toObject(Partnership::class.java)
                    partnershipList.add(partnership)
                }
                _partnershipsList.value = partnershipList
            }
    }
    fun fetchUserName(userId: String, callback: (String?) -> Unit) {
        val db = FirebaseFirestore.getInstance()
        db.collection("users").document(userId)
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val userName = document.getString("firstName") + " " + document.getString("lastName")
                    callback(userName)
                } else {
                    callback(null)
                }
            }
            .addOnFailureListener {
                callback(null)
            }
    }

    fun fetchUserImage(userId: String, callback: (String?) -> Unit) {
        val db = FirebaseFirestore.getInstance()
        db.collection("users").document(userId)
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val userImage = document.getString("imageUrl")
                    callback(userImage)
                } else {
                    callback(null)
                }
            }
            .addOnFailureListener {
                callback(null)
            }
    }

    fun loadPdfIntoView(pdfUrl: String, pdfView: PDFView, closePdfButton: View) {
        val storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(pdfUrl)
        storageReference.getStream().addOnSuccessListener { taskSnapshot ->
            val inputStream = taskSnapshot.stream
            pdfView.bringToFront()  // Brings the PDFView to the front
            pdfView.visibility = View.VISIBLE
            closePdfButton.visibility = View.VISIBLE  // Show the close button
            pdfView.fromStream(inputStream)
                .onLoad { totalPages -> Log.d("PDF_VIEW", "Loaded with total pages: $totalPages") }  // Log when PDF is loaded
                .onError { t -> Log.e("PDF_VIEW", "Error loading PDF", t) }  // Log errors if any
                .load()
        }.addOnFailureListener { e ->
            Log.e("PDF_VIEW", "Error downloading PDF", e)
        }
    }

}