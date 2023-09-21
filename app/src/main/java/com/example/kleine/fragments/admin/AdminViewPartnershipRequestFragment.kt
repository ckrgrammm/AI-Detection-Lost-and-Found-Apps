package com.example.kleine.fragments.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kleine.R
import com.example.kleine.activities.ShoppingActivity
import com.example.kleine.databinding.FragmentAdminViewPartnershipRequestBinding
import com.example.kleine.databinding.RecyclerViewAdminViewPartnershipRequestBinding
import com.example.kleine.model.Partnership
import com.example.kleine.model.PartnershipStatus
import com.example.kleine.model.Status
import com.example.kleine.viewmodel.partnership.PartnershipViewModel
import com.example.kleine.viewmodel.shopping.ShoppingViewModel
import com.google.firebase.firestore.FirebaseFirestore

interface OnRequestPdfClickListener {
    fun onPdfClick(pdfUrl: String)
}
class AdminViewPartnershipRequestFragment : Fragment(), OnRequestPdfClickListener {
    val TAG = "AdminViewPartnershipRequestFragment"
    private lateinit var binding: FragmentAdminViewPartnershipRequestBinding
    private lateinit var viewModel: ShoppingViewModel
    private val partnershipViewModel: PartnershipViewModel by viewModels()
    private var partnershipAdapter = PartnershipAdapter(listOf(), this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (activity as ShoppingActivity).viewModel
        viewModel.getUser()
    }

    override fun onCreateView (
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAdminViewPartnershipRequestBinding.inflate(inflater, container, false)

        binding.partnershipRequestData.adapter = partnershipAdapter



        partnershipViewModel.fetchPendingPartnerships()

        binding.closePdfButton.setOnClickListener {
            binding.pdfView.visibility = View.GONE
            it.visibility = View.GONE  // Hide the close button
        }
        partnershipViewModel.partnershipsList.observe(viewLifecycleOwner, Observer { partnerships ->
            partnershipAdapter.partnershipsList = partnerships
            partnershipAdapter.notifyDataSetChanged()
        })
        return binding.root
    }
    inner class PartnershipAdapter(var partnershipsList: List<Partnership>, private val pdfClickListener: OnRequestPdfClickListener) : RecyclerView.Adapter<PartnershipViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminViewPartnershipRequestFragment.PartnershipViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = RecyclerViewAdminViewPartnershipRequestBinding.inflate(inflater, parent, false)
            return PartnershipViewHolder(itemBinding, pdfClickListener)
        }


        override fun onBindViewHolder(holder: PartnershipViewHolder, position: Int) {
            holder.bind(partnershipsList[position])
        }

        override fun getItemCount(): Int {
            return partnershipsList.size
        }

    }
    inner class PartnershipViewHolder(private val itemBinding: RecyclerViewAdminViewPartnershipRequestBinding, private val pdfClickListener: OnRequestPdfClickListener) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(partnership: Partnership) {
            itemBinding.instiNameType.text = partnership.instiName + "\n" + partnership.instiType
            itemBinding.location.text = partnership.location
            itemBinding.contactNum.text = partnership.contactNum
            itemBinding.reason.text = partnership.reason
            val documentation = partnership.documentation
            val documentationName = partnership.documentationName
            val pdfFilesName = documentationName.split("|") // Split the field using the "|" delimiter
            val pdfFiles = documentation.split("|")
            if (pdfFilesName.isNotEmpty()) {
                itemBinding.pdfFile1.text = pdfFilesName[0]
                itemBinding.pdfFile1.setOnClickListener {
                    pdfClickListener.onPdfClick(pdfFiles[0])  // Using pdfClickListener
                }
            }

            if (pdfFilesName.size >= 2) {
                itemBinding.pdfFile2.text = pdfFilesName[1]
                itemBinding.pdfFile2.setOnClickListener {
                    pdfClickListener.onPdfClick(pdfFiles[0])
                }
            } else {
                itemBinding.pdfFile2.visibility = View.GONE
            }

            partnershipViewModel.fetchUserName(partnership.userId) { userName ->
                if (userName != null) {
                    itemBinding.nameText.text = userName
                }
            }

            partnershipViewModel.fetchUserImage(partnership.userId) { userImage ->
                if (userImage != null) {
                    Glide.with(itemBinding.root.context)
                        .load(userImage)
                        .into(itemBinding.userImg)
                }
            }

            itemBinding.btnApprove.setOnClickListener {
                showAlertDialog(
                    title = "Approve Partnership",
                    message = "Are you sure you want to approve this user as partner?",
                    partnership = partnership
                )
            }

            itemBinding.btnReject.setOnClickListener {
                showRejectAlertDialog(
                    title = "Reject Partnership",
                    message = "Are you sure you want to reject this user?",
                    partnership = partnership
                )
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
    private fun showAlertDialog(title: String, message: String, partnership: Partnership) {
        // Inflate the layout for the dialog
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.delete_alert_dialog, null)

        // Set the title and message
        val tvTitle = dialogView.findViewById<TextView>(R.id.tv_delete_item)
        val tvMessage = dialogView.findViewById<TextView>(R.id.tv_delete_message)
        tvTitle.text = title
        tvMessage.text = message
        // Create the AlertDialog
        val alertDialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .create()
        // Set up the click listeners for the buttons in the dialog
        val btnNo = dialogView.findViewById<Button>(R.id.btn_no)
        btnNo.setOnClickListener {
            alertDialog.dismiss()
        }

        val btnYes = dialogView.findViewById<Button>(R.id.btn_yes)
        btnYes.setOnClickListener {
            approvePartnerData(partnership.id, partnership.userId)
            partnershipAdapter.notifyDataSetChanged()
            alertDialog.dismiss()
        }



        alertDialog.show()
    }

    private fun showRejectAlertDialog(title: String, message: String, partnership: Partnership) {
        // Inflate the layout for the dialog
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.reject_partner_alert_dialog, null)  // Assuming the layout's name is 'delete_alert_dialog.xml'

        // Set the title and message
        dialogView.findViewById<TextView>(R.id.tv_delete_item).text = title
        dialogView.findViewById<TextView>(R.id.tv_delete_message).text = message
        // Create the AlertDialog
        val alertDialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .create()
        // Set up the click listeners for the buttons in the dialog
        val btnNo = dialogView.findViewById<Button>(R.id.btn_no)
        btnNo.setOnClickListener {
            alertDialog.dismiss()
        }

        val btnYes = dialogView.findViewById<Button>(R.id.btn_yes)
        btnYes.setOnClickListener {
            val reasonMessage = dialogView.findViewById<EditText>(R.id.reason_message).text.toString()
            rejectPartnerData(partnership.id, partnership.userId, reasonMessage)
            partnershipAdapter.notifyDataSetChanged()
            alertDialog.dismiss()
        }

        alertDialog.show()
    }

    private fun approvePartnerData(partnershipId: String, userId: String) {
        val firestore = FirebaseFirestore.getInstance()
        val partnershipRef = firestore.collection("Partnerships").document(partnershipId)

        partnershipRef.update("status", PartnershipStatus.approved) // Assuming the field name is "status"
            .addOnSuccessListener {
                // Now, update the user's status
                val userRef = firestore.collection("users").document(userId)
                userRef.update("status", Status.PARTNERS)
                    .addOnSuccessListener {
                        Toast.makeText(context, "Partner approved successfully!", Toast.LENGTH_SHORT).show()
                    }
            }
    }

    private fun rejectPartnerData(partnershipId: String, userId: String, reasonMsg: String) {
        val firestore = FirebaseFirestore.getInstance()
        val partnershipRef = firestore.collection("Partnerships").document(partnershipId)

        partnershipRef.update("status", PartnershipStatus.rejected)
        partnershipRef.update("rejectReason", reasonMsg)
            .addOnSuccessListener {
                Toast.makeText(context, "Partner rejected successfully!", Toast.LENGTH_SHORT).show()
            }
    }
}