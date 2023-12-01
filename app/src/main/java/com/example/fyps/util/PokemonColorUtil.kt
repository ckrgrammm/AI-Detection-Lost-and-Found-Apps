package com.example.fyps.util

import android.content.Context
import android.util.Log
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.example.fyps.R
import java.util.Locale

class PokemonColorUtil(var context: Context) {

    @ColorInt
    fun getCategoryColor(category: String): Int {
        val lowerCaseCategory = category.toLowerCase(Locale.ROOT)
        Log.d("PokemonColorUtil", "Category: $lowerCaseCategory")

        val color = when (lowerCaseCategory) {
            "personal items" -> R.color.lightTeal
            "electronics" -> R.color.lightRed
            "other" -> R.color.lightYellow
            // Add more categories as needed
            else -> R.color.lightPurple // default color
        }
        Log.d("PokemonColorUtil", "Resolved color: $color")
        return convertColor(color)
    }

    @ColorInt
    fun convertColor(@ColorRes color: Int): Int {
        return ContextCompat.getColor(context, color)
    }
}
