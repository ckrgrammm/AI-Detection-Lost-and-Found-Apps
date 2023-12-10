package com.example.fyps.model

import java.time.LocalDateTime

data class News(
    var title: String = "",
//    var publicationDate: LocalDateTime = LocalDateTime.now(),
    var previewText: String = "",
    var shortDesc: String = "",
    var longDesc: String = "",
    var imageUrls: List<String> = listOf() // This now allows for multiple image URLs.
)
