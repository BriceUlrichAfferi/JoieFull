package com.example.joiefull.presentation.bags.composants

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.joiefull.presentation.bags.BagsViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.joiefull.R
import com.example.joiefull.features.domain.model.Clothes
import com.example.joiefull.features.domain.model.Pictures
import com.example.joiefull.presentation.home.HomeSection
import org.koin.androidx.compose.koinViewModel


@Composable
fun BagsScreen(
    viewModel: BagsViewModel = koinViewModel(),
    modifier: Modifier = Modifier
) {
    val state = viewModel.bagsState.value

    Box(modifier = modifier.fillMaxSize()) {
        when {
            state.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            state.error.isNotEmpty() -> {
                Text(
                    text = state.error,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            else -> {
                HomeSection(title = R.string.bags_section_title) {
                    BagsRow(bags = state.bags)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BagsScreenPreview() {
    val sampleBags = listOf(
        Clothes(
            id = 1,
            picture = Pictures("A stylish bag", "https://example.com/image.jpg"),
            name = "Stylish Bag",
            category = "ACCESSORIES",
            likes = 100,
            price = 49.99,
            original_price = 59.99
        )
    )

    HomeSection(title = R.string.bags_section_title) {
        BagsRow(bags = sampleBags)
    }
}

