package com.crafted.indiabcraft.data.model

data class Content(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val videoUrl: String?,
    val type: ContentType,
    val year: Int,
    val duration: String?,
    val rating: Float,
    val genres: List<String>,
    val cast: List<CastMember>,
    val isLive: Boolean = false,
    val progress: Float? = null
)

enum class ContentType {
    MOVIE,
    TV_SHOW,
    LIVE_TV,
    SPORTS,
    SHORT_VIDEO
}

data class CastMember(
    val name: String,
    val role: String,
    val imageUrl: String?
)

data class Channel(
    val id: String,
    val name: String,
    val logoUrl: String?,
    val category: String,
    val streamUrl: String,
    val isLive: Boolean = true
)

data class Match(
    val id: String,
    val team1: String,
    val team2: String,
    val sport: String,
    val status: String,
    val score: String,
    val streamUrl: String?,
    val startTime: Long?
)