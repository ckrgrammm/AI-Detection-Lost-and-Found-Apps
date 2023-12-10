package com.example.fyps.fragments.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
        recyclerView = view.findViewById(R.id.rvItemSettings)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = ItemSettingAdapter(materialItems) { material ->
            // Here you can handle the item click event
            // For example, navigate to another fragment with the selected material
            val action = ItemSettingFragmentDirections.actionItemSettingMainFragmentToItemSettingFragmentToEditMaterialFragment(material.id)
            findNavController().navigate(action)
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
                    // Log or handle the error
                }
        }
    }
}
