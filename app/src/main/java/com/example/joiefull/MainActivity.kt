package com.example.joiefull

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.joiefull.features.animation.AnimatedSplashScreen
import com.example.joiefull.presentation.bags.composants.BagsScreen
import com.example.joiefull.presentation.bottoms.composants.BottomsScreen
import com.example.joiefull.presentation.home.HomeScreen
import com.example.joiefull.presentation.tops.TopsScreen
import com.example.joiefull.ui.theme.JoiefullTheme
import dagger.hilt.android.AndroidEntryPoint


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JoiefullTheme {
                AnimatedSplashScreen {
                    // Pass the main content here
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        HomeScreen(modifier = Modifier.padding(innerPadding)) // Apply innerPadding here
                    }
                }
            }
        }
    }
}





