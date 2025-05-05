package com.example.presentation.ui.screen.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.presentation.R
import com.example.presentation.ui.screen.home.model.ProductUiState

@Composable
fun FixedProductItem(
    state: ProductUiState,
    isWishlisted: Boolean,
    onWishlistToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.width(150.dp).height(320.dp).padding(4.dp)) {
        ProductImageWithWishlistButton(
            imageUrl = state.imageUrl,
            isWishlisted = isWishlisted,
            onWishlistToggle = onWishlistToggle
        )

        Text(
            text = state.name,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 8.dp)
        )

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
        }

        if (state.isDiscounted && state.discountedPriceText != null) {
            Text(
                text = state.discountedPriceText,
                color = Color.Gray,
                textDecoration = TextDecoration.LineThrough,
                modifier = Modifier.padding(top = 2.dp)
            )
        }
    }
}

@Composable
private fun ProductImageWithWishlistButton(
    imageUrl: String,
    isWishlisted: Boolean,
    onWishlistToggle: () -> Unit
) {
    Box(
        modifier = Modifier.width(150.dp).height(200.dp)
    ) {
        val imagePainter = rememberAsyncImagePainter(model = imageUrl)

        Image(
            painter = imagePainter,
            contentDescription = null,
            modifier = Modifier
                .size(150.dp, 200.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        IconButton(
            onClick = onWishlistToggle,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 26.dp, end = 4.dp)
                .size(20.dp)
        ) {
            Icon(
                painter = if (isWishlisted) painterResource(id = R.drawable.ic_btn_heart_on)
                else painterResource(id = R.drawable.ic_btn_heart_off),
                contentDescription = null,
                tint = if (isWishlisted) Color.Red else Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FixedProductItemPreview() {
    FixedProductItem(
        state = ProductUiState(
            id = 1L,
            name = "긴 제목의 상품 예시로, 두줄을 넘어갈 경우에는 말줄임처리가 됩니다.",
            imageUrl = "https://via.placeholder.com/150x200.png",
            priceText = "3,200원",
            isSoldOut = false,
            isDiscounted = true,
            discountedPriceText = "8,000원",
            discountRate = 30,
            isWishlisted = false
        ),
        isWishlisted = false,
        onWishlistToggle = {}
    )
}