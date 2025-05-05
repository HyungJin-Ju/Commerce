package com.example.presentation.ui.screen.home.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.ui.screen.home.model.ProductListType
import com.example.presentation.ui.screen.home.model.ProductUiState

@Composable
fun ProductList(
    products: List<ProductUiState>,
    type: ProductListType,
    onWishlistToggle: (ProductUiState) -> Unit,
    modifier: Modifier = Modifier
) {
    if (products.isEmpty()) return

    Column(modifier = modifier) {
        when (type) {
            is ProductListType.Vertical -> {
                products.forEach { product ->
                    RatioProductItem(
                        state = product,
                        isWishlisted = product.isWishlisted,
                        onWishlistToggle = { onWishlistToggle(product) }
                    )
                }
            }

            is ProductListType.Horizontal -> {
                LazyRow(modifier = Modifier.fillMaxWidth()) {
                    items(products, key = { it.id }) { product ->
                        FixedProductItem(
                            state = product,
                            isWishlisted = product.isWishlisted,
                            onWishlistToggle = { onWishlistToggle(product) }
                        )
                    }
                }
            }

            is ProductListType.Grid -> {
                val visibleCount = type.columnCount * type.rowCount
                val subList = products.take(visibleCount)

                LazyVerticalGrid(
                    columns = GridCells.Fixed(type.columnCount),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(640.dp)
                ) {
                    items(subList, key = { it.id }) { product ->
                        FixedProductItem(
                            state = product,
                            isWishlisted = product.isWishlisted,
                            onWishlistToggle = { onWishlistToggle(product) }
                        )
                    }
                }
            }
        }

        ProductListDivider()
    }
}


@Preview(showBackground = true, name = "Vertical List Preview")
@Composable
fun ProductListVerticalPreview() {
    val mockItems = sampleProductList()
    ProductList(
        products = mockItems,
        type = ProductListType.Vertical,
        modifier = Modifier.fillMaxWidth(),
        onWishlistToggle = {}
    )
}

@Preview(showBackground = true, name = "Horizontal List Preview")
@Composable
fun ProductListHorizontalPreview() {
    val mockItems = sampleProductList()
    ProductList(
        products = mockItems,
        type = ProductListType.Horizontal,
        modifier = Modifier.fillMaxWidth().height(250.dp),
        onWishlistToggle = {}
    )
}

@Preview(showBackground = true, name = "Grid 3x2 Preview")
@Composable
fun ProductListGridPreview() {
    val mockItems = sampleProductList()
    ProductList(
        products = mockItems,
        type = ProductListType.Grid(columnCount = 3, rowCount = 2),
        modifier = Modifier.fillMaxWidth(),
        onWishlistToggle = {}
    )
}

@Composable
private fun sampleProductList(): List<ProductUiState> {
    return List(10) {
        val isDiscounted = it % 2 == 0
        ProductUiState(
            id = it.toLong(),
            name = "상품 제목 $it",
            imageUrl = "https://something.png",
            priceText = if (isDiscounted) "5,000원" else "3,900원",
            isSoldOut = it % 5 == 0,
            isDiscounted = isDiscounted,
            discountedPriceText = if (isDiscounted) "3,900원" else null,
            discountRate = 30,
            isWishlisted = it % 3 == 0
        )
    }
}