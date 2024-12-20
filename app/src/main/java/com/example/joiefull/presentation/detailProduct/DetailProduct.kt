package com.example.joiefull.presentation.detailProduct

import android.net.Uri
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.joiefull.R
import com.example.joiefull.features.domain.model.Clothes
import com.example.joiefull.features.domain.model.Pictures
import com.example.joiefull.presentation.bags.composants.BagListItem
import com.example.joiefull.presentation.detailProduct.component.SelectableRoundImage
import com.example.joiefull.ui.theme.JoiefullTheme

@Composable
fun DetailProduct (
    clothes: Clothes,
    onRatingChanged: (Int) -> Unit,
    modifier: Modifier = Modifier,
    isPreview: Boolean = false
) {

    var selectedImageUri by rememberSaveable { mutableStateOf<Uri?>(null) }
    var rating by rememberSaveable { mutableStateOf(0) } // Holds the current rating (0-5)


    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Image Section
        Box(
            modifier = Modifier
                .width(328.dp)
                .height(431.dp)
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
                        .fillMaxSize()
                        .clip(RoundedCornerShape(12.dp))
                )
            }


            // Overlay Likes
            Surface(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(14.dp),
                shape = RoundedCornerShape(12.dp),
                color = Color.White,
                contentColor = Color.Black
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "${clothes.likes}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        // Name and Price Section

        Row (
            modifier = Modifier
                .width(328.dp)
                .padding(top = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = clothes.name,
                fontSize = 14.sp,
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),

                modifier = Modifier
                    .padding(top = 4.dp)

            )


            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Orange,
                    modifier = Modifier
                        .size(20.dp)
                )

                Text(
                    text = "4.6",
                    fontSize = 14.sp,
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),

                    modifier = Modifier
                        .padding(top = 4.dp)

                )
            }
        }

        Row(
            modifier = Modifier
                .width(328.dp)
                .padding(top = 4.dp),
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

        Surface(
            color = Color.White,
            modifier = Modifier
                .width(328.dp)
        ) {
            Text(
                text = "N'hésite pas à me contacter si tu as des questions ou si tu as besoin de clarifications. Je suis convaincu que tu accompliras un excellent travail, et que cette application deviendra une référence en termes d'accessibilité et d'utilité pour nos clients.",
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(2.dp) // Inner padding
            )
        }




        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(328.dp)
                .padding(8.dp)
        ) {
            // Round Image View
            SelectableRoundImage(
                imageUri = selectedImageUri,
                onImageSelected = { uri -> selectedImageUri = uri },
                modifier = Modifier.size(40.dp)
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
                            .clickable {
                                rating = i
                                onRatingChanged(rating) // Notify about the rating change
                            }
                    )
                }
            }

        }

        Surface(
            color = Color.White,
            modifier = Modifier
                .width(320.dp)
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
                    value = "",
                    onValueChange = {},
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
                        .width(328.dp)
                        .heightIn(min = 56.dp)
                        .padding(4.dp) // Optional padding inside the border
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
            isPreview = true // This will show the placeholder image
        )
    }
}
