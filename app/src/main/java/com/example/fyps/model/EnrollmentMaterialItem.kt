package com.example.fyps.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EnrollmentMaterialItem(
    val enrollment: Enrollment,
    val material: Material
) : Parcelable
