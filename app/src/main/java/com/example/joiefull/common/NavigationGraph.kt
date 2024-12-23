package com.example.joiefull.common

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.NavType
import com.example.joiefull.features.domain.repository.ClothesRepository
import com.example.joiefull.presentation.detailProduct.component.DetailProductScreen
import com.example.joiefull.presentation.home.HomeScreen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun NavigationGraph(
    navController: NavController,
    clothesRepository: ClothesRepository,
    isTablet: Boolean // Receive 'isTablet' as a parameter
) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = "home"
    ) {
        composable("home") {
            if (isTablet) {
                // On tablet, show HomeScreen and DetailProductScreen side by side
                val screenWidth = LocalConfiguration.current.screenWidthDp.dp
                val homeScreenWidth = (screenWidth.value * 0.6).dp.coerceAtMost(screenWidth)

                // Home screen with DetailProduct only if a product is selected
                Row(modifier = Modifier.fillMaxWidth()) {
                    HomeScreen(navController = navController, modifier = Modifier.fillMaxHeight().width(homeScreenWidth))

                    // Only show DetailProductScreen if a product is selected
                    val selectedProductId = navController.currentBackStackEntry?.arguments?.getInt("clothesId")
                    if (selectedProductId != null) {
                        DetailProductScreen(
                            navController = navController,
                            clothesRepository = clothesRepository,
                            clothesId = selectedProductId,
                            modifier = Modifier.fillMaxHeight().weight(1f)
                        )
                    }
                }
            } else {
                // On phone, just display HomeScreen
                HomeScreen(navController = navController)
            }
        }

        composable(
            "detailProduct/{clothesId}",
            arguments = listOf(navArgument("clothesId") { type = NavType.IntType })
        ) { backStackEntry ->
            val clothesId = backStackEntry.arguments?.getInt("clothesId") ?: return@composable
            if (isTablet) {
                val screenWidth = LocalConfiguration.current.screenWidthDp.dp
                val homeScreenWidth = (screenWidth.value * 0.6).dp.coerceAtMost(screenWidth)

                // On tablet, show HomeScreen and DetailProductScreen side by side
                Row(modifier = Modifier.fillMaxWidth()) {
                    HomeScreen(navController = navController, modifier = Modifier.fillMaxHeight().width(homeScreenWidth))
                    DetailProductScreen(
                        navController = navController,
                        clothesRepository = clothesRepository,
                        clothesId = clothesId,
                        modifier = Modifier.fillMaxHeight().weight(2f)
                    )
                }
            } else {
                // On phone, show just DetailProductScreen
                DetailProductScreen(
                    navController = navController,
                    clothesRepository = clothesRepository,
                    clothesId = clothesId
                )
            }
        }
    }
}
