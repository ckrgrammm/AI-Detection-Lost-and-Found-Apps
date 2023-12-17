package com.example.fyps.model

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "material")
data class Material(
//    @PrimaryKey(autoGenerate = true) // ID as auto increment
    var id: String = "",
    val desc: String = "",
    val name: String = "", // Course name
    val pass: Int = 0, // Number of students who passed this course/subject
    var rating: Double = 0.0,
    val category: String = "",
    val status: String = "", // Status (Available/Unavailable)
    var view: Long = 0,
    var claimed: Long = 0,
    var imageUrl: String = "", // URL for the course banner
    val partnershipsID: String = "",  // User's document ID
    val requirement: String = "", // Add this property
    val dateTime: String = "", // Add this line for the dateTime field
    val venue: String = "", // Add this line for the venue field
    var isUnique: Boolean = false, // This will be set based on the category
    var claimedBy: String = "", // Add this line to include the claimedBy field
    var hasBeenClaimed: Boolean = false // default to false



): Parcelable


@Parcelize
data class MaterialData(
    var id: String = "",
    var name: String = "",
    var desc: String = "",
    val category: String = "",
    var rating: Double = 0.0,
    var imageUrl: String = "",
    val status: String // Add this line

): Parcelable {
    constructor() : this("", "", "", "", 0.0, "","")
}

@Parcelize
data class MaterialEngageData(
    val name: String = "",
    val view: Long = 0,
    val enroll: Long = 0,
    val graduate: Long = 0,
    val imageUrl: String = ""
): Parcelable {
    constructor() : this("", 0,0,0,"")
}

@Parcelize
data class CourseDocument(
    val documentUrl: String=""
):Parcelable