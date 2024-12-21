package com.example.joiefull

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
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
                    // Initialize NavController here
                    val navController = rememberNavController()

                    // Wrap in Scaffold to handle padding
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        // Using a Box to stack components if necessary
                        Box(modifier = Modifier.padding(innerPadding)) {
                            // NavigationGraph for navigation handling
                            NavigationGraph(navController = navController, clothesRepository = clothesRepository)
                        }
                    }

                }
            }
        }
    }
}







