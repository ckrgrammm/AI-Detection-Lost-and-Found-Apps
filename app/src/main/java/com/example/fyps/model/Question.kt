package com.example.fyps.model

import android.os.Parcelable
import java.io.Serializable

data class Question(
    val id: String = "",
    val question: String = "",
    val answer: String = "",
    val setBy: String = "",
    var collectionName: String = "", // To store the collection name
    val itemType: String = ""

) : Serializable {
    // Additional logic or methods can go here
}