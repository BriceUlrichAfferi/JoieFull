package com.example.joiefull

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.compose.rememberNavController
import com.example.joiefull.common.NavigationGraph
import com.example.joiefull.features.animation.AnimatedSplashScreen
import com.example.joiefull.features.domain.repository.ClothesRepository
import com.example.joiefull.ui.theme.JoiefullTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val clothesRepository: ClothesRepository by inject() // Injecting the repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JoiefullTheme {
                AnimatedSplashScreen {
                    val navController = rememberNavController()

                    // Check if the device is a tablet based on screen width
                    val isTablet = LocalConfiguration.current.screenWidthDp >= 600

                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        Box(modifier = Modifier.padding(innerPadding)) {
                            // Pass the 'isTablet' flag to NavigationGraph
                            NavigationGraph(
                                navController = navController,
                                clothesRepository = clothesRepository,
                                isTablet = isTablet
                            )
                        }
                    }
                }
            }
        }
    }
}