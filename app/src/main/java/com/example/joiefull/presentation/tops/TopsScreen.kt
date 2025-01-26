package com.example.joiefull.presentation.tops

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.joiefull.R
import com.example.joiefull.features.domain.model.Clothes
import com.example.joiefull.features.domain.model.Pictures
import com.example.joiefull.presentation.home.HomeSection
import org.koin.androidx.compose.koinViewModel

@Composable
fun TopsScreen (
    viewModel: TopsViewModel = koinViewModel(),
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val state = viewModel.topsState.value

    Box(modifier = modifier.fillMaxSize()
        .semantics {
            contentDescription = "Tops section"
        }) {
        when {
            state.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center)
                    .semantics {
                        contentDescription = "Loading tops"
                    }
                )
            }
            state.error.isNotEmpty() -> {
                Text(
                    text = state.error,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Center)
                        .semantics {
                            contentDescription = "Error: ${state.error}"
                        }
                )
            }
            else -> {
                HomeSection(title = R.string.tops_section_title) {
                    TopsRow(tops = state.clothes, onItemClick = { selectedTop ->
                        navController.navigate("detailProduct/${selectedTop.id}")
                    })
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BagsScreenPreview() {
    val sampleTops = listOf(
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
        //TopsRow(tops = sampleTops)
    }
}
