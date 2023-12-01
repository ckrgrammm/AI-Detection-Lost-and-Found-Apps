package com.example.fyps.adapters.recyclerview

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fyps.R
import com.example.fyps.model.Material
import com.example.fyps.util.PokemonColorUtil

class MenuAdapter(
    private val categories: List<String>,
    private val context: Context,
    private val onCategoryClick: (String) -> Unit
) : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val categoryTextView: TextView = view.findViewById(R.id.textViewName)
        private val backgroundLayout: RelativeLayout = view.findViewById(R.id.relativeLayoutBackground) // Assuming this is the layout you want to color

        fun bind(category: String, onCategoryClick: (String) -> Unit, context: Context) {
            categoryTextView.text = category
            itemView.setOnClickListener { onCategoryClick(category) }

            // Use PokemonColorUtil to get the color for the category
            val colorUtil = PokemonColorUtil(context)
            val color = colorUtil.getCategoryColor(category)
            backgroundLayout.background.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_menu, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories[position]

        holder.bind(category, onCategoryClick, context)
    }

    override fun getItemCount() = categories.size
}
