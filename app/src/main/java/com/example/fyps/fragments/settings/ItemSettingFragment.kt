package com.example.fyps.fragments.settings

import android.app.AlertDialog
import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fyps.R
import com.example.fyps.adapters.recyclerview.ItemSettingAdapter
import com.example.fyps.model.Material
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ItemSettingFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ItemSettingAdapter
    private var materialItems: MutableList<Material> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_item_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val actionType = requireArguments().getString("actionType", "editItem")
        val labelText = view.findViewById<TextView>(R.id.labelText)
        labelText.text = if (actionType == "editItem") {
            getString(R.string.all_reported_item)
        } else {
            getString(R.string.select_claimed_item)
        }

        recyclerView = view.findViewById(R.id.rvItemSettings)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = ItemSettingAdapter(materialItems) { material ->
            when (actionType) {
                "editItem" -> {
                    val action = ItemSettingFragmentDirections.actionItemSettingMainFragmentToItemSettingFragmentToEditMaterialFragment(material.id)
                    findNavController().navigate(action)
                }
                "changeStatus" -> {
                    showClaimedUsersDialog(material)

                }
            }
        }
        recyclerView.adapter = adapter

        fetchReportedMaterials()
    }
    private fun fetchReportedMaterials() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        userId?.let { uid ->
            FirebaseFirestore.getInstance().collection("Materials")
                .whereEqualTo("partnershipsID", uid)
                .get()
                .addOnSuccessListener { documents ->
                    val materials = documents.mapNotNull { it.toObject(Material::class.java) }
                    materialItems.clear()
                    materialItems.addAll(materials)
                    adapter.updateItems(materialItems)
                }
                .addOnFailureListener { e ->

                }
        }
    }

    private fun showClaimedUsersDialog(material: Material) {
        if (material.hasBeenClaimed) {
            Toast.makeText(context, "This item has already been claimed.", Toast.LENGTH_SHORT).show()
            return
        }
        FirebaseFirestore.getInstance().collection("enrollments")
            .whereEqualTo("materialId", material.id)
            .get()
            .addOnSuccessListener { documents ->
                if (documents.isEmpty) {
                    Toast.makeText(context, "No users have claimed this item.", Toast.LENGTH_SHORT).show()
                    return@addOnSuccessListener
                }
                val userIds = documents.mapNotNull { it.getString("userId") }
                fetchUserDetailsAndShowDialog(material, userIds)
            }
            .addOnFailureListener {
            }
    }

    private fun fetchUserDetailsAndShowDialog(material: Material, userIds: List<String>) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_user_details, null)
        val usersLayout = dialogView.findViewById<LinearLayout>(R.id.layoutClaimedUser)
        usersLayout.orientation = LinearLayout.VERTICAL
        usersLayout.removeAllViews()

        val titleView = TextView(context).apply {
            text = "Select The Owner"
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
            setTypeface(typeface, Typeface.BOLD)
            setPadding(20, 40, 20, 40)
        }
        usersLayout.addView(titleView)

        val alertDialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setNegativeButton("Cancel", null)
            .create()

        userIds.forEach { userId ->
            FirebaseFirestore.getInstance().collection("users").document(userId)
                .get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        val userView = LayoutInflater.from(context).inflate(R.layout.item_user, usersLayout, false)
                        val firstName = document.getString("firstName") ?: "N/A"
                        val lastName = document.getString("lastName") ?: "N/A"
                        val profileImageUrl = document.getString("imagePath") ?: ""

                        val imageView = userView.findViewById<ImageView>(R.id.userImage)
                        val nameView = userView.findViewById<TextView>(R.id.firstName)

                        nameView.text = "$firstName $lastName"
                        if (profileImageUrl.isNotBlank()) {
                            Glide.with(this)
                                .load(profileImageUrl)
                                .error(R.drawable.ic_default_profile_picture)
                                .into(imageView)
                        } else {
                            imageView.setImageResource(R.drawable.ic_default_profile_picture)
                        }

                        userView.setOnClickListener {
                            updateMaterialStatus(material, userId)
                            alertDialog.dismiss()
                        }
                        usersLayout.addView(userView)
                    }
                }
                .addOnFailureListener {
                }
        }
        alertDialog.show()
    }


    private fun updateMaterialStatus(material: Material, userId: String) {
        if (material.hasBeenClaimed) return

        val materialRef = FirebaseFirestore.getInstance().collection("Materials").document(material.id)
        materialRef
            .update(mapOf(
                "status" to "Status : Claimed",
                "claimedBy" to userId,
                "hasBeenClaimed" to true
            ))
            .addOnSuccessListener {
                checkAndUpdateUserPoints()
                Toast.makeText(context, "Item has been claimed successfully.", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(context, "Failed to update item status: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
        adapter.notifyDataSetChanged()
    }

    private fun checkAndUpdateUserPoints() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val userRef = FirebaseFirestore.getInstance().collection("users").document(userId)
            userRef.get().addOnSuccessListener { document ->
                if (document.exists()) {
                    val userStatus = document.getString("status")
                    val pointsToAdd = if (userStatus == "REPORTERS") 2 else 1

                    val currentPoints = document.getLong("points") ?: 0
                    userRef.update("points", currentPoints + pointsToAdd)
                        .addOnSuccessListener {
                            if (isAdded) {
                                Toast.makeText(context, "Points updated successfully", Toast.LENGTH_SHORT).show()
                            }
                        }
                        .addOnFailureListener {
                            if (isAdded) {
                                Toast.makeText(context, "Failed to update points", Toast.LENGTH_SHORT).show()
                            }
                        }
                }
            }.addOnFailureListener {
                if (isAdded) {
                    Toast.makeText(context, "Failed to fetch user details", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            if (isAdded) {
                Toast.makeText(context, "Error: User ID not found", Toast.LENGTH_SHORT).show()
            }
        }
    }




}
