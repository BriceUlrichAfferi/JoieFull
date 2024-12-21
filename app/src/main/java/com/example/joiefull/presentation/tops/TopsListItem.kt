package com.example.joiefull.presentation.tops

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
import com.example.joiefull.presentation.bottoms.composants.BottomsListItem
import com.example.joiefull.ui.theme.JoiefullTheme

@Composable
fun TopsListItem(
    clothes: Clothes,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isPreview: Boolean = false
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        horizontalAlignment = Alignment.Start
    ) {
        // Image Section
        Box(
            modifier = Modifier
                .width(198.dp)
                .height(198.dp)
                .clickable { onClick() }
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
                    modifier = Modifier.fillMaxSize()
                        .clip(RoundedCornerShape(12.dp))
                )
            }


            // Overlay Likes
            Surface(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(4.dp),
                shape = RoundedCornerShape(8.dp),
                color = Color.White,
                contentColor = Color.Black
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp)
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
        Text(
            text = clothes.name,
            fontSize = 14.sp,
            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),

            modifier = Modifier
                .padding(top = 4.dp)

        )
        Row(
            modifier = Modifier
                .width(198.dp)
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
    }
}



@Preview(showBackground = true, name = "Bag List Item Preview")
@Composable
fun BagListItemPreview() {

    val sampleClothes = Clothes(
        name = "Sample Top",
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
        /*TopsListItem(
            clothes = sampleClothes,
            modifier = Modifier,
            isPreview = true // This will show the placeholder image
        )*/
    }
}
