package com.crafted.indiabcraft.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.crafted.indiabcraft.theme.MaitisPurplePrimary
import com.crafted.indiabcraft.ui.models.ContentItem

@Composable
fun ContentCard(
    contentItem: ContentItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(140.dp)
            .clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .width(140.dp)
                .height(210.dp)
                .clip(RoundedCornerShape(12.dp))
        ) {
            AsyncImage(
                model = contentItem.thumbnailUrl.ifEmpty { "https://via.placeholder.com/160x240" },
                contentDescription = contentItem.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = contentItem.title,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            maxLines = 2
        )
        if (contentItem.category.isNotEmpty()) {
            Text(
                text = contentItem.category,
                style = MaterialTheme.typography.bodySmall,
                color = MaitisPurplePrimary
            )
        }
    }
}
