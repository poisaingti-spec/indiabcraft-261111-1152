package com.crafted.indiabcraft.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.crafted.indiabcraft.navigation.Screen
import com.crafted.indiabcraft.theme.MaitisPurplePrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController
) {
    var selectedTab by remember { mutableStateOf(0) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "IndiabCraft",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaitisPurplePrimary
                ),
                actions = {
                    IconButton(onClick = { /* Search */ }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = { /* Notifications */ }) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notifications",
                            tint = Color.White
                        )
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") }
                )
                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    icon = { Icon(Icons.Default.LiveTv, contentDescription = "Live TV") },
                    label = { Text("Live TV") }
                )
                NavigationBarItem(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    icon = { Icon(Icons.Default.Movie, contentDescription = "Movies") },
                    label = { Text("Movies") }
                )
                NavigationBarItem(
                    selected = selectedTab == 3,
                    onClick = { selectedTab = 3 },
                    icon = { Icon(Icons.Default.Sports, contentDescription = "Sports") },
                    label = { Text("Sports") }
                )
                NavigationBarItem(
                    selected = selectedTab == 4,
                    onClick = { selectedTab = 4 },
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") }
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            // Featured Banner
            item {
                FeaturedBanner()
            }
            
            // Continue Watching
            item {
                SectionHeader(title = "Continue Watching")
            }
            item {
                ContinueWatchingRow()
            }
            
            // Trending Now
            item {
                SectionHeader(title = "Trending Now")
            }
            item {
                TrendingContentRow()
            }
            
            // Live Channels
            item {
                SectionHeader(title = "Live Channels")
            }
            item {
                LiveChannelsRow()
            }
            
            // Sports Highlights
            item {
                SectionHeader(title = "Sports Highlights")
            }
            item {
                SportsHighlightsRow()
            }
            
            // New Releases
            item {
                SectionHeader(title = "New Releases")
            }
            item {
                NewReleasesRow()
            }
        }
    }
}

@Composable
fun FeaturedBanner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaitisPurplePrimary)
    ) {
        AsyncImage(
            model = "https://via.placeholder.com/800x400",
            contentDescription = "Featured Content",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    androidx.compose.ui.graphics.Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.7f)
                        )
                    )
                )
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {
            Text(
                text = "Featured Movie",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Watch the latest blockbuster now",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White.copy(alpha = 0.8f)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = MaitisPurplePrimary
                )
            ) {
                Text("Watch Now")
            }
        }
    }
}

@Composable
fun SectionHeader(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )
        TextButton(onClick = { }) {
            Text("See All")
        }
    }
}

@Composable
fun ContinueWatchingRow() {
    val items = listOf("1", "2", "3", "4")
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items) { item ->
            ContentCard(
                imageUrl = "https://via.placeholder.com/160x240",
                title = "Content $item",
                progress = (0.3..0.9).random()
            )
        }
    }
}

@Composable
fun TrendingContentRow() {
    val items = listOf("1", "2", "3", "4", "5")
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items) { item ->
            ContentCard(
                imageUrl = "https://via.placeholder.com/160x240",
                title = "Trending $item"
            )
        }
    }
}

@Composable
fun LiveChannelsRow() {
    val channels = listOf("Sports 1", "News 24", "Movies HD", "Kids TV", "Music")
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(channels) { channel ->
            LiveChannelCard(channelName = channel)
        }
    }
}

@Composable
fun SportsHighlightsRow() {
    val sports = listOf("Cricket", "Football", "Tennis", "Basketball")
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(sports) { sport ->
            SportsCard(sportName = sport)
        }
    }
}

@Composable
fun NewReleasesRow() {
    val items = listOf("1", "2", "3", "4", "5")
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items) { item ->
            ContentCard(
                imageUrl = "https://via.placeholder.com/160x240",
                title = "New Release $item"
            )
        }
    }
}

@Composable
fun ContentCard(
    imageUrl: String,
    title: String,
    progress: Float? = null
) {
    Column(
        modifier = Modifier
            .width(140.dp)
            .clickable { }
    ) {
        Box(
            modifier = Modifier
                .width(140.dp)
                .height(210.dp)
                .clip(RoundedCornerShape(12.dp))
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            if (progress != null) {
                LinearProgressIndicator(
                    progress = progress,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .height(4.dp),
                    color = MaitisPurplePrimary,
                    trackColor = Color.White.copy(alpha = 0.3f)
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 2
        )
    }
}

@Composable
fun LiveChannelCard(channelName: String) {
    Box(
        modifier = Modifier
            .width(120.dp)
            .height(80.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(MaitisPurplePrimary.copy(alpha = 0.2f))
            .clickable { },
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = Icons.Default.LiveTv,
                contentDescription = null,
                tint = MaitisPurplePrimary,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = channelName,
                style = MaterialTheme.typography.labelMedium,
                color = MaitisPurplePrimary
            )
        }
    }
}

@Composable
fun SportsCard(sportName: String) {
    Box(
        modifier = Modifier
            .width(140.dp)
            .height(100.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(MaitisPurplePrimary.copy(alpha = 0.2f))
            .clickable { },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = sportName,
            style = MaterialTheme.typography.titleMedium,
            color = MaitisPurplePrimary,
            fontWeight = FontWeight.SemiBold
        )
    }
}