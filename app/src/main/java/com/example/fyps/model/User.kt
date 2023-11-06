package com.example.fyps.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

enum class Status {
    USERS, FINDERS, REPORTERS,PARTNERS, ADMINS
}

@Parcelize
data class User(
    var firstName: String,
    var lastName: String,
    var email: String,
    var imagePath: String = "",
    var status: Status = Status.USERS // Default status set to USERS
): Parcelable {
    constructor() : this("","","","")
}

data class UserDetails(
    val userName: String,
    val userImage: String?
)
