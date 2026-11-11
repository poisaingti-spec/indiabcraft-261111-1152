package com.crafted.indiabcraft.ui.movies

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
import com.crafted.indiabcraft.theme.MaitisPurplePrimary
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesScreen(
    navController: NavController
) {
    var selectedGenre by remember { mutableStateOf("All") }
    val genres = listOf("All", "Action", "Comedy", "Drama", "Thriller", "Horror", "Romance")
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Movies",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaitisPurplePrimary
                ),
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            // Genre Filter
            item {
                LazyRow(
                    modifier = Modifier.padding(vertical = 12.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(genres) { genre ->
                        FilterChip(
                            selected = selectedGenre == genre,
                            onClick = { selectedGenre = genre },
                            label = { Text(genre) }
                        )
                    }
                }
            }
            
            // Now Playing
            item {
                SectionHeader(title = "Now Playing")
            }
            item {
                MovieRow(movieType = "Now Playing")
            }
            
            // Popular Movies
            item {
                SectionHeader(title = "Popular Movies")
            }
            item {
                MovieRow(movieType = "Popular")
            }
            
            // Top Rated
            item {
                SectionHeader(title = "Top Rated")
            }
            item {
                MovieRow(movieType = "Top Rated")
            }
            
            // Coming Soon
            item {
                SectionHeader(title = "Coming Soon")
            }
            item {
                MovieRow(movieType = "Coming Soon")
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
fun MovieRow(movieType: String) {
    val movies = listOf("1", "2", "3", "4", "5")
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(movies) { movie ->
            MovieCard(
                imageUrl = "https://via.placeholder.com/160x240",
                title = "$movieType Movie $movie",
                year = "2024",
                rating = String.format("%.1f", Random.nextDouble(4.0, 5.0))
            )
        }
    }
}

@Composable
fun MovieCard(
    imageUrl: String,
    title: String,
    year: String,
    rating: String
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
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.Black.copy(alpha = 0.7f))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "⭐ $rating",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 2
        )
        Text(
            text = year,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
