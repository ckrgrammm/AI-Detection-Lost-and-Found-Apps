package com.example.fyps.adapters.recyclerview

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fyps.R
import com.example.fyps.databinding.ProductLayoutRowBinding
import com.example.fyps.fragments.shopping.FragmentCategoryDetails
import com.example.fyps.fragments.shopping.FragmentCategoryDetailsDirections
import com.example.fyps.fragments.shopping.HomeFragmentDirections
import com.example.fyps.model.Material
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class MaterialAdapter : RecyclerView.Adapter<MaterialAdapter.MaterialViewHolder>() {

    var onItemClick: ((Material) -> Unit)? = null

    inner class MaterialViewHolder(val binding: ProductLayoutRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }


    private val diffCallback = object : DiffUtil.ItemCallback<Material>() {
        override fun areItemsTheSame(oldItem: Material, newItem: Material): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Material, newItem: Material): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaterialViewHolder {
        return MaterialViewHolder(
            ProductLayoutRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    // Define a helper function for debounced clicks
    fun View.setDebouncedOnClickListener(debounceTime: Long = 500L, onClick: (view: View) -> Unit) {
        var lastClickTime = 0L
        this.setOnClickListener {
            if (System.currentTimeMillis() - lastClickTime >= debounceTime) {
                onClick(it)
                lastClickTime = System.currentTimeMillis()
            }
        }
    }

    override fun onBindViewHolder(holder: MaterialViewHolder, position: Int) {
        Log.d("MaterialAdapter", "onBindViewHolder called for position $position")



        val material = differ.currentList[position]
        holder.binding.apply {
            productModel = material

            val storage = FirebaseStorage.getInstance()
            val storageRef = storage.reference

            // Set the color and clickability based on the status
            if (material.status == "Status : Lost") {
                cardView2.setCardBackgroundColor(Color.parseColor("#AAFF00")) // Green color
                cardView2.isClickable = true
                cardView2.isFocusable = true
                productCard.setCardBackgroundColor(Color.WHITE) // Set the main CardView to white

                cardView2.invalidate() // force redraw for cardView2
                productCard.invalidate() // force redraw for productCard

            } else if (material.status == "Status : Claimed") {
                cardView2.setCardBackgroundColor(Color.parseColor("#FF0000")) // Red color
                cardView2.isClickable = false
                cardView2.isFocusable = false
                productCard.setCardBackgroundColor(Color.parseColor("#D3D3D3")) // Set the main CardView to bright grey

                cardView2.invalidate() // force redraw for cardView2
                productCard.invalidate() // force redraw for productCard
            }

            if (material.imageUrl.isNotEmpty()) {
                val pathReference = storage.getReferenceFromUrl(material.imageUrl)
                pathReference.downloadUrl.addOnSuccessListener { uri ->
                    Log.d("MaterialAdapter", "Successfully fetched URI: $uri")
                    Glide.with(holder.itemView).load(uri).into(imageView)
                }.addOnFailureListener { exception ->
                    Log.e("MaterialAdapter", "Failed to load image", exception)
                    imageView.setImageResource(R.drawable.default_book_logo)
                }
                imageView.invalidate() // force redraw for imageView
            } else {
                imageView.setImageResource(R.drawable.default_book_logo)
                imageView.invalidate() // force redraw for imageView
                Log.e("MaterialAdapter", "Failed to load image because of empty")
            }


        }

        // Set an onClick listener for the item
        if (material.status == "Status : Lost") {
            holder.itemView.setDebouncedOnClickListener {
                // Increment view count
                incrementViewCount(material.id)
                val action = FragmentCategoryDetailsDirections.actionFragmentCategoryDetailsToMaterialDetailsFragment(material)
                it.findNavController().navigate(action)
                Log.d("MaterialAdapter", "Navigating with Material ID: ${material.id}")

            }
        }

    }



    private fun incrementViewCount(materialId: String) {
        val firestore = FirebaseFirestore.getInstance()
        val materialRef = firestore.collection("Materials").document(materialId)

        firestore.runTransaction { transaction ->
            val snapshot = transaction.get(materialRef)
            val newViewValue = snapshot.getLong("view")?.plus(1) ?: 1L
            transaction.update(materialRef, "view", newViewValue)
        }.addOnSuccessListener {
            Log.d("MaterialAdapter", "Successfully incremented view count.")
        }.addOnFailureListener { exception ->
            Log.w("MaterialAdapter", "Error incrementing view count.", exception)
        }
    }

    override fun getItemCount(): Int {
        val count = differ.currentList.size
        Log.d("MaterialAdapter", "Item count: $count")
        return count
    }
}