package com.example.joiefull.presentation.detailProduct

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import com.example.joiefull.ui.theme.Orange

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.joiefull.R
import com.example.joiefull.common.LikesViewModel
import com.example.joiefull.features.domain.model.Clothes
import com.example.joiefull.features.domain.model.Pictures
import com.example.joiefull.presentation.bags.composants.BagListItem
import com.example.joiefull.presentation.detailProduct.component.SelectableRoundImage
import com.example.joiefull.ui.theme.JoiefullTheme
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun DetailProduct(
    clothes: Clothes,
    onRatingChanged: (Int) -> Unit,
    onBackPressed: () -> Unit,
    onSharePressed: (context: Context) -> Unit,
    viewModel: LikesViewModel = viewModel(),
    modifier: Modifier = Modifier,
    isPreview: Boolean = false
) {
    val context = LocalContext.current
    val likes by viewModel.getLikesForItem(clothes.id)
    val isLiked by viewModel.isLiked(clothes.id)
    val totalLikes by remember {
        derivedStateOf { clothes.likes + likes }
    }
    var textFieldValue by rememberSaveable { mutableStateOf("") }

    var selectedImageUri by rememberSaveable { mutableStateOf<Uri?>(null) }
    var rating by rememberSaveable { mutableStateOf(0) }
    val shareText = stringResource(R.string.share, clothes.name)

    fun showToast(message: String, context: Context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(18.dp)
            .verticalScroll(rememberScrollState())
            .semantics(mergeDescendants = true) {
                contentDescription = "Details for ${clothes.name}"
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(530.dp)
        ) {
            if (isPreview || clothes.picture.url.isEmpty()) {
                Image(
                    painter = painterResource(id = R.drawable.logo_pacem),
                    contentDescription = "Bag Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .border(1.dp, Color.Black, shape = RoundedCornerShape(4.dp))
                        .background(Color.Green)
                )
            } else {
                AsyncImage(
                    model = clothes.picture.url,
                    contentDescription = clothes.picture.description,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                )
            }

            IconButton(onClick = onBackPressed) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.navigate_up),
                    tint = Color.White,
                    modifier = Modifier.size(48.dp)
                )
            }

            IconButton(onClick = { onSharePressed(context) }, modifier = Modifier.align(Alignment.TopEnd)) {
                Icon(
                    Icons.Default.Share,
                    contentDescription = stringResource(R.string.share, clothes.name),
                    tint = Color.White,
                    modifier = Modifier.size(48.dp)
                )
            }

            Surface(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(14.dp)
                    .clickable(onClickLabel = stringResource(R.string.action_toggle_favorite)) {
                        if (isLiked) {
                            viewModel.decrementLikes(clothes.id)
                            showToast("${clothes.name} removed from favorite", context)
                        } else {
                            viewModel.incrementLikes(clothes.id)
                            showToast("${clothes.name} added to favorite", context)
                        }
                    },
                shape = RoundedCornerShape(12.dp),
                color = Color.White,
                contentColor = Color.Black
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 14.dp)
                ) {
                    Icon(
                        imageVector = if (isLiked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite Icon",
                        modifier = Modifier.size(30.dp),
                        tint = if (isLiked) Color.Red else Color.Black
                    )
                    Text(
                        text = "$totalLikes",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        // Name and Price Section
        Row(
            modifier = Modifier
                .fillMaxWidth() // Ensure the row takes up the full width
                .padding(top = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = clothes.name,
                fontSize = 14.sp,
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(top = 4.dp)
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Stars",
                    tint = Orange,
                    modifier = Modifier.size(20.dp)
                )

                Text(
                    text = "4.6",
                    fontSize = 14.sp,
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth() // Ensure the row takes up the full width
                .padding(top = 4.dp)
                .semantics(mergeDescendants = true) {},
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${clothes.price} €",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )

            if (clothes.price != clothes.original_price) {
                Text(
                    text = "${clothes.original_price} €",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal,
                    style = TextStyle(textDecoration = TextDecoration.LineThrough)
                )
            }
        }

        // Product Description Section
        Surface(
            color = Color.White,
            modifier = Modifier.fillMaxWidth() // Ensure the surface takes full width
        ) {
            Text(
                text = "${clothes.picture.description} ",
                fontSize = 14.sp,
                fontWeight = FontWeight.W400,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(2.dp) // Inner padding
            )
        }

        // Rating and Image Selection Section
        Row(
            verticalAlignment = Alignment.CenterVertically,

            modifier = Modifier
                .fillMaxWidth() // Ensure the row takes full width
                .padding(8.dp)
               // .semantics(mergeDescendants = true) {}

        ) {
            // Round Image View
            SelectableRoundImage(
                imageUri = selectedImageUri,
                onImageSelected = { uri -> selectedImageUri = uri },
                modifier = Modifier
                    .size(48.dp)
                    .clickable(
                        onClick = {  },
                        onClickLabel = stringResource(R.string.action_profil_picture)
                    )
            )

            // Row for Stars
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 8.dp)
            ) {
                for (i in 1..5) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Star $i",
                        tint = if (i <= rating) Color(0xFFFFD700) else Color.Gray, // Gold for selected stars, gray otherwise
                        modifier = Modifier
                            .size(30.dp)
                            .clickable ( onClickLabel = stringResource(R.string.action_set_star)
                            ) {
                                rating = i
                                onRatingChanged(rating) // Notify about the rating change
                            }
                    )
                }
            }
        }

        // Review Section
        Surface(
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth() // Ensure the surface takes full width
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .border(
                        width = 1.dp, // Border width
                        color = Color.Gray, // Border color
                        shape = RoundedCornerShape(12.dp) // Optional: Rounded corners
                    )
            ) {
                TextField(
                    value = textFieldValue,
                    onValueChange = { newValue -> textFieldValue = newValue },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                        focusedIndicatorColor = Color.Transparent, // Remove internal underline
                        unfocusedIndicatorColor = Color.Transparent // Remove internal underline
                    ),
                    placeholder = {
                        Text(stringResource(R.string.impression))
                    },
                    modifier = Modifier
                        .fillMaxWidth() // Ensure the TextField takes full width
                        .heightIn(min = 56.dp)
                        .padding(4.dp) // Optional padding inside the border
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally, // Center the items horizontally
                modifier = Modifier
                    .align(Alignment.CenterEnd) // Align the content to the end if needed
                    .clickable {
                        if (textFieldValue.isNotEmpty()) {
                            showToast("Review added!", context)
                            textFieldValue = "" // Clear the TextField
                            rating = 0 // Reset the rating
                            selectedImageUri = null // Reset the selected image
                        } else {
                            showToast("Please write a review before sending.", context)
                        }
                    }
            ) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(48.dp) // Adjust icon size
                )
                Text(
                    text = "Send",
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 4.dp), // Add spacing between icon and text
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }



    }
}



@Preview(showBackground = true, name = "Bag List Item Preview")
@Composable
fun DetailProductPreview() {

    val sampleClothes = Clothes(
        name = "Sample Bag",
        price = 49.99,
        original_price = 69.99,
        likes = 150,
        category = "",
        id = 1,
        picture = Pictures(
            url = "", // Empty URL to trigger the placeholder image
            description = "A stylish sample bag"
        )
    )

    // Using your theme if you have one defined
    JoiefullTheme {
        DetailProduct(
            clothes = sampleClothes,
            modifier = Modifier,
            onRatingChanged = { rating ->
                println("Rating changed to $rating") // Example logging for rating change
            },
            isPreview = true, // This will show the placeholder image
            onBackPressed = {},
            onSharePressed = {}
        )
    }
}
