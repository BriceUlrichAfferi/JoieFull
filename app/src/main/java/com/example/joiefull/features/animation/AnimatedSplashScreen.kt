package com.example.joiefull.features.animation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.zIndex
import kotlinx.coroutines.delay
import com.example.joiefull.R

@Composable
fun AnimatedSplashScreen(content: @Composable () -> Unit) {
    var animationCompleted by remember { mutableStateOf(false) }
    var showFirstImage by remember { mutableStateOf(true) }

    // Trigger the animation sequence
    LaunchedEffect(Unit) {
        delay(1000)
        showFirstImage = false
        delay(1500)
        animationCompleted = true
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // First Image Zoom Out (initially visible)
        ZoomingImage(
            imageRes = R.drawable.icon_app_splash,
            zoomScale = 1.5f,
            durationMillis = 1500
        )

        // Second Image Fade In
        AnimatedVisibility(
            visible = !showFirstImage,
            enter = fadeIn(animationSpec = tween(durationMillis = 800)),
            exit = fadeOut()
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .zIndex(0f),
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                painter = painterResource(id = R.drawable.icon_splash),
                contentDescription = "Second Splash Screen Image"
            )
        }
    }

    if (animationCompleted) {
        content()
    }
}

@Composable
fun ZoomingImage(imageRes: Int, zoomScale: Float, durationMillis: Int) {
    var scale by remember { mutableStateOf(zoomScale) }

    LaunchedEffect(Unit) {
        animate(
            initialValue = zoomScale,
            targetValue = 1f,
            animationSpec = tween(durationMillis = durationMillis)
        ) { value, _ ->
            scale = value
        }
    }

    Image(
        painter = painterResource(id = imageRes),
        contentDescription = "Zooming Image",
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer(scaleX = scale, scaleY = scale),
        contentScale = ContentScale.Crop,
        alignment = Alignment.Center
    )
}
