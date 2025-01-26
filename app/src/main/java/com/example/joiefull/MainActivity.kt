package com.example.joiefull

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.joiefull.common.NavigationGraph
import com.example.joiefull.features.animation.AnimatedSplashScreen
import com.example.joiefull.features.domain.repository.ClothesRepository
import com.example.joiefull.ui.theme.JoiefullTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val clothesRepository: ClothesRepository by inject()

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JoiefullTheme {
                AnimatedSplashScreen {
                    val navController = rememberNavController()
                    val windowSizeClass = calculateWindowSizeClass(this)

                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        Box(modifier = Modifier.padding(innerPadding)) {
                            // Pass the 'windowSizeClass' to NavigationGraph
                            NavigationGraph(
                                navController = navController,
                                clothesRepository = clothesRepository,
                                windowSizeClass = windowSizeClass
                            )
                        }
                    }
                }
            }
        }
    }
}