package com.example.data.local.mapper

import com.example.data.local.entity.WishlistEntity
import com.example.domain.model.Product

fun WishlistEntity.toDomain(): Product = Product(
    id = productId,
    name = name,
    image = image,
    originalPrice = originalPrice,
    discountedPrice = discountedPrice ?: 0,
    isSoldOut = false
)

fun Product.toEntity(): WishlistEntity = WishlistEntity(
    productId = id,
    name = name,
    image = image,
    originalPrice = originalPrice,
    discountedPrice = discountedPrice
)