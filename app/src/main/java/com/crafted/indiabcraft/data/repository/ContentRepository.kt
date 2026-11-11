package com.crafted.indiabcraft.data.repository

import com.crafted.indiabcraft.data.model.Content
import com.crafted.indiabcraft.data.model.ContentType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ContentRepository {

    fun getFeaturedContent(): Flow<List<Content>> = flow {
        emit(generateFeaturedContent())
    }

    fun getTrendingContent(): Flow<List<Content>> = flow {
        emit(generateTrendingContent())
    }

    fun getContinueWatching(): Flow<List<Content>> = flow {
        emit(generateContinueWatching())
    }

    fun getLiveChannels(): Flow<List<Content>> = flow {
        emit(generateLiveChannels())
    }

    fun getSportsContent(): Flow<List<Content>> = flow {
        emit(generateSportsContent())
    }

    fun searchContent(query: String): Flow<List<Content>> = flow {
        emit(generateSearchResults(query))
    }

    private fun generateFeaturedContent(): List<Content> {
        return listOf(
            Content(
                id = "1",
                title = "Featured Movie",
                description = "Watch the latest blockbuster now",
                imageUrl = "https://via.placeholder.com/800x400",
                videoUrl = null,
                type = ContentType.MOVIE,
                year = 2024,
                duration = "2h 15m",
                rating = 8.5f,
                genres = listOf("Action", "Adventure"),
                cast = emptyList()
            )
        )
    }

    private fun generateTrendingContent(): List<Content> {
        return (1..10).map { i ->
            Content(
                id = "trending_$i",
                title = "Trending Content $i",
                description = "Description for trending content $i",
                imageUrl = "https://via.placeholder.com/300x450",
                videoUrl = null,
                type = if (i % 2 == 0) ContentType.MOVIE else ContentType.TV_SHOW,
                year = 2024,
                duration = if (i % 2 == 0) "2h ${i * 5}m" else "Season ${i}",
                rating = (4.0f..5.0f).random(),
                genres = listOf("Drama", "Thriller"),
                cast = emptyList()
            )
        }
    }

    private fun generateContinueWatching(): List<Content> {
        return (1..5).map { i ->
            Content(
                id = "continue_$i",
                title = "Continue Watching $i",
                description = "Pick up where you left off",
                imageUrl = "https://via.placeholder.com/300x450",
                videoUrl = null,
                type = ContentType.TV_SHOW,
                year = 2024,
                duration = "S${i}:E${i * 2}",
                rating = 4.5f,
                genres = listOf("Drama"),
                cast = emptyList(),
                progress = (0.1f..0.9f).random()
            )
        }
    }

    private fun generateLiveChannels(): List<Content> {
        return listOf(
            Content(
                id = "live_1",
                title = "Sports 1",
                description = "Live sports channel",
                imageUrl = "https://via.placeholder.com/300x200",
                videoUrl = null,
                type = ContentType.LIVE_TV,
                year = 2024,
                duration = null,
                rating = 4.0f,
                genres = listOf("Sports"),
                cast = emptyList(),
                isLive = true
            )
        )
    }

    private fun generateSportsContent(): List<Content> {
        return listOf(
            Content(
                id = "sports_1",
                title = "Cricket Live",
                description = "Live cricket match",
                imageUrl = "https://via.placeholder.com/300x200",
                videoUrl = null,
                type = ContentType.SPORTS,
                year = 2024,
                duration = null,
                rating = 4.8f,
                genres = listOf("Cricket"),
                cast = emptyList(),
                isLive = true
            )
        )
    }

    private fun generateSearchResults(query: String): List<Content> {
        return (1..5).map { i ->
            Content(
                id = "search_$i",
                title = "$query Result $i",
                description = "Search result for $query",
                imageUrl = "https://via.placeholder.com/300x450",
                videoUrl = null,
                type = ContentType.MOVIE,
                year = 2024,
                duration = "1h ${i * 10}m",
                rating = (3.5f..5.0f).random(),
                genres = listOf("Action"),
                cast = emptyList()
            )
        }
    }
}