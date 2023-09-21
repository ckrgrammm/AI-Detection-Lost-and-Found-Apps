package com.example.kleine.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

enum class PartnershipStatus {
    pending, approved, rejected, quit
}
@Parcelize
data class Partnership(
    var id: String = "",
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
    constructor() : this("","", "", "", "", "", "", PartnershipStatus.pending, "", "", "")
}
