package com.example.joiefull.common

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.joiefull.presentation.home.HomeScreen

@Composable
fun JoieFullNavigationRail(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.fillMaxSize()) {
        // Navigation Rail
        NavigationRail(
            modifier = Modifier.fillMaxHeight(),
            containerColor = MaterialTheme.colorScheme.primary
        ) {
            NavigationRailItem(
                selected = false,
                onClick = { navController.navigate("home") },
                label = { Text("Home") },
                icon = { Spacer(Modifier.size(0.dp)) } // Empty icon
            )
            NavigationRailItem(
                selected = false,
                onClick = { navController.navigate("tops") },
                label = { Text("Tops") },
                icon = { Spacer(Modifier.size(0.dp)) } // Empty icon
            )
            NavigationRailItem(
                selected = false,
                onClick = { navController.navigate("bottoms") },
                label = { Text("Bottoms") },
                icon = { Spacer(Modifier.size(0.dp)) } // Empty icon
            )
            NavigationRailItem(
                selected = false,
                onClick = { navController.navigate("bags") },
                label = { Text("Bags") },
                icon = { Spacer(Modifier.size(0.dp)) } // Empty icon
            )
        }

        // Content area (home page)
        Box(modifier = Modifier.weight(1f).fillMaxHeight()) {
            HomeScreen(navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewJoieFullNavigationRail() {
    JoieFullNavigationRail(navController = rememberNavController())
}
