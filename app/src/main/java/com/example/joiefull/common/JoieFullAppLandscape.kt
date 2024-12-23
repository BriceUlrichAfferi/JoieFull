package com.example.joiefull.common

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.NavController
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import com.example.joiefull.presentation.home.HomeScreen
import com.example.joiefull.ui.theme.JoiefullTheme


@Composable
fun JoieFullAppLandscape(navController: NavController){

    val configuration = LocalConfiguration.current

    JoiefullTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Row {
                JoieFullNavigationRail(navController)
                HomeScreen(navController = navController)
            }
        }
    }
}
