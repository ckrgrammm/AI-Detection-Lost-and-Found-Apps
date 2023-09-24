package com.example.kleine.fragments.admin

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
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
import androidx.room.Room
import com.bumptech.glide.Glide
import com.example.kleine.R
import com.example.kleine.activities.ShoppingActivity
import com.example.kleine.databinding.FragmentAdminViewPartnershipRequestBinding
import com.example.kleine.databinding.RecyclerViewAdminViewPartnershipRequestBinding
import com.example.kleine.model.AppDatabase
import com.example.kleine.model.Partnership
import com.example.kleine.model.PartnershipEntity
import com.example.kleine.model.PartnershipStatus
import com.example.kleine.model.Status
import com.example.kleine.viewmodel.partnership.PartnershipViewModel
import com.example.kleine.viewmodel.shopping.ShoppingViewModel
import com.example.kleine.viewmodel.user.UserViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.Uri
import androidx.lifecycle.lifecycleScope
import com.example.kleine.resource.NetworkReceiver
import kotlinx.coroutines.launch
import java.io.File


interface OnRequestPdfClickListener {
    fun onPdfClick(pdfUrl: String,documentName: String)
}
class AdminViewPartnershipRequestFragment : Fragment(), OnRequestPdfClickListener {
    val TAG = "AdminViewPartnershipRequestFragment"
    private lateinit var binding: FragmentAdminViewPartnershipRequestBinding
    private lateinit var viewModel: ShoppingViewModel
    private val partnershipViewModel: PartnershipViewModel by viewModels()
    private val userViewModel: UserViewModel by viewModels()
    private var partnershipAdapter = PartnershipAdapter(listOf(), this)
    private lateinit var roomDB: AppDatabase
    private var isNetworkAvailable: Boolean = false
    private var partnershipId: String? = null
    private val networkReceiver = NetworkReceiver(
        onNetworkAvailable = {
            isNetworkAvailable = true
            partnershipAdapter.setNetworkAvailability(true)
        },
        onNetworkUnavailable = {
            isNetworkAvailable = false
            partnershipAdapter.setNetworkAvailability(false)
        }
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (activity as ShoppingActivity).viewModel
        viewModel.getUser()
    }

    override fun onCreateView (
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        roomDB = Room.databaseBuilder(
            requireContext(),
            AppDatabase::class.java, "partnership-database"
        ).build()

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
        var isNetworkAvailable: Boolean = false

        fun setNetworkAvailability(isAvailable: Boolean) {
            isNetworkAvailable = isAvailable
            notifyDataSetChanged()
        }

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

            val partnershipEntity = partnership.toEntity()
            insertPartnershipIntoRoomDB(partnershipEntity)


//            insertPartnershipIntoRoomDB(partnership)
            partnershipId = partnership.id
            itemBinding.btnApprove.isEnabled = isNetworkAvailable
            itemBinding.btnReject.isEnabled = isNetworkAvailable
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
                    pdfClickListener.onPdfClick(pdfFiles[0],documentationName)  // Using pdfClickListener
                }
            }

            if (pdfFilesName.size >= 2) {
                itemBinding.pdfFile2.text = pdfFilesName[1]
                itemBinding.pdfFile2.setOnClickListener {
                    pdfClickListener.onPdfClick(pdfFiles[0],documentationName)
                }
            } else {
                itemBinding.pdfFile2.visibility = View.GONE
            }

            userViewModel.fetchUserName(partnership.userId) { userName ->
                if (userName != null) {
                    itemBinding.nameText.text = userName
                }
            }

            userViewModel.fetchUserImage(partnership.userId) { userImage ->
                if (userImage != null) {
                    val storageReference =
                        FirebaseStorage.getInstance().getReferenceFromUrl(userImage)
                    storageReference.downloadUrl.addOnSuccessListener { uri ->
                        Glide.with(itemBinding.root.context)
                            .load(uri.toString())
                            .into(itemBinding.userImg)
                    }
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
    override fun onPdfClick(pdfUrl: String, documentName: String) {
        val alertDialog = AlertDialog.Builder(requireContext())
            .setTitle("Download PDF")
            .setMessage("Do you want to download the PDF for offline viewing?")
            .setPositiveButton("Download") { dialog, which ->
                downloadAndSavePdf(pdfUrl, documentName)
            }
            .setNegativeButton("View Online") { dialog, which ->
                partnershipViewModel.loadPdfIntoView(
                    pdfUrl,
                    binding.pdfView,
                    binding.closePdfButton
                )
            }
            .create()

        alertDialog.show()
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
        if (isNetworkAvailable) {
            Toast.makeText(context, "Network is not available. Cannot approve partner.", Toast.LENGTH_SHORT).show()
            return
        }
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
        if (isNetworkAvailable) {
            Toast.makeText(context, "Network is not available. Cannot reject partner.", Toast.LENGTH_SHORT).show()
            return
        }
        val firestore = FirebaseFirestore.getInstance()
        val partnershipRef = firestore.collection("Partnerships").document(partnershipId)

        partnershipRef.update("status", PartnershipStatus.rejected)
        partnershipRef.update("rejectReason", reasonMsg)
            .addOnSuccessListener {
                Toast.makeText(context, "Partner rejected successfully!", Toast.LENGTH_SHORT).show()
            }
    }



    override fun onResume() {
        super.onResume()
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        requireContext().registerReceiver(networkReceiver, filter)
    }

    override fun onPause() {
        super.onPause()
        requireContext().unregisterReceiver(networkReceiver)
    }
    private val onDownloadComplete: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
            if (downloadID == id) {
                // Download is complete, update the Room Database with the local file path
                updateRoomDatabaseWithFilePath(partnershipId.toString(), file.absolutePath)
            }
        }
    }


    private var downloadID: Long = 0L
    private lateinit var file: File

    private fun downloadAndSavePdf(pdfUrl: String,documentName:String) {
        val downloadManager = requireActivity().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

        val uri = Uri.parse(pdfUrl)
        val request = DownloadManager.Request(uri)
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)

        // Set the local destination for the downloaded file to a path within the application's external files directory
        file = File(requireActivity().getExternalFilesDir(null), documentName)
        request.setDestinationUri(Uri.fromFile(file))

        // Enqueue a new download and get the reference ID
        downloadID = downloadManager.enqueue(request)

        // Register a BroadcastReceiver to listen for the completion of the download
        requireActivity().registerReceiver(onDownloadComplete, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
    }

    private fun updateRoomDatabaseWithFilePath(partnershipId: String, filePath: String) {
        lifecycleScope.launch {
            roomDB.partnershipDao().updateDocumentationLocalPath(partnershipId, filePath)
        }
    }
    private fun insertPartnershipIntoRoomDB(partnershipEntity: PartnershipEntity) {
        lifecycleScope.launch {
            roomDB.partnershipDao().insert(partnershipEntity)
        }
    }


}