package com.example.kleine.adapters.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kleine.R
import com.example.kleine.databinding.ProductLayoutRowBinding
import com.example.kleine.model.Material
import com.google.firebase.storage.FirebaseStorage

class MaterialAdapter : RecyclerView.Adapter<MaterialAdapter.MaterialViewHolder>() {

    var onItemClick: ((Material) -> Unit)? = null

    inner class MaterialViewHolder(val binding: ProductLayoutRowBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Material>() {
        override fun areItemsTheSame(oldItem: Material, newItem: Material): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Material, newItem: Material): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaterialViewHolder {
        Log.d("MaterialAdapter", "onCreateViewHolder called")
        return MaterialViewHolder(
            ProductLayoutRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: MaterialViewHolder, position: Int) {
        Log.d("MaterialAdapter", "onBindViewHolder called for position $position")

        val material = differ.currentList[position]
        holder.binding.apply {
            productModel = material  // This binds your XML views to your data model

            val storage = FirebaseStorage.getInstance()
            val storageRef = storage.reference

            // Check if imageUrl is not empty
            if (material.imageUrl.isNotEmpty()) {
                // Construct the dynamic path based on the material's image URL
                val pathToImage = "materialImages/${material.imageUrl}"

                // Log the dynamic path and material image URL for debugging
                Log.d("MaterialAdapter", "Dynamic Path to image: $pathToImage")
                Log.d("MaterialAdapter", "Material image URL: ${material.imageUrl}")

                // Use the dynamic path to reference the image in Firebase Storage
                val pathReference = storageRef.child(pathToImage)

                // Attempt to download the image using the dynamic path
                pathReference.downloadUrl.addOnSuccessListener { uri ->
                    Log.d("MaterialAdapter", "Successfully fetched URI: $uri")
                    Glide.with(holder.itemView).load(uri).into(imageView)
                }.addOnFailureListener { exception ->
                    Log.e("MaterialAdapter", "Failed to load image", exception)
                    imageView.setImageResource(R.drawable.default_book_logo)  // Set a default image
                }
            } else {
                imageView.setImageResource(R.drawable.default_book_logo)  // Set a default image
            }
        }

        // Set an onClick listener for the item
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(differ.currentList[position])
        }
    }









    override fun getItemCount(): Int {
        val count = differ.currentList.size
        Log.d("MaterialAdapter", "Item count: $count")
        return count
    }
}
