package com.example.fyps.model

data class Comment(
    var id: String = "",
    val comment: String = "",
    val commentDate: String = "",
    val materialId: String = "",
    val replyComment: String? = "",
    val replyDate: String? = "",
    val replyPartnerId: String? = "",
    val userId: String = "",
    val rating: Long = 0
)

data class CommentWithUserDetails(
    val comment: Comment,
    val userName: String?,
    val userImage: String?,
    val adminName: String?,  // Name of the admin who replied
    val adminImage: String?  // Image URL of the admin who replied
)
