package com.example.kleine.fragments.partnership

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kleine.R
import com.example.kleine.databinding.FragmentPartnershipViewMaterialBinding
import com.example.kleine.databinding.RecyclerViewMaterialDataBinding
import com.example.kleine.model.MaterialData
import com.example.kleine.viewmodel.material.MaterialViewModel
import com.google.firebase.storage.FirebaseStorage

class PartnershipViewMaterialFragment : Fragment() {

    val TAG = "PartnershipViewMaterialFragment"
    private lateinit var binding: FragmentPartnershipViewMaterialBinding
    private val materialViewModel: MaterialViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPartnershipViewMaterialBinding.inflate(inflater, container, false)

        val materialAdapter = MaterialAdapter(listOf())
        binding.materialData.adapter = materialAdapter
        // Observe the material list LiveData from the ViewModel
        materialViewModel.materialList.observe(viewLifecycleOwner, Observer { materials ->
            // Update the adapter's materialList when the LiveData changes
            materialAdapter.materialList = materials
            materialAdapter.notifyDataSetChanged()
        })
        materialViewModel.fetchMaterialsData()

        return binding.root
    }

    inner class MaterialViewHolder(private val itemBinding: RecyclerViewMaterialDataBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(material: MaterialData) {
            itemBinding.materialName.text = material.name
            itemBinding.materialDesc.text = material.desc
            itemBinding.materialRequirement.text = "Requirement: ${material.requirement}"
            itemBinding.ratingBar.rating = material.rating.toFloat()

            val storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(material.imageUrl)
            storageReference.downloadUrl.addOnSuccessListener { uri ->
                Glide.with(itemBinding.root.context)
                    .load(uri.toString())
                    .into(itemBinding.image)
            }

            onViewMaterialClick(itemBinding, material.id)
            setupPopupMenu(itemBinding.threeDotsImage)
        }
    }

    inner class MaterialAdapter(var materialList: List<MaterialData>) : RecyclerView.Adapter<MaterialViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaterialViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = RecyclerViewMaterialDataBinding.inflate(inflater, parent, false)
            return MaterialViewHolder(itemBinding)
        }

        override fun onBindViewHolder(holder: MaterialViewHolder, position: Int) {
            holder.bind(materialList[position])
        }

        override fun getItemCount(): Int {
            return materialList.size
        }
    }

    private fun onViewMaterialClick(itemBinding: RecyclerViewMaterialDataBinding, id: String) {
        itemBinding.materialViewData.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("documentId", id)
            findNavController().navigate(R.id.action_partnershipViewMaterialFragment_to_partnershipViewMaterialDetailFragment, bundle)
        }
    }

    private fun setupPopupMenu(threeDotsImageView: ImageView) {
        threeDotsImageView.setOnClickListener {
            val popupMenu = PopupMenu(requireContext(), it)
            popupMenu.menuInflater.inflate(R.menu.popup, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.edit_material -> true
                    R.id.disable_material -> {
                        showConfirmationDialog()
                        true
                    }
                    else -> false
                }
            }
            popupMenu.show()
        }
    }

    private fun showConfirmationDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Disable Material")
            .setMessage("Are you sure you want to disable this material?")
            .setPositiveButton(android.R.string.yes) { _, _ ->
                // Implement your logic to disable the material here.
            }
            .setNegativeButton(android.R.string.no, null)
            .show()
    }
}
