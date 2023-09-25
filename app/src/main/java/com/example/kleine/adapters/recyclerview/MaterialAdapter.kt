package com.example.kleine.adapters.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kleine.R
import com.example.kleine.databinding.ProductLayoutRowBinding
import com.example.kleine.fragments.shopping.HomeFragmentDirections
import com.example.kleine.model.Material
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
        Log.d("MaterialAdapter", "onCreateViewHolder called")
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

            if (material.imageUrl.isNotEmpty()) {
                val pathToImage = "materialImages/${material.imageUrl}"
                Log.d("MaterialAdapter", "Dynamic Path to image: $pathToImage")
                Log.d("MaterialAdapter", "Material image URL: ${material.imageUrl}")


                val pathReference = storageRef.child(pathToImage)

                pathReference.downloadUrl.addOnSuccessListener { uri ->
                    Log.d("MaterialAdapter", "Successfully fetched URI: $uri")
                    Glide.with(holder.itemView).load(uri).into(imageView)
                }.addOnFailureListener { exception ->
                    Log.e("MaterialAdapter", "Failed to load image", exception)
                    imageView.setImageResource(R.drawable.default_book_logo)
                }
            } else {
                imageView.setImageResource(R.drawable.default_book_logo)
                Log.e("MaterialAdapter", "Failed to load image because of empty")
            }
        }
        // Set an onClick listener for the item
        holder.itemView.setDebouncedOnClickListener {
            Log.d("MaterialAdapter", "Navigating with Material ID: ${material.id}")
            val action = HomeFragmentDirections.actionHomeFragmentToMaterialDetailsFragment(material)
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        val count = differ.currentList.size
        Log.d("MaterialAdapter", "Item count: $count")
        return count
    }
}
