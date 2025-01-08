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
import com.example.joiefull.common.ShareDialog
import com.example.joiefull.common.getClothesById
import com.example.joiefull.features.domain.model.Clothes
import com.example.joiefull.features.domain.repository.ClothesRepository
import com.example.joiefull.presentation.detailProduct.DetailProduct

@Composable
fun DetailProductScreen(
    navController: NavController,
    clothesRepository: ClothesRepository,
    clothesId: Int,
    modifier: Modifier = Modifier,
    windowSizeClass: WindowSizeClass
) {
    val clothes = rememberSaveable { mutableStateOf<Clothes?>(null) }
    val showDialog = rememberSaveable { mutableStateOf(false) }
    val shareText = rememberSaveable { mutableStateOf("") }

    LaunchedEffect(clothesId) {
        try {
            clothes.value = getClothesById(clothesRepository, clothesId)
        } catch (e: NoSuchElementException) {
            clothes.value = null
        }
    }

    val context = LocalContext.current

    if (showDialog.value) {
        ShareDialog(shareText = shareText.value, onDismiss = { showDialog.value = false }) {
            shareText.value = it
        }
    }

    if (clothes.value != null) {
        DetailProduct(
            clothes = clothes.value!!,
            onRatingChanged = { rating -> /* Handle rating */ },
            onBackPressed = { navController.popBackStack() },
            onSharePressed = {
                val productLink = clothes.value?.picture?.url
                if (productLink != null) {
                    shareText.value =
                        "Check out this amazing product: ${clothes.value?.name} for ${clothes.value?.price} €\n\n${clothes.value?.picture?.description}\n\n$productLink"
                    showDialog.value = true
                } else {
                    Toast.makeText(context, "No product link available", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = modifier
                .then(if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact) Modifier.fillMaxSize() else Modifier)
                .semantics(mergeDescendants = true) {
                    contentDescription = "Product details for ${clothes.value?.name}"
                }
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