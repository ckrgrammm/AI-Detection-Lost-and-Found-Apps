package com.example.kleine.fragments.partnership

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kleine.R
import com.example.kleine.databinding.FragmentPartnershipViewMaterialBinding
import com.example.kleine.databinding.RecyclerViewMaterialDataBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class PartnershipViewMaterialFragment : Fragment() {

    val TAG = "PartnershipViewMaterialFragment"
    private lateinit var binding: FragmentPartnershipViewMaterialBinding
    private lateinit var materialAdapter: MaterialAdapter
//    val storageRef = FirebaseStorage.getInstance().reference
    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView (
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPartnershipViewMaterialBinding.inflate(inflater, container, false)

        // Initialize the adapter and attach it to the RecyclerView
        materialAdapter = MaterialAdapter(listOf())
        binding.partnershipData.adapter = materialAdapter

        // Fetch materials from Firestore
        fetchMaterialsFromFirestore()

        return binding.root
    }

    // Data class to represent a material
    data class MaterialItem(
        val id: String,
        val name: String,
        val desc: String,
        val requirement: String,
        val rating: Double,
        val imageUrl: String
    )
    inner class MaterialViewHolder(private val itemBinding: RecyclerViewMaterialDataBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(materialItem: MaterialItem) {
            // Set up UI elements here
            itemBinding.materialName.text = materialItem.name
            itemBinding.materialDesc.text = materialItem.desc
            itemBinding.materialRequirement.text = "Requirement: ${materialItem.requirement}"
            itemBinding.ratingBar.rating = materialItem.rating.toFloat()
            val storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(materialItem.imageUrl)
            storageReference.downloadUrl.addOnSuccessListener { uri ->
                Glide.with(itemBinding.root.context)
                    .load(uri.toString())
                    .into(itemBinding.image)
            }
            onViewMaterialClick(itemBinding, materialItem.id)
            setupPopupMenu(itemBinding.threeDotsImage)
        }
    }


    // RecyclerView.Adapter for the list of materials
    inner class MaterialAdapter(var materialList: List<MaterialItem>) : RecyclerView.Adapter<MaterialViewHolder>() {
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
                    R.id.edit_material -> {
                        // Start EditMaterialActivity when Edit Material is clicked
//                        val editIntent = Intent(requireContext(), EditMaterialActivity::class.java)
//                        startActivity(editIntent)
                        true
                    }
                    R.id.disable_material -> {
                        // Show confirmation dialog when Make it Disabled is clicked
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
            .setPositiveButton(android.R.string.yes) { dialog, which ->
                //TODO: Implement your logic to disable the material here.
            }
            .setNegativeButton(android.R.string.no, null)
            .show()
    }
    private fun fetchMaterialsFromFirestore() {
        db.collection("Materials")
            .get()
            .addOnSuccessListener { result ->
                val materialList = ArrayList<MaterialItem>()
                for (document in result) {
                    val id = document.id
                    val materialName = document.getString("name") ?: ""
                    val description = document.getString("desc") ?: ""
                    val requirement = document.getString("requirement") ?: ""
                    val rating = document.getDouble("rating") ?: 0.0
                    val imageUrl = document.getString("imageUrl") ?: ""
                    materialList.add(MaterialItem(id, materialName, description, requirement, rating, imageUrl))
                }

                materialAdapter.materialList = materialList
                materialAdapter.notifyDataSetChanged()

            }
    }



}