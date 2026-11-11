package com.crafted.indiabcraft.ui.livetv

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LiveTv
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.crafted.indiabcraft.theme.MaitisPurplePrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LiveTVScreen(
    navController: NavController
) {
    var selectedCategory by remember { mutableStateOf("All") }
    val categories = listOf("All", "Sports", "News", "Entertainment", "Kids", "Music")
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Live TV",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaitisPurplePrimary
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Category Filter
            LazyRow(
                modifier = Modifier.padding(vertical = 12.dp),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(categories) { category ->
                    FilterChip(
                        selected = selectedCategory == category,
                        onClick = { selectedCategory = category },
                        label = { Text(category) }
                    )
                }
            }
            
            // Channel Grid
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 150.dp),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(generateChannels()) { channel ->
                    ChannelCard(channel = channel)
                }
            }
        }
    }
}

data class Channel(
    val id: String,
    val name: String,
    val category: String,
    val isLive: Boolean = true
)

fun generateChannels(): List<Channel> {
    return listOf(
        Channel("1", "Sports 1", "Sports"),
        Channel("2", "Sports 2", "Sports"),
        Channel("3", "News 24", "News"),
        Channel("4", "News Live", "News"),
        Channel("5", "Movies HD", "Entertainment"),
        Channel("6", "Cinema Plus", "Entertainment"),
        Channel("7", "Kids TV", "Kids"),
        Channel("8", "Cartoon Net", "Kids"),
        Channel("9", "Music Hits", "Music"),
        Channel("10", "MTV", "Music"),
        Channel("11", "Cricket Live", "Sports"),
        Channel("12", "Football TV", "Sports")
    )
}

@Composable
fun ChannelCard(channel: Channel) {
    Box(
        modifier = Modifier
            .aspectRatio(16f / 10f)
            .clip(RoundedCornerShape(12.dp))
            .background(MaitisPurplePrimary.copy(alpha = 0.1f))
            .clickable { },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(12.dp)
        ) {
            Icon(
                imageVector = Icons.Default.LiveTv,
                contentDescription = null,
                tint = MaitisPurplePrimary,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = channel.name,
                style = MaterialTheme.typography.titleMedium,
                color = MaitisPurplePrimary,
                fontWeight = FontWeight.SemiBold
            )
            if (channel.isLive) {
                Spacer(modifier = Modifier.height(4.dp))
                Badge(
                    containerColor = Color.Red
                ) {
                    Text("LIVE", color = Color.White)
                }
            }
        }
    }
}