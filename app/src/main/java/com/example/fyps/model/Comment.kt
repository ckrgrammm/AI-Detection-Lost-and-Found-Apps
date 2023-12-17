package com.example.fyps.model

import com.google.firebase.firestore.PropertyName
import java.io.Serializable


data class Comment(
    @PropertyName("id") var id: String = "",
    @PropertyName("comment") val comment: String = "",
    @PropertyName("commentDate") val commentDate: String = "",
    @PropertyName("materialId") val materialId: String = "",
    @PropertyName("replyComment") val replyComment: String? = null,
    @PropertyName("replyDate") val replyDate: String? = null,
    @PropertyName("replyPartnerId") val replyPartnerId: String? = null,
    @PropertyName("userId") val userId: String = "",
    @PropertyName("rating") val rating: Long = 0
) : Serializable

data class CommentWithUserDetails(
    // Provide default values for all properties
    val comment: Comment = Comment(),
    val userName: String? = null,
    val userImage: String? = null,
    val adminName: String? = null,
    val adminImage: String? = null
)
