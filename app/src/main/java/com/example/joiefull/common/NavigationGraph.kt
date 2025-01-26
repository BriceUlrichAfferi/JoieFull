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
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun NavigationGraph(
    navController: NavHostController,
    clothesRepository: ClothesRepository,
    windowSizeClass: WindowSizeClass
) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            when (windowSizeClass.widthSizeClass) {
                WindowWidthSizeClass.Compact -> {
                    HomeScreen(navController = navController)
                }
                WindowWidthSizeClass.Medium, WindowWidthSizeClass.Expanded -> {
                    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
                    val homeScreenWidth = (screenWidth.value * 0.6).dp.coerceAtMost(screenWidth)

                    Row(modifier = Modifier.fillMaxWidth()) {
                        HomeScreen(navController = navController, modifier = Modifier.fillMaxHeight().width(homeScreenWidth))

                        val selectedProductId = navController.currentBackStackEntry?.arguments?.getInt("clothesId")
                        if (selectedProductId != null) {
                            DetailProductScreen(
                                navController = navController,
                                clothesId = selectedProductId,
                                windowSizeClass = windowSizeClass,
                                modifier = Modifier.fillMaxHeight().weight(1f)
                            )
                        }
                    }
                }
            }
        }

        composable(
            "detailProduct/{clothesId}",
            arguments = listOf(navArgument("clothesId") { type = NavType.IntType })
        ) { backStackEntry ->
            val clothesId = backStackEntry.arguments?.getInt("clothesId") ?: return@composable
            when (windowSizeClass.widthSizeClass) {
                WindowWidthSizeClass.Compact -> {
                    DetailProductScreen(
                        navController = navController,
                        clothesId = clothesId,
                        windowSizeClass = windowSizeClass
                    )
                }
                WindowWidthSizeClass.Medium, WindowWidthSizeClass.Expanded -> {
                    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
                    val homeScreenWidth = (screenWidth.value * 0.6).dp.coerceAtMost(screenWidth)

                    Row(modifier = Modifier.fillMaxWidth()) {
                        HomeScreen(navController = navController, modifier = Modifier.fillMaxHeight().width(homeScreenWidth))
                        DetailProductScreen(
                            navController = navController,
                            clothesId = clothesId,
                            windowSizeClass = windowSizeClass,
                            modifier = Modifier.fillMaxHeight().weight(2f)
                        )
                    }
                }
            }
        }
    }
}