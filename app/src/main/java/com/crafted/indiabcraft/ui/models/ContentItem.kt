package com.crafted.indiabcraft.ui.models

data class ContentItem(
    val id: String,
    val title: String,
    val thumbnailUrl: String,
    val category: String = "",
    val description: String = "",
    val duration: String = "",
    val rating: Float = 0f,
    val year: Int = 0,
    val videoUrl: String = ""
)
