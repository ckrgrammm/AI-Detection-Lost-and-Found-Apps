package com.example.fyps.model

import java.time.LocalDateTime


data class News(
    var id: String = "",
    var previewText: String = "",
    var previewImage : String = "",
    var shortDesc: String = "",
    var longDesc: String = "",
    var publishDate : String = "",
    var imageUrls: List<String> = listOf()
)
