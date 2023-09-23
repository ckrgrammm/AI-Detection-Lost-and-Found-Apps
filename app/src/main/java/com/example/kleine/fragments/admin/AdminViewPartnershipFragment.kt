package com.example.kleine.fragments.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kleine.R
import com.example.kleine.databinding.FragmentAdminViewPartnershipBinding
import com.example.kleine.databinding.RecyclerViewAdminViewPartnershipBinding
import com.example.kleine.model.Partnership
import com.example.kleine.viewmodel.partnership.PartnershipViewModel
import com.example.kleine.viewmodel.user.UserViewModel
import com.google.firebase.storage.FirebaseStorage

interface OnPdfClickListener {
    fun onPdfClick(pdfUrl: String)
}
class AdminViewPartnershipFragment : Fragment(), OnPdfClickListener {
    private lateinit var binding: FragmentAdminViewPartnershipBinding
    private val partnershipViewModel: PartnershipViewModel by viewModels()
    private val userViewModel: UserViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
    override fun onPdfClick(pdfUrl: String) {
        partnershipViewModel.loadPdfIntoView(
            pdfUrl,
            binding.pdfView,
            binding.closePdfButton
        )
    }
    override fun onResume() {
        super.onResume()
        partnershipViewModel.fetchApprovedPartnerships()
    }




}
