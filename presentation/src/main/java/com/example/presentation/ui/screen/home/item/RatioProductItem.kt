package com.example.presentation.ui.screen.home.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.presentation.R

@Composable
fun RatioProductItem(
    state: ProductUiState,
    isWishlisted: Boolean,
    onWishlistToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    val imageModifier = remember {
        Modifier
            .fillMaxWidth()
            .aspectRatio(6f / 4f)
            .clip(RoundedCornerShape(8.dp))
    }

    Column(modifier = modifier.fillMaxWidth().padding(8.dp)) {
        Box {
            AsyncImage(
                model = state.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = imageModifier
            )

            IconButton(
                onClick = onWishlistToggle,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 16.dp, end = 16.dp)
                    .size(24.dp)
                    .background(Color.White.copy(alpha = 0.7f), shape = CircleShape)
            ) {
                Icon(
                    painter = if (isWishlisted) painterResource(id = R.drawable.ic_btn_heart_on)
                    else painterResource(id = R.drawable.ic_btn_heart_off),
                    contentDescription = "Wishlist",
                    tint = if (isWishlisted) Color.Red else Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = state.name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(4.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            if (state.isDiscounted && state.discountedPriceText != null) {
                Text(
                    text = "${state.discountRate}%",
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFA622F)
                )
                Spacer(modifier = Modifier.width(4.dp))
            }

            Text(
                text = state.priceText,
                fontWeight = if (state.isDiscounted) FontWeight.Bold else FontWeight.Normal
            )

            if (state.isDiscounted) {
                state.discountedPriceText?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = Color.Gray,
                            textDecoration = TextDecoration.LineThrough
                        )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RatioProductItemPreview() {
    RatioProductItem(
        state = ProductUiState(
            id = 1L,
            name = "한 줄 제목 상품 예시로 길 경우 말줄임처리됩니다.",
            imageUrl = "https://via.placeholder.com/300x200.png",
            priceText = "6,000원",
            isSoldOut = false,
            isDiscounted = true,
            discountRate = 30,
            discountedPriceText = "4,500원"
        ),
        isWishlisted = true,
        onWishlistToggle = {}
    )
}