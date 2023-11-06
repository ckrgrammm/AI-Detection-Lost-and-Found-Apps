package com.example.fyps.fragments.admin

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fyps.R
import com.example.fyps.databinding.FragmentAdminViewPartnershipBinding
import com.example.fyps.databinding.RecyclerViewAdminViewPartnershipBinding
import com.example.fyps.model.AppDatabase
import com.example.fyps.model.Partnership
import com.example.fyps.model.PartnershipDao
import com.example.fyps.model.PartnershipEntity
import com.example.fyps.resource.NetworkReceiver
import com.example.fyps.viewmodel.partnership.PartnershipViewModel
import com.example.fyps.viewmodel.user.UserViewModel
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File

interface OnPdfClickListener {
    fun onPdfClick(pdfUrl: String, documentName: String, isFirstPdf: Boolean, partnershipId: String)
}
class AdminViewPartnershipFragment : Fragment(), OnPdfClickListener {
    private lateinit var binding: FragmentAdminViewPartnershipBinding
    private val partnershipViewModel: PartnershipViewModel by viewModels()
    private val userViewModel: UserViewModel by viewModels()
    private var isNetworkAvailable: Boolean = false
    private var partnershipAdapter = PartnershipAdapter(listOf(), this)
    private var partnershipId: String? = null
    private lateinit var database: AppDatabase
    private lateinit var partnershipDao: PartnershipDao
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        database = AppDatabase.getDatabase(requireContext())
        partnershipDao = database.partnershipDao()
        binding = FragmentAdminViewPartnershipBinding.inflate(inflater, container, false)

        val partnershipAdapter = PartnershipAdapter(listOf(), this)
        binding.partnershipData.adapter = partnershipAdapter

        partnershipViewModel.partnershipsList.observe(viewLifecycleOwner, Observer { partnerships ->
            partnershipAdapter.partnershipsList = partnerships
            partnershipAdapter.notifyDataSetChanged()
        })

        partnershipViewModel.fetchApprovedPartnerships()

        binding.closePdfButton.setOnClickListener {
            binding.pdfView.visibility = View.GONE
            it.visibility = View.GONE  // Hide the close button
        }

        binding.viewRequest.setOnClickListener {  // viewRequest is from XML
            findNavController().navigate(R.id.action_adminViewPartnershipFragment_to_adminViewPartnershipRequestFragment)
        }

        return binding.root
    }

    inner class PartnershipAdapter(var partnershipsList: List<Partnership>, private val pdfClickListener: OnPdfClickListener) : RecyclerView.Adapter<PartnershipViewHolder>() {
        var isNetworkAvailable: Boolean = false

        fun setNetworkAvailability(isAvailable: Boolean) {
            isNetworkAvailable = isAvailable
            notifyDataSetChanged()
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartnershipViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = RecyclerViewAdminViewPartnershipBinding.inflate(inflater, parent, false)
            return PartnershipViewHolder(itemBinding, pdfClickListener)
        }

        override fun onBindViewHolder(holder: PartnershipViewHolder, position: Int) {
            holder.bind(partnershipsList[position])
        }

        override fun getItemCount(): Int {
            return partnershipsList.size
        }

    }

    inner class PartnershipViewHolder(private val itemBinding: RecyclerViewAdminViewPartnershipBinding, private val pdfClickListener: OnPdfClickListener) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(partnership: Partnership) {
            val partnershipEntity = partnership.toEntity()
            insertPartnershipIntoRoomDB(partnershipEntity)
            partnershipId = partnership.id
            itemBinding.instiNameType.text = partnership.instiName + "\n" + partnership.instiType
            itemBinding.location.text = partnership.location
            itemBinding.contactNum.text = partnership.contactNum
            itemBinding.reason.text = partnership.reason
            val documentation = partnership.documentation
            val documentationName = partnership.documentationName
            val pdfFilesName = documentationName.split("|")
            val pdfFiles = documentation.split("|")
            if (pdfFilesName.isNotEmpty()) {
                itemBinding.pdfFile1.text = pdfFilesName[0]
                itemBinding.pdfFile1.setOnClickListener {
                    pdfClickListener.onPdfClick(pdfFiles[0], pdfFilesName[0], true,partnership.id)
                }
            }

            if (pdfFilesName.size >= 2) {
                itemBinding.pdfFile2.text = pdfFilesName[1]
                itemBinding.pdfFile2.setOnClickListener {
                    pdfClickListener.onPdfClick(pdfFiles[1], pdfFilesName[1], false,partnership.id)
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
                    val storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(userImage)
                    storageReference.downloadUrl.addOnSuccessListener { uri ->
                        Glide.with(itemBinding.root.context)
                            .load(uri.toString())
                            .into(itemBinding.userImg)
                    }
                }
            }

        }
    }
    override fun onPdfClick(pdfUrl: String, documentName: String, isFirstPdf: Boolean, partnershipId: String) {
        val alertDialog = AlertDialog.Builder(requireContext())
            .setTitle("Download PDF")
            .setMessage("Do you want to download the PDF for offline viewing?")
            .setPositiveButton("Download") { dialog, which ->
                if (isNetworkAvailable) {
                    downloadAndSavePdf(pdfUrl, documentName, isFirstPdf)
                } else {
                    showNoInternetDialog()
                }

            }
            .setNeutralButton("View Online") { dialog, which ->
                if (isNetworkAvailable) {
                    partnershipViewModel.loadPdfIntoView(
                        pdfUrl,
                        binding.pdfView,
                        binding.closePdfButton
                    )
                } else {
                    showNoInternetDialog()
                }

            }
            .setNegativeButton("View Offline") { dialog, which ->
                val pdfPaths = runBlocking {
                    database.partnershipDao().getDocumentationLocalPath(partnershipId)?.split("|")
                }
                if (pdfPaths != null && pdfPaths.isNotEmpty()) {

                    if(isFirstPdf){
                        val firstPdfPath = pdfPaths[0]
                        val pdfFile = File(firstPdfPath)
                        if (pdfFile.exists()) {
                            openPdfFile(firstPdfPath)
                        } else {
                            // PDF file not found, show a message
                            Toast.makeText(requireContext(), "PDF not found on device.", Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        val secondPdfPath = pdfPaths[1]
                        val pdfFile = File(secondPdfPath)
                        if (pdfFile.exists()) {
                            openPdfFile(secondPdfPath)
                        } else {
                            // PDF file not found, show a message
                            Toast.makeText(requireContext(), "PDF not found on device.", Toast.LENGTH_SHORT).show()
                        }
                    }

                } else {
                    // PDF path not found in the database, show a message
                    Toast.makeText(requireContext(), "PDF path not found.", Toast.LENGTH_SHORT).show()
                }
            }

            .create()

        alertDialog.show()
    }
    override fun onResume() {
        super.onResume()
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        requireContext().registerReceiver(networkReceiver, filter)
        partnershipViewModel.fetchApprovedPartnerships()
    }

    override fun onPause() {
        super.onPause()
        requireContext().unregisterReceiver(networkReceiver)
    }
    private val onDownloadComplete: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
            if (downloadID == id) {
                // Download is complete, update the Room Database with the updated downloadedPdfLocations string
                updateRoomDatabaseWithFilePath(partnershipId.toString(), downloadedPdfLocations)
            }
        }
    }
    private var downloadID: Long = 0L
    private lateinit var file: File
    private var downloadedPdfLocations = "|"
    private fun downloadAndSavePdf(pdfUrl: String, documentName: String, isFirstPdf: Boolean) {
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

        // Update the downloadedPdfLocations string based on whether it is the first or second PDF
        val currentPaths = downloadedPdfLocations.split("|").toMutableList()
        if (isFirstPdf) {
            currentPaths[0] = file.absolutePath
        } else {
            currentPaths[1] = file.absolutePath
        }
        downloadedPdfLocations = currentPaths.joinToString("|")
    }



    private fun updateRoomDatabaseWithFilePath(partnershipId: String, filePath: String) {
        lifecycleScope.launch {
            database.partnershipDao().updateDocumentationLocalPath(partnershipId, filePath)
        }
    }
    private fun insertPartnershipIntoRoomDB(partnershipEntity: PartnershipEntity) {
        lifecycleScope.launch {
            val existingEntity = database.partnershipDao().getPartnershipById(partnershipEntity.id)
            if (existingEntity != null) {
                database.partnershipDao().update(
                    id = partnershipEntity.id,
                    instiName = partnershipEntity.instiName,
                    instiType = partnershipEntity.instiType,
                    location = partnershipEntity.location,
                    contactNum = partnershipEntity.contactNum,
                    reason = partnershipEntity.reason,
                    documentation = partnershipEntity.documentation,
                    documentationName = partnershipEntity.documentationName,
                    userId = partnershipEntity.userId,
                    status = partnershipEntity.status
                )
            } else {
                database.partnershipDao().insert(partnershipEntity)
            }
        }
    }



    private fun openPdfFile(pdfPath: String) {
        val pdfFile = File(pdfPath)
        if (pdfFile.exists()) {
            binding.pdfView.fromFile(pdfFile)
                .load()

            binding.pdfView.bringToFront()
            binding.pdfView.visibility = View.VISIBLE
            binding.closePdfButton.visibility = View.VISIBLE
        } else {
            Toast.makeText(requireContext(), "PDF file not found.", Toast.LENGTH_SHORT).show()
        }
    }


    private fun showNoInternetDialog() {
        // Inflate the layout for the dialog
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.no_internet_dialog, null)

        // Create the AlertDialog
        val alertDialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .create()

        // Set up the click listener for the "OK" button in the dialog
        val btnOk = dialogView.findViewById<Button>(R.id.btn_ok)
        btnOk.setOnClickListener {
            alertDialog.dismiss()
        }

        alertDialog.show()
    }
}