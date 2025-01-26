package com.example.joiefull.presentation.detailProduct.component

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.navigation.NavController
import com.example.joiefull.common.ClothesViewModel
import com.example.joiefull.common.ShareDialog

import com.example.joiefull.presentation.detailProduct.DetailProduct
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailProductScreen(
    navController: NavController,
    clothesId: Int,
    modifier: Modifier = Modifier,
    windowSizeClass: WindowSizeClass
) {
    val viewModel: ClothesViewModel = koinViewModel()
    val clothes by viewModel.clothesState.collectAsState()
    val showDialog = rememberSaveable { mutableStateOf(false) }
    val shareText = rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current

    // Fetch clothes by ID when the composable is launched or clothesId changes
    LaunchedEffect(clothesId) {
        viewModel.loadClothes(clothesId)
    }

    if (showDialog.value) {
        ShareDialog(shareText = shareText.value, onDismiss = { showDialog.value = false }) {
            shareText.value = it
        }
    }

    if (clothes != null) {
        DetailProduct(
            clothes = clothes!!,
            onRatingChanged = { rating ->
            },
            onBackPressed = { navController.popBackStack() },
            onSharePressed = {
                val productLink = clothes?.picture?.url
                if (productLink != null) {
                    shareText.value =
                        "Check out this amazing product: ${clothes?.name} for ${clothes?.price} €\n\n${clothes?.picture?.description}\n\n$productLink"
                    showDialog.value = true
                } else {
                    Toast.makeText(context, "No product link available", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = modifier
                .then(if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact) Modifier.fillMaxSize() else Modifier)
        )
    } else {
        Text(
            text = "Error: Product not found!",
            modifier = Modifier.semantics {
                contentDescription = "Error message: Product not found"
            }
        )
    }
}