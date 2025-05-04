package com.example.data.remote.dto

data class ProductListResponse(
    val data: List<ProductDto>
)

data class ProductDto(
    val id: Long,
    val name: String,
    val image: String,
    val originalPrice: Int,
    val discountedPrice: Int? = null,
    val isSoldOut: Boolean
)