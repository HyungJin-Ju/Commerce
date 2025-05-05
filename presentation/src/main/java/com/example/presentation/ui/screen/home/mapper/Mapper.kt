package com.example.presentation.ui.screen.home.mapper

import com.example.domain.model.Product
import com.example.domain.model.Section
import com.example.presentation.ui.screen.home.model.ProductListType
import com.example.presentation.ui.screen.home.model.ProductUiState
import com.example.presentation.ui.screen.home.model.SectionUiState
import java.text.NumberFormat
import java.util.Locale

fun ProductUiState.toDomain(): Product {
    val numberFormat = NumberFormat.getNumberInstance(Locale.KOREA)

    fun parsePrice(text: String?): Int {
        return try {
            text?.replace("원", "")?.let {
                numberFormat.parse(it)?.toInt() ?: 0
            } ?: 0
        } catch (e: Exception) {
            0
        }
    }

    return Product(
        id = id,
        name = name,
        image = imageUrl,
        originalPrice = parsePrice(priceText),
        discountedPrice = parsePrice(discountedPriceText),
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
    val formatter = NumberFormat.getNumberInstance(Locale.KOREA)

    val rate = if (discountedPrice > 0 && originalPrice > 0) {
        ((originalPrice - discountedPrice) * 100 / originalPrice)
    } else {
        null
    }

    return ProductUiState(
        id = id,
        name = name,
        imageUrl = image,
        priceText = "${formatter.format(originalPrice)}원",
        isSoldOut = isSoldOut,
        isDiscounted = discountedPrice > 0,
        discountedPriceText = formatter.format(discountedPrice).let { "${it}원" },
        discountRate = rate,
        isWishlisted = isWishlisted
    )
}