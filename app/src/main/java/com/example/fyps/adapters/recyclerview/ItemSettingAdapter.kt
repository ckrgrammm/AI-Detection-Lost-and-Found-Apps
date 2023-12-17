package com.example.fyps.adapters.recyclerview

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
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
    private val onItemClick: ((Material) -> Unit)? = null

) : RecyclerView.Adapter<ItemSettingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item_setting, parent, false)
        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val material = materials[position]
        holder.causeSymptomName.text = material.name

        // Load image asynchronously using coroutines
        CoroutineScope(Dispatchers.Main).launch {
            loadMaterialImage(holder.causeSymptomImg, material.imageUrl)
        }

        // Reference to the card view
        val cardView = holder.itemView.findViewById<CardView>(R.id.cause_symptom_layout)

        if (material.hasBeenClaimed) {
            // Set the card background color to grey if the item has been claimed
            cardView.setCardBackgroundColor(
                holder.itemView.context.getColor(R.color.grey) // Make sure you have a grey color defined in colors.xml
            )
            // Disable click events for this item
            cardView.isEnabled = false
        } else {
            // Set the card background color to white if the item has not been claimed
            cardView.setCardBackgroundColor(
                holder.itemView.context.getColor(android.R.color.white)
            )
            // Enable click events for this item
            cardView.isEnabled = true
            holder.itemView.setOnClickListener {
                onItemClick?.invoke(material)
            }
        }

        // Optionally, change the opacity or other properties of the card view to indicate it's disabled
        cardView.alpha = if (material.hasBeenClaimed) 0.5f else 1.0f
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
