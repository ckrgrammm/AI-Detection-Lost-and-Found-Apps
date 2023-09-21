package com.example.kleine.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

enum class PartnershipStatus {
    pending, accepted, rejected
}
@Parcelize
data class Partnership(
    var userId: String,
    var instiName: String,
    var instiType: String,
    var location: String,
    var contactNum: String,
    var reason: String,
    var status: PartnershipStatus = PartnershipStatus.pending,
    var rejectReason: String = "",
    var documentation: String = "",
    var documentationName: String = ""
): Parcelable {
    constructor() : this("", "", "", "", "", "", PartnershipStatus.pending, "", "", "")
}

