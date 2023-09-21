package com.example.kleine.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "material")
data class Material(
    @PrimaryKey(autoGenerate = true) // ID as auto increment
    val id: Int = 0,
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
