package com.example.joiefull.presentation.tops

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.joiefull.features.domain.model.Clothes
import com.example.joiefull.features.domain.model.Pictures
import com.example.joiefull.presentation.bottoms.composants.BottomsListItem
import com.example.joiefull.presentation.bottoms.composants.BottomsRow
import com.example.joiefull.ui.theme.JoiefullTheme

@Composable
fun TopsRow (
    tops: List<Clothes>,
    onItemClick: (Clothes) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(1.dp)
    ) {
        items(tops) { tops ->
            TopsListItem(clothes = tops, onClick = { onItemClick(tops) })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BagsRowPreview() {
    JoiefullTheme {
        // Sample data for previewing BagsRow
        val sampleTops = listOf(
            Clothes(
                id = 1,
                picture = Pictures("A stylish bag", "https://example.com/image1.jpg"),
                name = "Stylish Bag 1",
                category = "ACCESSORIES",
                likes = 100,
                price = 49.99,
                original_price = 59.99
            ),
            Clothes(
                id = 2,
                picture = Pictures("Another stylish bag", "https://example.com/image2.jpg"),
                name = "Stylish Bag 2",
                category = "ACCESSORIES",
                likes = 150,
                price = 69.99,
                original_price = 79.99
            )
        )

        // BagsRow to display the sample list of bags
       // TopsRow(tops = sampleTops)
    }
}