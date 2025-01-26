package com.example.joiefull.presentation.bottoms.composants

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.joiefull.R
import com.example.joiefull.features.domain.model.Clothes
import com.example.joiefull.features.domain.model.Pictures
import com.example.joiefull.presentation.home.HomeSection
import com.example.joiefull.presentation.bottoms.BottomsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun BottomsScreen (
    viewModel: BottomsViewModel = koinViewModel(),
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val state = viewModel.bottomsState.value

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
                HomeSection(title = R.string.bottoms_section_title) {
                    BottomsRow(bottoms = state.clothes, onItemClick = { selectedBottom ->
                        navController.navigate("detailProduct/${selectedBottom.id}")
                    })
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BagsScreenPreview() {
    val sampleBottoms = listOf(
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
    }
}