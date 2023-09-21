package com.example.kleine.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "material")
data class Material(
    @PrimaryKey(autoGenerate = true) // ID as auto increment
    val desc: String = "",
    val name: String = "", // Course name
    val pass: Int = 0, // Number of students who passed this course/subject
    val rating: Float = 0f, // Rating (1 - 5)
    val requirement: String = "", // Requirement (e.g., Free)
    val status: String = "", // Status (Available/Unavailable)
    val view: Int = 0, // Number of students who viewed this course/subject
    val imageUrl: String = "", // URL for the course banner
    val enroll: Int = 0, // Number of students enrolled in this course/subject
    val partnershipsID: String? = null  // partnership ID

)
@Parcelize
data class MaterialData(
    var id: String = "",
    var name: String = "",
    var desc: String = "",
    var requirement: String = "",
    var rating: Double = 0.0,
    var imageUrl: String = ""
): Parcelable {
    constructor() : this("", "", "", "", 0.0, "")
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