package com.example.presentation.ui.screen.home.item

import androidx.compose.runtime.Immutable

@Immutable
data class ProductUiState(
    val id: Long,
    val name: String,
    val imageUrl: String,
    val priceText: String,
    val isSoldOut: Boolean,
    val isDiscounted: Boolean,
    val discountedPriceText: String?,
    val isWishlisted: Boolean = false
)