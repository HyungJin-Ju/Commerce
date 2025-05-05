package com.example.presentation.ui.screen.home.item

import com.example.domain.model.Product
import com.example.domain.model.Section

fun ProductUiState.toDomain(): Product {
    return Product(
        id = id,
        name = name,
        image = imageUrl,
        originalPrice = priceText.filter { it.isDigit() }.toIntOrNull() ?: 0,
        discountedPrice = discountedPriceText?.filter { it.isDigit() }?.toIntOrNull() ?: 0,
        isSoldOut = isSoldOut
    )
}

fun Section.toUiState(): SectionUiState {
    val type = when (this.type.lowercase()) {
        "vertical" -> ProductListType.Vertical
        "horizontal" -> ProductListType.Horizontal
        "grid" -> ProductListType.Grid(columnCount = 3, rowCount = 2)
        else -> ProductListType.Vertical
    }

    return SectionUiState(
        id = this.id,
        title = this.title,
        type = type
    )
}

fun Product.toUiState(isWishlisted: Boolean): ProductUiState {
    val rate = if (discountedPrice > 0 && originalPrice > 0) {
        ((originalPrice - discountedPrice) * 100 / originalPrice)
    } else {
        null
    }

    return ProductUiState(
        id = id,
        name = name,
        imageUrl = image,
        priceText = "${originalPrice}원",
        isSoldOut = isSoldOut,
        isDiscounted = discountedPrice > 0,
        discountedPriceText = discountedPrice.let { "${it}원" },
        discountRate = rate,
        isWishlisted = isWishlisted
    )
}