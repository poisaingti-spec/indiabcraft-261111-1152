package com.crafted.indiabcraft.ui.livetv

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.crafted.indiabcraft.navigation.Screen
import com.crafted.indiabcraft.ui.components.ContentCard
import com.crafted.indiabcraft.ui.models.ContentItem

@Composable
fun LiveTVScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var selectedCategory by remember { mutableStateOf("All") }
    
    val categories = listOf("All", "News", "Sports", "Entertainment", "Music", "Kids")
    val liveChannels = listOf(
        ContentItem(id = "1", title = "News Live", thumbnailUrl = "", category = "News"),
        ContentItem(id = "2", title = "Sports HD", thumbnailUrl = "", category = "Sports"),
        ContentItem(id = "3", title = "Movies Plus", thumbnailUrl = "", category = "Entertainment"),
        ContentItem(id = "4", title = "Music TV", thumbnailUrl = "", category = "Music"),
        ContentItem(id = "5", title = "Kids Zone", thumbnailUrl = "", category = "Kids"),
        ContentItem(id = "6", title = "Documentary", thumbnailUrl = "", category = "Entertainment")
    )

    Scaffold(
        modifier = modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                text = "Live TV",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Category Selection Row
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 0.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(categories) { category ->
                    CategoryChip(
                        category = category,
                        isSelected = selectedCategory == category,
                        onClick = { selectedCategory = category }
                    )
                }
            }

            // Live Channels Grid
            Text(
                text = "Live Channels",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 24.dp, bottom = 12.dp)
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 0.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(liveChannels) { channel ->
                    ContentCard(
                        contentItem = channel,
                        onClick = { 
                            navController.navigate(Screen.Detail.createRoute(channel.id))
                        },
                        modifier = Modifier.padding(end = 4.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun CategoryChip(
    category: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    androidx.compose.material3.FilterChip(
        selected = isSelected,
        onClick = onClick,
        label = { Text(text = category) }
    )
}
