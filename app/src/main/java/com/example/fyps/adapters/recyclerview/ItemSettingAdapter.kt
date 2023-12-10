package com.example.fyps.adapters.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.fyps.R
import com.example.fyps.model.Material
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemSettingAdapter(
    private var materials: List<Material>,
    private val onItemClicked: (Material) -> Unit
) : RecyclerView.Adapter<ItemSettingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item_setting, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val material = materials[position]
        holder.causeSymptomName.text = material.name

        // Load image asynchronously using coroutines
        CoroutineScope(Dispatchers.Main).launch {
            loadMaterialImage(holder.causeSymptomImg, material.imageUrl)
        }

        holder.itemView.setOnClickListener {
            // Call the callback with the clicked item
            onItemClicked(material)
        }
    }

    override fun getItemCount(): Int = materials.size

    fun updateItems(newItems: List<Material>) {
        materials = newItems
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val causeSymptomName: TextView = view.findViewById(R.id.cause_symptom_name)
        val causeSymptomImg: ImageView = view.findViewById(R.id.cause_symptom_img)
    }

    private suspend fun loadMaterialImage(imageView: ImageView, imageUrl: String) {
        try {
            val context = imageView.context
            val requestOptions = RequestOptions.circleCropTransform()
            Glide.with(context)
                .load(imageUrl)
                .apply(requestOptions)
                .into(imageView)
        } catch (e: Exception) {
            // Handle any exceptions here
        }
    }
}
