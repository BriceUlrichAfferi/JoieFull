package com.example.joiefull.common

import android.content.Intent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.NavType
import com.example.joiefull.features.domain.model.Clothes
import com.example.joiefull.features.domain.repository.ClothesRepository
import com.example.joiefull.presentation.detailProduct.DetailProduct
import com.example.joiefull.presentation.home.HomeScreen
import org.koin.java.KoinJavaComponent.inject

@Composable
fun NavigationGraph(navController: NavController, clothesRepository: ClothesRepository) {
    // Define your NavHost and routes
    NavHost(
        navController = navController as NavHostController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(navController = navController) // Your home screen with bags
        }

        composable(
            "detailProduct/{clothesId}",
            arguments = listOf(navArgument("clothesId") { type = NavType.IntType })
        ) { backStackEntry ->
            val clothesId = backStackEntry.arguments?.getInt("clothesId") ?: return@composable

            // Using a state to store the fetched clothes data
            val clothes = remember { mutableStateOf<Clothes?>(null) }

            // Launch a coroutine to fetch clothes data asynchronously
            LaunchedEffect(clothesId) {
                try {
                    // Fetch clothes by ID in a coroutine scope
                    clothes.value = getClothesById(clothesRepository, clothesId)
                } catch (e: NoSuchElementException) {
                    clothes.value = null // If not found, set to null
                }
            }

            // Display the DetailProduct or error message based on the fetched clothes
            if (clothes.value != null) {
                DetailProduct(clothes = clothes.value!!, onRatingChanged = { rating ->
                    // Handle rating change logic
                },
                    onBackPressed = {
                        navController.popBackStack() // Navigate back to the previous screen
                    },
                    onSharePressed = { context ->
                        // Share logic
                        val shareText = "Check out this amazing product: ${clothes.value?.name} for ${clothes.value?.price} €\n\n${clothes.value?.picture?.description}"

                        // Create an intent to share the product details
                        val shareIntent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, shareText)
                            type = "text/plain"
                        }

                        // Let the user choose the app to share with
                        val chooserIntent = Intent.createChooser(shareIntent, "Share via")

                        // Use the passed context to start the activity
                        context.startActivity(chooserIntent)
                    }
                    )
            } else {
                Text("Error: Product not found!")
            }
        }
    }
}

suspend fun getClothesById(clothesRepository: ClothesRepository, id: Int): Clothes? {
    return try {
        clothesRepository.getClothesById(id) // Fetch from the repository
    } catch (e: NoSuchElementException) {
        null // Return null if not found
    }
}
