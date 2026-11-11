package com.crafted.indiabcraft.ui.sports

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.crafted.indiabcraft.theme.MaitisPurplePrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SportsScreen(
    navController: NavController
) {
    var selectedSport by remember { mutableStateOf("All") }
    val sports = listOf("All", "Cricket", "Football", "Tennis", "Basketball", "Kabaddi")
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Sports",
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            // Sport Filter
            item {
                LazyRow(
                    modifier = Modifier.padding(vertical = 12.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(sports) { sport ->
                        FilterChip(
                            selected = selectedSport == sport,
                            onClick = { selectedSport = sport },
                            label = { Text(sport) }
                        )
                    }
                }
            }
            
            // Live Matches
            item {
                SectionHeader(title = "Live Matches")
            }
            item {
                LiveMatchesRow()
            }
            
            // Upcoming Matches
            item {
                SectionHeader(title = "Upcoming Matches")
            }
            item {
                UpcomingMatchesList()
            }
            
            // Sports News
            item {
                SectionHeader(title = "Sports News")
            }
            item {
                SportsNewsList()
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
fun LiveMatchesRow() {
    val matches = listOf(
        Match("IND", "AUS", "Cricket", "Live", "45/2"),
        Match("ENG", "Pak", "Cricket", "Live", "120/4"),
        Match("Man Utd", "Liverpool", "Football", "Live", "2-1")
    )
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(matches) { match ->
            LiveMatchCard(match = match)
        }
    }
}

data class Match(
    val team1: String,
    val team2: String,
    val sport: String,
    val status: String,
    val score: String
)

@Composable
fun LiveMatchCard(match: Match) {
    Card(
        modifier = Modifier
            .width(200.dp)
            .clickable { },
        colors = CardDefaults.cardColors(
            containerColor = MaitisPurplePrimary.copy(alpha = 0.1f)
        )
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = match.team1,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold
                )
                Badge(
                    containerColor = Color.Red
                ) {
                    Text("LIVE", color = Color.White, style = MaterialTheme.typography.labelSmall)
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "vs",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = match.team2,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = match.score,
                style = MaterialTheme.typography.titleMedium,
                color = MaitisPurplePrimary,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = match.sport,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun UpcomingMatchesList() {
    val matches = listOf(
        Match("IND", "SA", "Cricket", "Tomorrow", ""),
        Match("Real Madrid", "Barcelona", "Football", "Tomorrow", ""),
        Match("Federer", "Nadal", "Tennis", "Dec 25", "")
    )
    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        matches.forEach { match ->
            UpcomingMatchCard(match = match)
        }
    }
}

@Composable
fun UpcomingMatchCard(match: Match) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "${match.team1} vs ${match.team2}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = match.sport,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Text(
                text = match.status,
                style = MaterialTheme.typography.labelMedium,
                color = MaitisPurplePrimary,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun SportsNewsList() {
    val news = listOf(
        "India wins thrilling match against Australia",
        "World Cup schedule announced",
        "Top players transfer news"
    )
    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        news.forEach { item ->
            NewsCard(headline = item)
        }
    }
}

@Composable
fun NewsCard(headline: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaitisPurplePrimary.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Sports,
                    contentDescription = null,
                    tint = MaitisPurplePrimary,
                    modifier = Modifier.size(40.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = headline,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "2 hours ago",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}