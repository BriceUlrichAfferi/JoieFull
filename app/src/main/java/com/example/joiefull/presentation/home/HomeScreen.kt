package com.example.joiefull.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.joiefull.presentation.bags.composants.BagsScreen
import com.example.joiefull.presentation.bottoms.composants.BottomsScreen
import com.example.joiefull.presentation.tops.TopsScreen


@Composable
fun HomeScreen (navController: NavController, modifier: Modifier = Modifier) {
    Column(modifier
        .verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.height(16.dp))
            TopsScreen(navController = navController)
            BottomsScreen(navController = navController)
            BagsScreen(navController = navController)
        Spacer(Modifier.height(16.dp))
    }
}

